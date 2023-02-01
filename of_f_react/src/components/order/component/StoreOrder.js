import {useRecoilState} from "recoil";
import {
    orderStore as orderStoreRecoil,
    orderStoreCategory as orderStoreCategoryRecoil, orderStoreSideCategory as orderStoreSideCategoryRecoil
} from "../../../store/order/orderStore";
import {useEffect} from "react";
import OrderFooter from "./OrderFooter";
import {useQuery} from "../../../Config/getQuery";
import {useNavigate, useParams} from "react-router-dom";
import {orderMenu as orderMenuRecoil} from "../../../store/order/orderSelectMenu";

let StoreOrder = () => {

    let query = useQuery();

    const navigate = useNavigate();

    let {storeId, qrId} = useParams();

    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보
    const [orderStoreCategory, setOrderStoreCategory] = useRecoilState(orderStoreCategoryRecoil); // 가게 정보 카테고리
    const [orderStoreSideCategory, setOrderStoreSideCategory] = useRecoilState(orderStoreSideCategoryRecoil); // 가게 정보 사이드 카테고리

    const [orderMenu, setOrderMenu] = useRecoilState(orderMenuRecoil); // 선택된 카테고리의 메뉴 가져옴

    useEffect(() => {
        console.log(orderStore);
        console.log(orderStoreCategory)
        console.log(orderStoreSideCategory)
    }, [orderStore])

    return (
        <>
            <div className={"order-container-body"}>
                <div>
                    {
                        orderStoreCategory[Number(query.get("category"))].storeMenus.map((data, index) => (
                            <div className={"order-body-menu"} key={index}>
                                <div className={"order-menu-top"}>
                                    <img className={"order-menu-img"} src={""} alt={data.name}/>
                                </div>
                                <div className={"order-menu-body"}>
                                    <h4>메뉴이름</h4>
                                </div>
                                <div className={"order-menu-footer"}>
                                    <small>10000원</small>
                                </div>
                            </div>
                        ))
                    }
                </div>
            </div>
            <OrderFooter/>
        </>
    )
}

export default StoreOrder;