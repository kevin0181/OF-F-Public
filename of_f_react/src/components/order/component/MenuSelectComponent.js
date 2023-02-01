import {useRecoilState} from "recoil";
import {clickMenuStatus as clickMenuStatusRecoil} from "../../../store/order/orderSelectMenu";
import {ReactComponent as XBtn} from "./../../../assets/icon/x.svg";

let MenuSelectComponent = () => {

    const [clickMenuStatus, setClickMenuStatus] = useRecoilState(clickMenuStatusRecoil); // 메뉴 선택시, 모달창 나옴

    return (
        <div className={"order-container animate__animated animate__slideInRight"}
             style={{backgroundColor: "red"}}>
            <div className={"menu-select-container"}>
                <div className={"menu-select-container-header"}>
                    <div className={"menu-select-x-btn"}>
                        <div onClick={() => {
                            setClickMenuStatus(false)
                        }}>
                            <XBtn/>
                        </div>
                    </div>
                    <div className={"menu-select-img"}>

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

export default MenuSelectComponent;