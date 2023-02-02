import {useRecoilState} from "recoil";
import {
    clickMenuStatus as clickMenuStatusRecoil,
    selectOrderMenu as selectOrderMenuRecoil
} from "../../../store/order/orderSelectMenu";
import {ReactComponent as XBtn} from "./../../../assets/icon/x.svg";
import {orderStore as orderStoreRecoil} from "../../../store/order/orderStore";
import {useEffect, useState} from "react";

let MenuSelectComponent = () => {


    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보

    const [clickMenuStatus, setClickMenuStatus] = useRecoilState(clickMenuStatusRecoil); // 메뉴 선택시, 모달창 나옴

    const [selectOrderMenu, setSelectOrderMenu] = useRecoilState(selectOrderMenuRecoil); //  현재 선택된 카테고리의 메뉴 가져옴

    const [imgCurrent, setImgCurrent] = useState(0); //이미지 번호
    const [imgMoveStyle, setImgMoveStyle] = useState({
        transform: `translate(-${imgCurrent}00%)`
    })

    useEffect(() => {
        setTimeout(() => {
            if (selectOrderMenu.storeMenuImgs.length - 1 <= imgCurrent) {
                setImgCurrent(0);
            } else {
                setImgCurrent(imgCurrent + 1);
            }
            console.log(imgCurrent)
        }, 4000);
        return (
            setImgMoveStyle(
                {
                    transform: `translate(-${imgCurrent}00%)`
                }
            )
        )
    }, [imgCurrent]);

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
                        <div style={{
                            width: "78%",
                            height: "90%",
                            overflow: "hidden"
                        }}>
                            <div style={imgMoveStyle}>
                                {
                                    selectOrderMenu.storeMenuImgs.map((data, index) => (
                                        <img key={index}
                                             src={`${process.env.REACT_APP_SERVER_URL_PORT}/api/v1/img/get?name=${data.name}&kind=menu&store=${orderStore.name}`}
                                        />
                                    ))
                                }
                            </div>
                        </div>
                    </div>
                </div>
                <div className={"menu-select-container-body"}>
                    <div>
                        <div className={"menu-select-body-top"}>
                            <div className={"menu-select-name"}>
                                <h3>{selectOrderMenu.name}</h3>
                                <div className={"menu-select-menu-small"}>
                                    {/*<p>이 음식은 가나다라마바사가나다라마바사*/}
                                    {/*    가나다라마바사가나다라마바사가나다라마바사가나다라마바사가나다라마바사가나다라마바사가나다라마바사</p>*/}
                                </div>
                            </div>
                            <div className={"menu-select-price"}>
                                <div>
                                    <h3>가격 : </h3>
                                    <h3>{selectOrderMenu.price}원</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className={"menu-select-container-footer"}>

                </div>
            </div>
        </div>
    )
}

export default MenuSelectComponent;