import {useQuery} from "../../../Config/getQuery";
import {useEffect} from "react";
import {useNavigate} from "react-router-dom";
import {useRecoilState} from "recoil";
import {order as orderRecoil} from "../../../store/order/order";

let PayRedirect = () => {

    const query = useQuery();

    const navigate = useNavigate();

    const imp_uid = query.get("imp_uid");
    const merchant_uid = query.get("merchant_uid");
    const imp_success = query.get("imp_success");

    console.log(imp_uid, merchant_uid, imp_success)

    const [order, setOrder] = useRecoilState(orderRecoil); //  주문 목록(장바구니)

    useEffect(() => {

        if (imp_success) {

        } else {
            alert("결제를 실패하였습니다.");
            navigate("/")
        }
    }, [])

    return (
        <></>
    )

}

export default PayRedirect;