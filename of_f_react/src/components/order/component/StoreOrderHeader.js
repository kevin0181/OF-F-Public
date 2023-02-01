import {Outlet} from "react-router-dom";
import "../../../styles/css/order/orderMain.css";

let StoreOrderHeader = () => {
    return (
        <div className={"order-container"}>
            <div className={"order-container-header"}>

            </div>
            <Outlet/>
        </div>

    )
}

export default StoreOrderHeader;