import React, {useEffect} from "react";
import {ReactComponent as XBtn} from "../../../assets/icon/x.svg";
import {useNavigate, useParams} from "react-router-dom";
import "./../../../styles/css/order/basket.css";
import {useRecoilState} from "recoil";
import {order as orderRecoil} from "../../../store/order/order";
import {orderStore as orderStoreRecoil} from "../../../store/order/orderStore";
import {
    clickMenuStatus as clickMenuStatusRecoil,
    selectOrderMenu as selectOrderMenuRecoil
} from "../../../store/order/orderSelectMenu";
import MenuSelectComponent from "./MenuSelectComponent";

let MenuBasketList = () => {

    const navigate = useNavigate();
    let {storeId, qrId} = useParams();

    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보

    const [order, setOrder] = useRecoilState(orderRecoil); //  주문 목록(장바구니)
    const [selectOrderMenu, setSelectOrderMenu] = useRecoilState(selectOrderMenuRecoil); //  현재 선택된 메뉴 가져옴
    const [clickMenuStatus, setClickMenuStatus] = useRecoilState(clickMenuStatusRecoil); // 메뉴 선택시, 모달창 나옴

    useEffect(() => {
        if (order.storeOrderMenus.length === 0) {
            alert("주문 목록이 비어있습니다. 메뉴를 선택해주세요.");
            navigate(`/store/${storeId}/${qrId}/main`);
        }
    }, [order.storeOrderMenus]);

    let cancelOrderMenu = (cancelMenuDataIndex) => { //메뉴 주문 취소

        let newMenuData = order.storeOrderMenus.filter((data, index) => {
            return index !== cancelMenuDataIndex
        });

        setOrder({
            ...order,
            storeOrderMenus: newMenuData
        });
    }

    useEffect(() => {

        let price = 0;
        order.storeOrderMenus.map((data) => {
            price += data.price;
        });

        setOrder({
            ...order,
            totalPrice: price
        })


    }, [order.storeOrderMenus]); // 주문 목록에서 메뉴가 변경된다면?

    return (
        <>
            {
                clickMenuStatus ? (
                    <MenuSelectComponent/>
                ) : (<div className={"order-container animate__animated animate__slideInUp"}>
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
                            <div>
                                {
                                    order.storeOrderMenus.map((menuData, menuIndex) => (
                                        <div className={"basket-part"} key={menuIndex}>
                                            <div className={"basket-part-menu-title"}>
                                                <div className={"basket-part-menu-h3"}>
                                                    <h3>{menuData.storeMenu.name}</h3>
                                                </div>
                                                <div className={"basket-part-menu-XBtn"} onClick={() => {
                                                    cancelOrderMenu(menuIndex);
                                                }}>
                                                    <XBtn/>
                                                </div>
                                            </div>
                                            <div className={"basket-part-menu-body"}>
                                                <div className={"basket-part-img"}>
                                                    <div>
                                                        {
                                                            menuData.storeMenu.storeMenuImgs.length !== 0 ? (
                                                                <img
                                                                    src={`${process.env.REACT_APP_SERVER_URL_PORT}/api/v1/img/get?name=${menuData.storeMenu.storeMenuImgs[0].name}&kind=menu&store=${orderStore.name}`}/>
                                                            ) : (<></>)
                                                        }
                                                    </div>
                                                </div>
                                                <div className={"basket-part-menu-info"}>
                                                    <div className={"basket-part-menu-info-price"}>
                                                        &bull;<small>메뉴 가격
                                                        : {menuData.size * Number(menuData.storeMenu.price)}원</small>
                                                    </div>
                                                    {
                                                        menuData.storeOrderSides.map((sideData, sideIndex) => (
                                                            <div className={"basket-part-menu-info-side"}
                                                                 key={sideIndex}>
                                                                &bull;<small>사이드
                                                                : {sideData.storeSideMenu.name} - {sideData.storeSideMenu.price}원</small>
                                                            </div>
                                                        ))
                                                    }
                                                    <div className={"basket-part-menu-info-price"} style={{
                                                        color: "black",
                                                        fontWeight: "bolder"
                                                    }}>
                                                        &bull;<small>총 가격 : {menuData.price}원</small>
                                                    </div>
                                                    <div className={"basket-part-btn"}>
                                                        <div onClick={() => {
                                                            setSelectOrderMenu(menuData.storeMenu);
                                                            setClickMenuStatus(true);
                                                        }}>
                                                            변경
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    ))
                                }
                            </div>
                        </div>
                        <div className={"basket-list-container-footer"}>
                            <div>
                                <p>
                                    {order.totalPrice}원 결제 및 주문하기
                                </p>
                            </div>
                        </div>
                    </div>
                </div>)
            }
        </>
    )
}

export default MenuBasketList;