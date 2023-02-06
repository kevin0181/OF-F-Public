import React from "react";
import {ReactComponent as XBtn} from "../../../assets/icon/x.svg";
import {useNavigate} from "react-router-dom";

let MenuBasketList = () => {
    const navigate = useNavigate();

    return (
        <div className={"order-container animate__animated animate__slideInUp"}>
            <div className={"menu-select-container"}>
                <div className={"menu-select-container-header"}>
                    <div className={"menu-select-x-btn"}>
                        <div onClick={() => {
                            navigate(-1);
                        }}>
                            <XBtn/>
                        </div>
                    </div>
                </div>
                <div className={"menu-select-container-body"}>
                </div>
                <div className={"menu-select-container-footer"}>
                </div>
            </div>
        </div>
    )
}

export default MenuBasketList;