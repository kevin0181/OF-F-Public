import {useRecoilState} from "recoil";
import {orderStore as orderStoreRecoil} from "../../../store/order/orderStore";
import {
    clickMenuStatus as clickMenuStatusRecoil,
    selectOrderMenu as selectOrderMenuRecoil
} from "../../../store/order/orderSelectMenu";

let SlideImg = () => {

    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보

    const [selectOrderMenu, setSelectOrderMenu] = useRecoilState(selectOrderMenuRecoil); //  현재 선택된 카테고리의 메뉴 가져옴
    return (
        <>
            {
                selectOrderMenu.storeMenuImgs.map((data, index) => (
                    <img key={index}
                         src={`${process.env.REACT_APP_SERVER_URL_PORT}/api/v1/img/get?name=${data.name}&kind=menu&store=${orderStore.name}`}
                    />
                ))
            }
        </>
    )
}

export default SlideImg;