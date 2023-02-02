import {ReactComponent as Basket} from "./../../../assets/icon/shopping-cart.svg";

let OrderFooter = () => {
    return (
        <div className={"order-container-footer"}>
            <div className={"order-footer-basket"}>
                <div className={"order-footer-basket-number"}>
                    <p>4</p>
                </div>
                <div className={"order-footer-basket-icon"}>
                    <Basket/>
                </div>
            </div>
            <div className={"order-footer-total-order-btn"}>
                <div className={"order-footer-btn"}>
                    <p>1000원 결제 및 주문하기</p>
                </div>
            </div>
        </div>
    )
}

export default OrderFooter;