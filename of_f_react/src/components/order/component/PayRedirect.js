import {useQuery} from "../../../Config/getQuery";
import {useEffect} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {useRecoilState} from "recoil";
import {order as orderRecoil} from "../../../store/order/order";
import {notTokenAxios} from "../../../Config/customAxios";

let PayRedirect = () => {

    const query = useQuery();

    let {storeId, qrId} = useParams();

    const navigate = useNavigate();

    const imp_uid = query.get("imp_uid");
    const merchant_uid = query.get("merchant_uid");
    const imp_success = query.get("imp_success");

    console.log(imp_uid, merchant_uid, imp_success)

    const [order, setOrder] = useRecoilState(orderRecoil); //  주문 목록(장바구니)

    useEffect(() => {

        if (imp_success) {
            // -> 검증 처리해야함

            notTokenAxios({
                url: "/api/v1/store/order/pay/after",
                method: "POST",
                data: {
                    storeId, qrId, imp_uid, merchant_uid
                }
            }).then(res => {
                console.log(res);
            }).catch(err => {
                console.log(err);
            })

        } else {
            // -> 결제 실패했으니깐 db데이터 삭제하기.
            alert("결제를 실패하였습니다.");
            navigate("/")
        }

    }, [])

    return (
        <></>
    )

}

export default PayRedirect;