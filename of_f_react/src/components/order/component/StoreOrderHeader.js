import {Outlet} from "react-router-dom";
import "../../../styles/css/order/orderMain.css";
import {ReactComponent as Logo} from "./../../../assets/logo1.svg";


let StoreOrderHeader = () => {
    return (
        <div className={"order-container"}>
            <div className={"order-container-header"}>
                <div className={"order-header-logo"}>
                    <div className={"order-main-logo"}>
                        <Logo/>
                    </div>
                    <div className={"order-main-store-name"}>
                        <div>
                            가게 : 10231023123
                        </div>
                        <div>
                            QR : 1QR
                        </div>
                    </div>
                </div>
                <div className={"order-header-category"}>
                    <div>
                        <div className={"order-category-part"}>
                            <div>카테고리1</div>
                        </div>
                        <div className={"order-category-part"}>
                            <div>카테고리1</div>
                        </div>
                        <div className={"order-category-part"}>
                            <div>카테고리1</div>
                        </div>
                        <div className={"order-category-part"}>
                            <div>카테고리1</div>
                        </div>
                        <div className={"order-category-part"}>
                            <div>카테고리1</div>
                        </div>
                        <div className={"order-category-part"}>
                            <div>카테고리1</div>
                        </div>
                        <div className={"order-category-part"}>
                            <div>카테고리1</div>
                        </div>
                        <div className={"order-category-part"}>
                            <div>카테고리1</div>
                        </div>
                        <div className={"order-category-part"}>
                            <div>카테고리1</div>
                        </div>
                        <div className={"order-category-part"}>
                            <div>카테고리1</div>
                        </div>
                        <div className={"order-category-part"}>
                            <div>카테고리1</div>
                        </div>


                    </div>
                </div>
            </div>
            <Outlet/>
        </div>

    )
}

export default StoreOrderHeader;