import React from "react";
import {ReactComponent as XBtn} from "../../../assets/icon/x.svg";
import {useNavigate} from "react-router-dom";
import "./../../../styles/css/order/basket.css";

let MenuBasketList = () => {
    const navigate = useNavigate();

    return (
        <div className={"order-container animate__animated animate__slideInUp"}>
            <div className={"menu-select-container"}>
                <div className={"basket-list-container-header"}>
                    <div className={"menu-select-x-btn"} style={{
                        height: "auto"
                    }}>
                        <div onClick={() => {
                            navigate(-1);
                        }}>
                            <XBtn/>
                        </div>
                    </div>
                    <div className={"basket-title"}>
                       <div>
                           <h3>주문 목록</h3>
                       </div>
                    </div>
                </div>
                <div className={"basket-list-container-body"}>
                </div>
                <div className={"basket-list-container-footer"}>
                </div>
            </div>
        </div>
    )
}

export default MenuBasketList;