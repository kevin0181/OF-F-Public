import {useQuery} from "../../../Config/getQuery";
import {useNavigate} from "react-router-dom";
import {useEffect} from "react";
import "./../../../styles/css/management/main.css";
import "./../../../styles/css/management/category.css";
import "./../../../styles/css/management/order.css";
import "animate.css";
import {tokenStoreAdminAxios} from "../../../Config/customStoreAdminAjax";
import {useRecoilState} from "recoil";
import {
    selectStoreInfoRecoil,
    storeOrderList as storeOrderRecoil,
    storeStatus as storeStatusRecoil
} from "../../../store/management/storeInfo";
import {nodeServerAxios} from "../../../Config/customAxios";
import {getCookie} from "../../../Config/cookie";
import StoreOrderDetail from "../components/order/StoreOrderDetail";

let OrderStart = ({socketStoreOrder, setSocketStoreOrder}) => {
    const query = useQuery();

    const navigate = useNavigate();

    const [store, setStore] = useRecoilState(selectStoreInfoRecoil); //선택된 가게 정보
    const [storeOrder, setStoreOrder] = useRecoilState(storeOrderRecoil); // 들어온 주문 전체 내역
    const [storeStatus, setStoreStatus] = useRecoilState(storeStatusRecoil); // 가게 상태 정보 몽고디비에서 가져옴

    // spring -> 서버에서 이미 들어온 주문 가져오기
    useEffect(() => {
        if (storeOrder.length === 0) {
            if (store.seq !== undefined && store.storeOrders === null) {
                getOrderData();
            }
        }
    }, []);

    useEffect(() => {
        if (Object.keys(socketStoreOrder).length !== 0) {
            setStoreOrder([
                socketStoreOrder,
                ...storeOrder
            ]);
            setSocketStoreOrder({});
        }
    }, [socketStoreOrder])

    let getOrderData = () => { //주문 데이터 가져옴
        tokenStoreAdminAxios({
            url: "/api/v1/store/admin/get/order?storeSeq=" + store.seq,
            method: "GET"
        }).then(res => {
            setStoreOrder(
                res.data.data
            );
        }).catch(err => {
            console.log(err);
        })
    }


    let storeStatusBtn = () => { //가게 상태 보냄
        let q = "";
        if (storeStatus.status) {
            q = "가게 주문을 종료 하시겠습니까?";
        } else {
            q = "가게 주문을 시작하시겠습니까?";
        }

        // eslint-disable-next-line no-restricted-globals
        if (confirm(q)) {
            nodeServerAxios({
                method: "POST",
                url: "/store/set/status",
                data: storeStatus,
                headers: {
                    "accessToken": `Bearer ${getCookie("accessToken")}`
                }
            }).then(res => {
                setStoreStatus(
                    res.data.data
                )
            }).catch(err => {
                alert("가게 주문 상태 변경을 실패했습니다.");
            });
        } else {
            return;
        }

    }

    return (
        <div className={"manage-main-container"}>
            <div className={"f-line m-scroll "}>
                <div>
                    <div className={"name-card "}>
                        <div className={"name-card-btn"}>
                            {
                                storeStatus.status ? (
                                    <div className={"order-close-btn"} onClick={storeStatusBtn}>
                                        <h5>종료</h5>
                                    </div>
                                ) : (
                                    <div className={"order-start-btn"} onClick={storeStatusBtn}>
                                        <h5>시작</h5>
                                    </div>
                                )
                            }
                        </div>
                    </div>
                    {
                        storeOrder.map((data, index) => (
                            <div
                                className={"name-card "} id={index + "-category"}
                                key={index}>
                                {/*{*/}
                                {/*    data.payStatus === 1 && data.status === 0 ? (*/}
                                {/*        <div className={"name-card-new"}></div>*/}
                                {/*    ) : (*/}
                                {/*        <></>*/}
                                {/*    )*/}
                                {/*}*/}
                                <div className={"name-card-btn"}>
                                    <div
                                        className={"name-card-part " + (query.get("f") === String(index) ? 'active' : '')}
                                        style={{
                                            flexDirection: "column"
                                        }}
                                        onClick={() => {
                                            navigate(`/manage/store?kind=orderStart&f=${index}&order=detail`)
                                        }}
                                    >
                                        <p>{data.storeQRId}</p>
                                        <p style={{
                                            fontSize: "15px"
                                        }}>{data.id}</p>
                                        <div className={"order-line"}></div>
                                    </div>
                                </div>
                                <div style={{
                                    width: "3%"
                                }}>
                                    {
                                        query.get("f") === String(index) ? (
                                            <div className={"name-card-active"}>
                                            </div>) : (
                                            <></>
                                        )
                                    }
                                </div>
                            </div>
                        ))
                    }
                </div>
            </div>
            {
                query.get("order") !== null & query.get("f") !== null ? (
                    <div
                        className={"l-line m-scroll m-70"}>
                        <div
                            className={"main-container2 m-100 animate__animated " + (query.get("f") !== null ? 'animate__slideInLeft' : '')}>
                            <StoreOrderDetail storeOrder={storeOrder[Number(query.get("f"))]}
                                              storeOrderList={storeOrder}
                                              setStoreOrder={setStoreOrder}/>
                        </div>
                    </div>
                ) : (
                    <></>
                )
            }
        </div>
    )
}
export default OrderStart;