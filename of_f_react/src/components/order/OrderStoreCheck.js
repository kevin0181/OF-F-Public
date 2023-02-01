import {Outlet, useNavigate, useParams} from "react-router-dom";
import {useEffect} from "react";
import {useRecoilState} from "recoil";
import {orderStore as orderStoreRecoil} from "../../store/order/orderStore";
import {notTokenAxios} from "../../Config/customAxios";

let OrderStoreCheck = () => {

    let navigate = useNavigate();
    let {storeId, qrId} = useParams();

    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보

    useEffect(() => {

        if (storeId === null) {
            alert("존재하지 않는 가게 입니다.");
            navigate("/store/search");
        }

        if (isNaN(Number(storeId))) {
            alert("존재하지 않는 가게 정보입니다.");
            navigate("/store/search");
        }

        if (qrId === null) {
            alert("존재하지 않는 QR 정보입니다.");
            navigate("/store/search");
        }

        notTokenAxios({
            method: "get",
            url: `/api/v1/store/order/qr?storeSeq=${storeId}&qrId=${qrId}`,
        }).then(res => {

            if (res.data.data === null) {
                alert("존재하지 않는 가게 정보입니다.");
                navigate("/store/search");
            } else {
                setOrderStore(res.data.data);
                navigate(`/store/${storeId}/${qrId}/main`);
            }

        }).catch(err => {
            console.log(err);
            alert("존재하지 않는 QR 및 가게입니다.");
            navigate("/store/search");
        })

    }, []);
    return (
        <Outlet/>
    )
}

export default OrderStoreCheck;