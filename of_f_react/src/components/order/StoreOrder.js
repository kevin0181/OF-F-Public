import {useRecoilState} from "recoil";
import {orderStore as orderStoreRecoil} from "../../store/order/orderStore";
import {useEffect} from "react";

let StoreOrder = () => {

    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보

    useEffect(() => {
        console.log(orderStore)
    }, [orderStore])

    return (
        <>ㅁㄴㅇ</>
    )
}

export default StoreOrder;