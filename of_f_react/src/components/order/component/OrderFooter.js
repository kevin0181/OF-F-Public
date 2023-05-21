import {ReactComponent as Basket} from "./../../../assets/icon/shopping-cart.svg";
import {useRecoilState} from "recoil";
import {order as orderRecoil} from "../../../store/order/order";
import {useNavigate, useParams} from "react-router-dom";
import {orderStatus as orderStatusRecoil} from "../../../store/order/orderStore";
import {useEffect} from "react";

let OrderFooter = () => {

    const navigate = useNavigate();

    let {storeId, qrId} = useParams();

    const [order, setOrder] = useRecoilState(orderRecoil); //  주문 목록(장바구니)

    const [orderStatus, setOrderStatus] = useRecoilState(orderStatusRecoil); // 가게 정보 사이드 카테고리

    let basketNavigate = () => {
        if (order.storeOrderMenus.length === 0) {
            alert("주문 목록이 비어있습니다.");
            return;
        } else {
            navigate(`/store/${storeId}/${qrId}/basket`);
        }
    }

    return (
        <div className={"order-container-footer"}>
            <div className={"order-footer-basket"}>
                <div className={"order-footer-basket-number"}>
                    <p>{order.storeOrderMenus.length}</p>
                </div>
                <div className={"order-footer-basket-icon"} onClick={basketNavigate}>
                    <Basket/>
                </div>
            </div>
            <div className={"order-footer-total-order-btn"}>
                {
                    orderStatus.status ? (<div className={"order-footer-btn"} onClick={basketNavigate}>
                        <p>{order.totalPrice}원 결제 및 주문하기</p>
                    </div>) : (
                        <div className={"order-footer-btn"}>
                            <p>가게 오픈 준비중</p>
                        </div>
                    )
                }
            </div>
        </div>
    )
}

export default OrderFooter;