import {useRecoilState} from "recoil";
import {orderStore as orderStoreRecoil} from "../../../store/order/orderStore";
import {useEffect} from "react";
import OrderFooter from "./OrderFooter";

let StoreOrder = () => {

    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보

    useEffect(() => {
        console.log(orderStore)
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