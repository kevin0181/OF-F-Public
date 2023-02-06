import {ReactComponent as Basket} from "./../../../assets/icon/shopping-cart.svg";
import {useRecoilState} from "recoil";
import {order as orderRecoil} from "../../../store/order/order";

let OrderFooter = () => {
    const [order, setOrder] = useRecoilState(orderRecoil); //  주문 목록(장바구니)

    return (
        <div className={"order-container-footer"}>
            <div className={"order-footer-basket"}>
                <div className={"order-footer-basket-number"}>
                    <p>{order.storeOrderMenus.length}</p>
                </div>
                <div className={"order-footer-basket-icon"}>
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