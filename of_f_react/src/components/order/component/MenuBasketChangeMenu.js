import {useRecoilState, useResetRecoilState} from "recoil";
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

let MenuBasketChangeMenu = () => {


    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보
    const [clickMenuStatus, setClickMenuStatus] = useRecoilState(clickMenuStatusRecoil); // 메뉴 선택시, 모달창 나옴
    const [selectOrderMenu, setSelectOrderMenu] = useRecoilState(selectOrderMenuRecoil); //  현재 선택된 메뉴 가져옴

    const [imgCurrent, setImgCurrent] = useState(0); //이미지 번호
    const [imgMoveStyle, setImgMoveStyle] = useState({
        transform: `translate(-${imgCurrent}00%)`
    })

    const [order, setOrder] = useRecoilState(orderRecoil); //  전체 주문
    const [orderMenu, setOrderMenu] = useRecoilState(orderMenuRecoil); //  주문 목록(장바구니)
    const resetOrderMenu = useResetRecoilState(orderMenuRecoil);

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
        let price = 0;

        orderMenu.storeOrderSides.map((data, index) => {
            price += Number(data.storeSideMenu.price) * orderMenu.size;
        });

        setOrderMenu({
            ...orderMenu,
            price: (orderMenu.size * Number(orderMenu.storeMenu.price)) + price
        })

    }, [orderMenu.storeOrderSides, orderMenu.size]); // => 메뉴 사이드 선택시 가격 변경 or 주문 메뉴 사이즈가 변경됐을경우


    useEffect(() => {

        let checkBasketMenu = order.storeOrderMenus.filter(data => {
            return selectOrderMenu.seq === data.storeMenuSeq
        })

        if (checkBasketMenu.length !== 0) {
            setOrderMenu(checkBasketMenu[0]);
        } else {
            setOrderMenu({
                ...orderMenu,
                storeMenuSeq: selectOrderMenu.seq,
                storeMenu: selectOrderMenu,
                price: Number(selectOrderMenu.price)
            });
        }

    }, []);

    let onClickAddSideMenu = (sideData) => {

        setOrderMenu({
            ...orderMenu,
            storeOrderSides: [
                ...orderMenu.storeOrderSides,
                {
                    seq: "",
                    storeSideMenuSeq: sideData.seq,
                    storeOrderMenuSeq: "",
                    storeSideMenu: sideData
                }
            ]
        });

    }

    let onClickRemoveSideMenu = (sideData) => {

        let newSideData = orderMenu.storeOrderSides.filter(data => {
            return data.storeSideMenuSeq !== sideData.seq;
        })

        setOrderMenu({
            ...orderMenu,
            storeOrderSides: newSideData
        });

    }

    let onClickInputBasketMenu = () => {


        let checkBasketMenu = order.storeOrderMenus.filter(data => {
            return selectOrderMenu.seq === data.storeMenuSeq
        })

        if (checkBasketMenu.length !== 0) { //이미 장바구니에 존재하면?

            let newBasketMenuData = order.storeOrderMenus.filter(data => {
                return selectOrderMenu.seq !== data.storeMenuSeq;
            });

            let price = 0;
            newBasketMenuData.map(data => {
                price += data.price;
            })

            setOrder({
                ...order,
                storeOrderMenus: [
                    ...newBasketMenuData,
                    orderMenu
                ],
                totalPrice: price + orderMenu.price
            });

        } else { // 새로 추가

            setOrder({
                ...order,
                storeOrderMenus: [
                    ...order.storeOrderMenus,
                    orderMenu
                ],
                totalPrice: Number(order.totalPrice) + orderMenu.price
            });

        }


        resetOrderMenu();
        setClickMenuStatus(false);

    }

    return (
        <div className={"order-container animate__animated animate__slideInRight"}>
            <div className={"menu-select-container"}>
                <div className={"menu-select-container-header"}>
                    <div className={"menu-select-x-btn"}>
                        <div onClick={() => {
                            resetOrderMenu();
                            setClickMenuStatus(false)
                        }}>
                            <XBtn/>
                        </div>
                    </div>
                    <div className={"menu-select-img"}>
                        {
                            selectOrderMenu.storeMenuImgs.length !== 0 ? (
                                <div style={{
                                    width: "60%",
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
                        {
                            selectOrderMenu.storeMSs.length !== 0 ? (
                                <div className={"menu-select-body-center"}>
                                    {
                                        selectOrderMenu.storeMSs.map((data, index) => (
                                            <React.Fragment key={index}>
                                                <div
                                                    className={"menu-select-side-name"}>{data.storeSideCategory.name}</div>
                                                {
                                                    data.storeSideCategory.storeSideMenus.map((data, index) => (
                                                        <div className={"menu-select-side-part"} key={index}>
                                                            <div className={"menu-select-side"}>
                                                                <div>
                                                                    <div>{data.name}</div>
                                                                    <div style={{
                                                                        fontSize: "13px"
                                                                    }}>{data.price}원
                                                                    </div>
                                                                </div>
                                                                <div className={"menu-select-side-number"}>
                                                                    <div
                                                                        className={"number-btn " + (
                                                                            orderMenu.storeOrderSides.filter(sideData => {
                                                                                return sideData.storeSideMenuSeq === data.seq
                                                                            }).length === 0 ? "" : "number-btn-hidden"
                                                                        )}
                                                                        onClick={() => {
                                                                            onClickAddSideMenu(data)
                                                                        }}>
                                                                        <PlusBtn/>
                                                                    </div>
                                                                    <div className={"number-btn number-btn-active " + (
                                                                        orderMenu.storeOrderSides.filter(sideData => {
                                                                            return sideData.storeSideMenuSeq === data.seq
                                                                        }).length === 0 ? "number-btn-hidden" : ""
                                                                    )}
                                                                         onClick={() => {
                                                                             onClickRemoveSideMenu(data)
                                                                         }}>
                                                                        <CheckBtn/>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    ))
                                                }
                                            </React.Fragment>
                                        ))
                                    }
                                </div>
                            ) : (<></>)
                        }
                        <div className={"menu-select-body-bottom"}>
                            <div>
                                <div className={"menu-select-count-p"}>
                                    <h3>메뉴 수량 : </h3>
                                </div>
                                <div className={"menu-select-side-number"}>
                                    <div className={"number-btn"} onClick={() => {
                                        setOrderMenu({
                                            ...orderMenu,
                                            size: orderMenu.size + 1
                                        })
                                    }}>
                                        <PlusBtn/>
                                    </div>
                                    <div style={{
                                        padding: "10px"
                                    }}>
                                        {orderMenu.size}
                                    </div>
                                    <div className={"number-btn"} onClick={() => {

                                        if (orderMenu.size === 1) {
                                            alert("주문 수량은 1개 이상이어야합니다.");
                                            return;
                                        }
                                        setOrderMenu({
                                            ...orderMenu,
                                            size: orderMenu.size - 1
                                        });

                                    }}>
                                        <MinusBtn/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className={"menu-select-container-footer"}>
                    <div>
                        <div className={"menu-select-side-btn"} onClick={onClickInputBasketMenu}>
                            <p>{orderMenu.price}원 변경하기</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default MenuBasketChangeMenu;