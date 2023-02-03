import {useRecoilState} from "recoil";
import {
    clickMenuStatus as clickMenuStatusRecoil,
    selectOrderMenu as selectOrderMenuRecoil
} from "../../../store/order/orderSelectMenu";
import {ReactComponent as XBtn} from "./../../../assets/icon/x.svg";
import {ReactComponent as PlusBtn} from "./../../../assets/icon/plus.svg";
import {ReactComponent as CheckBtn} from "./../../../assets/icon/check.svg";
import {ReactComponent as MinusBtn} from "./../../../assets/icon/minus.svg";
import {orderStore as orderStoreRecoil} from "../../../store/order/orderStore";
import {useEffect, useState} from "react";
import React from "react";
import {order as orderRecoil, orderMenu as orderMenuRecoil} from "../../../store/order/order";

let MenuSelectComponent = () => {


    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보
    const [clickMenuStatus, setClickMenuStatus] = useRecoilState(clickMenuStatusRecoil); // 메뉴 선택시, 모달창 나옴
    const [selectOrderMenu, setSelectOrderMenu] = useRecoilState(selectOrderMenuRecoil); //  현재 선택된 카테고리의 메뉴 가져옴

    const [order, setOrder] = useRecoilState(orderRecoil); //  주문 목록(장바구니)
    const [orderMenu, setOrderMenu] = useRecoilState(orderMenuRecoil); //  주문 목록(장바구니)

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
        }, 4000);
        return (
            setImgMoveStyle(
                {
                    transform: `translate(-${imgCurrent}00%)`
                }
            )
        )
    }, [imgCurrent]);

    useEffect(() => {
        console.log(selectOrderMenu);
    }, [selectOrderMenu])

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
                        {
                            selectOrderMenu.storeMenuImgs.length !== 0 ? (
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
                            ) : (<div>
                                이 메뉴는 이미지가 존재하지 않습니다.
                            </div>)
                        }
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
                        <div className={"menu-select-body-center"}>
                            {
                                selectOrderMenu.storeMSs.map((data, index) => (
                                    <React.Fragment key={index}>
                                        <div className={"menu-select-side-name"}>{data.storeSideCategory.name}</div>
                                        {
                                            data.storeSideCategory.storeSideMenus.map((data, index) => (
                                                <div className={"menu-select-side-part"} key={index}>
                                                    <div className={"menu-select-side"}>
                                                        <div>
                                                            <div>{data.name}</div>
                                                            <div style={{
                                                                fontSize: "13px"
                                                            }}>1000원
                                                            </div>
                                                        </div>
                                                        <div className={"menu-select-side-number"}>
                                                            <div className={"number-btn"}>
                                                                <PlusBtn/>
                                                            </div>
                                                            {/*<div className={"number-btn number-btn-active"}>*/}
                                                            {/*    <CheckBtn/>*/}
                                                            {/*</div>*/}
                                                        </div>
                                                    </div>
                                                </div>
                                            ))
                                        }
                                    </React.Fragment>
                                ))
                            }
                        </div>
                        <div className={"menu-select-body-bottom"}>
                            <div>
                                <div className={"menu-select-count-p"}>
                                    <h3>메뉴 수량 : </h3>
                                </div>
                                <div className={"menu-select-side-number"}>
                                    <div className={"number-btn"}>
                                        <PlusBtn/>
                                    </div>
                                    <div style={{
                                        padding: "10px"
                                    }}>
                                        0
                                    </div>
                                    <div className={"number-btn"}>
                                        <MinusBtn/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className={"menu-select-container-footer"}>
                    <div>
                        <div className={"menu-select-side-btn"}>
                            <p>1000원 담기</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default MenuSelectComponent;