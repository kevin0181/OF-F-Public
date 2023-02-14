import {useQuery} from "../../../Config/getQuery";
import {useEffect} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {notTokenAxios} from "../../../Config/customAxios";
import io from 'socket.io-client';


let PayRedirect = () => {

    const query = useQuery();

    let {storeId, qrId} = useParams();

    const navigate = useNavigate();

    const imp_uid = query.get("imp_uid");
    const merchant_uid = query.get("merchant_uid");
    const imp_success = query.get("imp_success");


    const socket = io('http://localhost:8000/store');

    useEffect(() => {

        socket.emit("insert room", "1");

        socket.emit('message', 'Hello, room!');

        socket.on("message2", (data) => {
            console.log(data);
            console.log(1);
        })

        if (imp_success === true) {
            // -> 검증 처리해야함
            notTokenAxios({
                url: "/api/v1/store/order/pay/after",
                method: "POST",
                data: {
                    storeId, qrId, imp_uid, merchant_uid
                }
            }).then(res => {
                console.log(res);
                switch (res.data.data.storeOrderPgInfo.status) {
                    case "paid":
                        alert("결제가 완료되었습니다.");
                        //서버에서 받은 결제완료 정보를토대로 알림창 발송 및 관리자 websocket으로 전송하기.
                        navigate(`/store/${storeId}/${qrId}/main`)
                        return;
                    case "ready":
                        alert("가상계좌 발급이 완료되었습니다.")
                        navigate(`/store/${storeId}/${qrId}/main`)
                        return;
                    default:
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
                        return;
                }
            }).catch(err => {
                console.log(err);
                navigate(0);
            })

        } else {
            // // -> 결제 실패했으니깐 db데이터 삭제하기.
            // notTokenAxios({
            //     url: "/api/v1/store/order/pay/fail/delete?merchantUid=" + merchant_uid,
            //     method: "POST",
            // }).then(res => {
            //     alert("결제를 실패하였습니다.");
            //     navigate(`/store/${storeId}/${qrId}/main`)
            // }).catch(err => {
            //     alert("결제를 실패하였습니다.");
            //     navigate(`/store/${storeId}/${qrId}/main`)
            // });
        }
    }, [])

    return (
        <></>
    )

}

export default PayRedirect;