import {Outlet, useNavigate, useParams} from "react-router-dom";
import "../../../styles/css/order/orderMain.css";
import {ReactComponent as Logo} from "./../../../assets/logo1.svg";
import {ReactComponent as XBtn} from "./../../../assets/icon/x.svg";
import {useRecoilState} from "recoil";
import {
    orderStore as orderStoreRecoil,
    orderStoreCategory as orderStoreCategoryRecoil,
    orderStoreSideCategory as orderStoreSideCategoryRecoil
} from "../../../store/order/orderStore";
import {useQuery} from "../../../Config/getQuery";
import {clickMenuStatus as clickMenuStatusRecoil} from "../../../store/order/orderSelectMenu";


let StoreOrderHeader = () => {

    const [clickMenuStatus, setClickMenuStatus] = useRecoilState(clickMenuStatusRecoil); // 메뉴 선택시, 모달창 나옴

    let query = useQuery();

    const navigate = useNavigate();

    let {storeId, qrId} = useParams();

    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보
    const [orderStoreCategory, setOrderStoreCategory] = useRecoilState(orderStoreCategoryRecoil); // 가게 정보 카테고리

    return (
        <>
            {
                clickMenuStatus ? (
                    <div className={"order-container"}>
                        <div className={"menu-select-container"}>
                            <div className={"menu-select-container-header"}>
                                <div className={"menu-select-x-btn"}>
                                    <div onClick={() => {
                                        setClickMenuStatus(false)
                                    }}>
                                        <XBtn/>
                                    </div>
                                </div>
                                <div className={"menu-select-img"}>

                                </div>
                            </div>
                            <div className={"menu-select-container-body"}>

                            </div>
                            <div className={"menu-select-container-footer"}>

                            </div>
                        </div>
                    </div>
                ) : (
                    <div className={"order-container"}>
                        <div className={"order-container-header"}>
                            <div className={"order-header-logo"}>
                                <div className={"order-main-logo"}>
                                    <Logo/>
                                </div>
                                <div className={"order-main-store-name"}>
                                    <div>
                                        가게 : {orderStore.name} &nbsp;&nbsp;
                                    </div>
                                    <div>
                                        QR : {qrId}
                                    </div>
                                </div>
                            </div>
                            <div className={"order-header-category"}>
                                <div>
                                    <div className={"order-category-part"}>
                                        <div className={query.get("category") === null ? 'active' : ''} onClick={() => {
                                            navigate(`/store/${storeId}/${qrId}/main`);
                                        }}>
                                            <div>전체</div>
                                        </div>
                                    </div>
                                    {
                                        orderStoreCategory.map((data, index) => (
                                            <div className={"order-category-part "} key={index}>
                                                <div
                                                    className={(query.get("category") === index.toString() ? 'active' : '')}
                                                    onClick={() => {
                                                        navigate(`/store/${storeId}/${qrId}/main?category=${index}`);
                                                    }}>
                                                    <div>{data.name}</div>
                                                </div>
                                            </div>
                                        ))
                                    }
                                </div>
                            </div>
                        </div>
                        <Outlet/>
                    </div>
                )
            }
        </>
    )
}

export default StoreOrderHeader;