import {useQuery} from "../../../Config/getQuery";
import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import "./../../../styles/css/management/main.css";
import "./../../../styles/css/management/category.css";
import "./../../../styles/css/management/order.css";
import "animate.css";
import {tokenStoreAdminAxios} from "../../../Config/customStoreAdminAjax";
import {useRecoilState} from "recoil";
import {selectStoreInfoRecoil} from "../../../store/management/storeInfo";

let OrderStart = () => {
    const query = useQuery();

    const navigate = useNavigate();

    const [store, setStore] = useRecoilState(selectStoreInfoRecoil); //선택된 가게 정보
    const [order, setOrder] = useState([
        // {
        //     seq: "",
        //     cancelAfterPrice: "",
        //     comment: "",
        //     date: "",
        //     email: "",
        //     emailReceiveStatus: false,
        //     id: "",
        //     kind: "",
        //     orderNumber: "",
        //     payStatus: "",
        //     phoneNumber: "",
        //     phoneNumberReceiveStatus: false,
        //     place: "",
        //     status: "",
        //     storeOrderMenus: [],
        //     storeOrderPgInfo: {},
        //     storeOrderVanInfo: {},
        //     storeQRId: "",
        //     storeSeq: "",
        //     totalPrice: "",
        //     user: {}
        // }
    ])

    // node -> 서버에서 실시간으로 현재 가게 정보 가져오기
    // spring -> 서버에서 이미 들어온 주문 가져오기
    useEffect(() => {
        if (store.seq !== undefined && store.storeOrders === null) {
            getOrderData();
        }
    }, [store]);

    useEffect(() => {
        if (store.storeOrders !== undefined) {
            setOrder(store.storeOrders);
        }
    }, [])

    let getOrderData = () => {
        tokenStoreAdminAxios({
            url: "/api/v1/store/admin/get/order?storeSeq=" + store.seq,
            method: "GET"
        }).then(res => {
            console.log(res)
            setStore({
                ...store,
                storeOrders: res.data.data
            });
            setOrder(
                res.data.data
            )
        }).catch(err => {
            console.log(err);
        })
    }

    return (
        <div className={"manage-main-container"}>
            <div className={"f-line m-scroll "}>
                <div>
                    <div className={"name-card "}>
                        <div className={"name-card-btn"}>
                            {/*<div className={"order-start-btn"}>*/}
                            {/*    <h5>시작</h5>*/}
                            {/*</div>*/}
                            <div className={"order-close-btn"}>
                                <h5>종료</h5>
                            </div>
                        </div>
                    </div>
                    {
                        order.map((data, index) => (
                            <div
                                className={"name-card "} id={index + "-category"}
                                key={index}>
                                <div className={"name-card-btn"}>
                                    <div
                                        className={"name-card-part " + (query.get("f") === String(index) ? 'active' : '')}
                                        style={{
                                            flexDirection: "column"
                                        }}
                                        onClick={() => {
                                            navigate("/manage/store?kind=Category&f=" + index)
                                        }}
                                    >
                                        <p>{data.storeQRId}</p>
                                        <p style={{
                                            fontSize: "10px"
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
            <div
                className={"l-line m-scroll m-70"}>
                <div
                    className={"main-container2 m-100 animate__animated " + (query.get("f") !== null ? 'animate__slideInLeft' : '')}>
                    <div>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default OrderStart;