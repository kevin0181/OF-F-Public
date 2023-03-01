import {useQuery} from "../../../Config/getQuery";
import {useContext, useEffect} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {notTokenAxios} from "../../../Config/customAxios";
import socketIoClient from "socket.io-client";

let PayRedirect = () => {

    const query = useQuery();

    let {storeId, qrId} = useParams();

    const navigate = useNavigate();

    const imp_uid = query.get("imp_uid");
    const merchant_uid = query.get("merchant_uid");
    const imp_success = query.get("imp_success");

    useEffect(() => {

        const socket = socketIoClient(`${process.env.REACT_APP_NODE_SERVER_URL_PORT}/store`);
        socket.emit("insert room", storeId); // websocket 방참가

        if (imp_success === "true") {
            // -> 검증 처리해야함
            notTokenAxios({
                url: "/api/v1/store/order/pay/after",
                method: "POST",
                data: {
                    storeId, qrId, imp_uid, merchant_uid
                }
            }).then(res => {

                if (res.data.data.storeOrderPgInfo.status === "paid") {
                    //서버에서 받은 결제완료 정보를토대로 알림창 발송 및 관리자 websocket으로 전송하기.
                    socket.emit("room send", res.data.data, storeId);
                } else {
                    alert("결제를 실패하였습니다.");
                    navigate(0);
                }

                // switch (res.data.data.storeOrderPgInfo.status) {
                //     case "paid":
                //         alert("결제가 완료되었습니다.");
                //         navigate(`/store/${storeId}/${qrId}/main`)
                //         return;
                //     case "ready":
                //         alert("가상계좌 발급이 완료되었습니다.")
                //         navigate(`/store/${storeId}/${qrId}/main`)
                //         return;
                //     default:
                //         //주문 실패시 데이터 삭제하지말고 미결제로 보관하기
                //         // notTokenAxios({
                //         //     url: "/api/v1/store/order/pay/fail/delete?merchantUid=" + merchant_uid,
                //         //     method: "POST",
                //         // }).then(res => {
                //         //     alert("결제를 실패하였습니다.");
                //         //     navigate(`/store/${storeId}/${qrId}/main`)
                //         // }).catch(err => {
                //         //     alert("결제를 실패하였습니다.");
                //         //     navigate(`/store/${storeId}/${qrId}/main`)
                //         // });
                //         // return;
                // }
            }).catch(err => {
                console.log(err);
                navigate(0);
            }).finally(() => {
                navigate(`/store/${storeId}/${qrId}/main`);  //보여지는 주문 완료 페이지로 보내주는것도 나쁘지 않을지도??
            })

        } else {
            // -> 결제 실패했으니깐 db데이터 삭제하기.
            notTokenAxios({
                url: "/api/v1/store/order/pay/fail/delete?merchantUid=" + merchant_uid,
                method: "POST",
            }).then(res => {
                alert("결제를 실패하였습니다.");
                navigate(`/store/${storeId}/${qrId}/main`)
            }).catch(err => {
                alert("결제를 실패하였습니다.");
                navigate(`/store/${storeId}/${qrId}/main`)
            });
        }
    }, [])

    return (
        <></>
    )

}

export default PayRedirect;