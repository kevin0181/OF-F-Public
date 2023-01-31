import {useNavigate, useParams} from "react-router-dom";
import {useEffect} from "react";
import {useRecoilState} from "recoil";
import {orderStore as orderStoreRecoil} from "../../store/order/orderStore";
import {notTokenAxios} from "../../Config/customAxios";

let OrderStoreCheck = () => {

    let navigate = useNavigate();
    let {storeId} = useParams();

    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 선택된 가게 id

    useEffect(() => {

        if (storeId === null)
            alert("존재하지 않는 가게 입니다.");

        if (isNaN(Number(storeId))) {
            alert("존재하지 않는 가게 정보입니다.");
            navigate("/store/search");
        }

        notTokenAxios({
            method: "get",
            url: `/api/v1/store/order/qr?storeSeq=${storeId}`,
        }).then(res => {

            if (res.data.data === null) {
                alert("존재하지 않는 가게 정보입니다.");
                navigate("/store/search");
            } else {
                setOrderStore(res.data.data);
            }

        }).catch(err => {
            console.log(err);
        })

    }, []);
}

export default OrderStoreCheck;