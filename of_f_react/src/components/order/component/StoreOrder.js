import {useRecoilState} from "recoil";
import {
    orderStore as orderStoreRecoil,
    orderStoreCategory as orderStoreCategoryRecoil, orderStoreSideCategory as orderStoreSideCategoryRecoil
} from "../../../store/order/orderStore";
import {useEffect} from "react";
import OrderFooter from "./OrderFooter";
import {useQuery} from "../../../Config/getQuery";
import {useNavigate, useParams} from "react-router-dom";
import {
    clickMenuStatus as clickMenuStatusRecoil,
    selectOrderMenu as selectOrderMenuRecoil
} from "../../../store/order/orderSelectMenu";
import React from "react";

let StoreOrder = () => {

    let query = useQuery();

    const navigate = useNavigate();

    let {storeId, qrId} = useParams();

    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보
    const [orderStoreCategory, setOrderStoreCategory] = useRecoilState(orderStoreCategoryRecoil); // 가게 정보 카테고리
    const [orderStoreSideCategory, setOrderStoreSideCategory] = useRecoilState(orderStoreSideCategoryRecoil); // 가게 정보 사이드 카테고리

    const [selectOrderMenu, setSelectOrderMenu] = useRecoilState(selectOrderMenuRecoil); //  현재 선택된 카테고리의 메뉴 가져옴
    const [clickMenuStatus, setClickMenuStatus] = useRecoilState(clickMenuStatusRecoil); // 메뉴 선택시, 모달창 나옴

    useEffect(() => {
        console.log(orderStore);
        console.log(orderStoreCategory)
        console.log(orderStoreSideCategory)
        console.log(selectOrderMenu);
        console.log(clickMenuStatus)
    }, [orderStore, selectOrderMenu])

    return (
        <>
            <div className={"order-container-body"}>
                <div>
                    {
                        query.get("category") === null ? (
                            <>
                                {
                                    orderStoreCategory.map((categoryData, index) => (
                                        <React.Fragment key={index}>
                                            {
                                                categoryData.storeMenus.map((menuData, index) => (
                                                    <div className={"order-body-menu"} key={index}>
                                                        {
                                                            menuData.storeMenuImgs[0] !== undefined ? (
                                                                <div className={"order-menu-top"} onClick={() => {
                                                                    setSelectOrderMenu(menuData);
                                                                    setClickMenuStatus(true);
                                                                }}>
                                                                    <img className={"order-menu-img"}
                                                                         src={`${process.env.REACT_APP_SERVER_URL_PORT}/api/v1/img/get?name=${menuData.storeMenuImgs[0].name}&kind=menu&store=${orderStore.name}`}
                                                                         alt={menuData.name}/>
                                                                </div>
                                                            ) : (
                                                                <></>
                                                            )
                                                        }
                                                        <div className={"order-menu-body"} onClick={() => {
                                                            setSelectOrderMenu(menuData);
                                                            setClickMenuStatus(true);
                                                        }}>
                                                            <h3>{menuData.name}</h3>
                                                        </div>
                                                        <div className={"order-menu-footer"}>
                                                            <small>{menuData.price}원</small>
                                                        </div>
                                                    </div>
                                                ))
                                            }
                                        </React.Fragment>
                                    ))
                                }
                            </>
                        ) : (
                            <>
                                {
                                    orderStoreCategory[Number(query.get("category"))] !== undefined ? (
                                        <>
                                            {
                                                orderStoreCategory[Number(query.get("category"))].storeMenus.map((menuData, index) => (
                                                    <div className={"order-body-menu"} key={index}>
                                                        {
                                                            menuData.storeMenuImgs[0] !== undefined ? (
                                                                <div className={"order-menu-top"} onClick={() => {
                                                                    setSelectOrderMenu(menuData);
                                                                    setClickMenuStatus(true);
                                                                }}>
                                                                    <img className={"order-menu-img"}
                                                                         src={`${process.env.REACT_APP_SERVER_URL_PORT}/api/v1/img/get?name=${menuData.storeMenuImgs[0].name}&kind=menu&store=test 가게`}
                                                                         alt={menuData.name}/>
                                                                </div>
                                                            ) : (
                                                                <></>
                                                            )
                                                        }
                                                        <div className={"order-menu-body"} onClick={() => {
                                                            setSelectOrderMenu(menuData);
                                                            setClickMenuStatus(true);
                                                        }}>
                                                            <h3>{menuData.name}</h3>
                                                        </div>
                                                        <div className={"order-menu-footer"}>
                                                            <small>{menuData.price}원</small>
                                                        </div>
                                                    </div>
                                                ))
                                            }
                                        </>
                                    ) : (<></>)
                                }
                            </>
                        )
                    }
                </div>
            </div>
            <OrderFooter/>
        </>
    )
}

export default StoreOrder;