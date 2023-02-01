import {useRecoilState} from "recoil";
import {
    orderStore as orderStoreRecoil,
    orderStoreCategory as orderStoreCategoryRecoil, orderStoreSideCategory as orderStoreSideCategoryRecoil
} from "../../../store/order/orderStore";
import {useEffect} from "react";
import OrderFooter from "./OrderFooter";

let StoreOrder = () => {

    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보
    const [orderStoreCategory, setOrderStoreCategory] = useRecoilState(orderStoreCategoryRecoil); // 가게 정보 카테고리
    const [orderStoreSideCategory, setOrderStoreSideCategory] = useRecoilState(orderStoreSideCategoryRecoil); // 가게 정보 사이드 카테고리

    useEffect(() => {
        console.log(orderStore);
        console.log(orderStoreCategory)
        console.log(orderStoreSideCategory)
    }, [orderStore])

    return (
        <>
            <div className={"order-container-body"}>
            </div>
            <OrderFooter/>
        </>
    )
}

export default StoreOrder;