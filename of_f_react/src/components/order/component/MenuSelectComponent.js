import {useRecoilState} from "recoil";
import {
    clickMenuStatus as clickMenuStatusRecoil,
    selectOrderMenu as selectOrderMenuRecoil
} from "../../../store/order/orderSelectMenu";
import {ReactComponent as XBtn} from "./../../../assets/icon/x.svg";
import {orderStore as orderStoreRecoil} from "../../../store/order/orderStore";

let MenuSelectComponent = () => {


    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보

    const [clickMenuStatus, setClickMenuStatus] = useRecoilState(clickMenuStatusRecoil); // 메뉴 선택시, 모달창 나옴

    const [selectOrderMenu, setSelectOrderMenu] = useRecoilState(selectOrderMenuRecoil); //  현재 선택된 카테고리의 메뉴 가져옴

    return (
        <div className={"order-container animate__animated animate__slideInRight"}>
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
                        <div>
                            {
                                selectOrderMenu.storeMenuImgs.map((data, index) => (
                                    <img key={index}
                                         src={`${process.env.REACT_APP_SERVER_URL_PORT}/api/v1/img/get?name=${data.name}&kind=menu&store=${orderStore.name}`}/>
                                ))
                            }
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

export default MenuSelectComponent;