import {ReactComponent as Basket} from "./../../../assets/icon/shopping-cart.svg";
import {useRecoilState} from "recoil";
import {order as orderRecoil} from "../../../store/order/order";
import {useNavigate, useParams} from "react-router-dom";

let OrderFooter = () => {

    const navigate = useNavigate();

    let {storeId, qrId} = useParams();

    const [order, setOrder] = useRecoilState(orderRecoil); //  주문 목록(장바구니)

    return (
        <div className={"order-container-footer"}>
            <div className={"order-footer-basket"}>
                <div className={"order-footer-basket-number"}>
                    <p>{order.storeOrderMenus.length}</p>
                </div>
                <div className={"order-footer-basket-icon"} onClick={() => {
                    if (order.storeOrderMenus.length === 0) {
                        alert("주문 목록이 비어있습니다.");
                        return;
                    } else {
                        navigate(`/store/${storeId}/${qrId}/basket`);
                    }
                }}>
                    <Basket/>
                </div>
            </div>
            <div className={"order-footer-total-order-btn"}>
                <div className={"order-footer-btn"}>
                    <p>{order.totalPrice}원 결제 및 주문하기</p>
                </div>
            </div>
        </div>
    )
}

export default OrderFooter;