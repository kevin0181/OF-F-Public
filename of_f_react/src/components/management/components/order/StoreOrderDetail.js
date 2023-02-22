import React, {useEffect} from "react";
import {useRecoilState} from "recoil";
import {selectStoreInfoRecoil} from "../../../../store/management/storeInfo";

let StoreOrderDetail = ({storeOrder}) => {

    const [store, setStore] = useRecoilState(selectStoreInfoRecoil); //선택된 가게 정보

    useEffect(() => {
        console.log(storeOrder)
    }, [storeOrder])

    return (
        <div>
            <div className={"main-container2-top"}>
                <h2>주문 상세보기</h2>
            </div>
            <div className={"main-container2-body"}>
                <div className={"order-detail-body"}>
                    {
                        storeOrder !== undefined ? (
                            <>
                                {
                                    storeOrder.storeOrderMenus.map((data, index) => (
                                        <div key={index}>
                                            <div>
                                                <div className={"order-detail-part"}>
                                                    <div className={"order-detail-img"}>
                                                        <img alt={"주문 메뉴 이미지"}
                                                             src={`${process.env.REACT_APP_SERVER_URL_PORT}/api/v1/img/get?name=${data.storeMenu.storeMenuImgs[0].name}&kind=menu&store=${store.name}`}/>
                                                    </div>
                                                    <div className={"order-detail-content"}>
                                                        <div className={"order-detail-main"}>
                                                            <div className={"order-detail-menu"}>
                                                                <div className={"order-detail-menu-part"}>
                                                                    <h4>메뉴 이름</h4>
                                                                    <h4>-</h4>
                                                                    <span>{data.storeMenu.name}&nbsp;({data.storeMenu.price}원)</span>
                                                                </div>
                                                            </div>
                                                            {
                                                                data.storeOrderSides.map((data, index) => (
                                                                    <div className={"order-detail-menu"} key={index}>
                                                                        <div className={"order-detail-side-part"}>
                                                                            <h4>SIDE &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h4>
                                                                            <span>{data.storeSideMenu.name}&nbsp;({data.storeSideMenu.price}원)</span>
                                                                        </div>
                                                                    </div>
                                                                ))
                                                            }
                                                            <div className={"order-detail-menu"} style={{
                                                                fontSize: "17px"
                                                            }}>
                                                                <div
                                                                    className={"order-detail-menu-part order-detail-size-part"}>
                                                                    <span>{data.size}개</span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <hr style={{
                                                margin: "8px 0px"
                                            }}/>
                                        </div>
                                    ))
                                }
                                <div className={"order-detail-totalPrice-part"}>
                                    <div>
                                        <h4>총 금액 : {storeOrder.totalPrice}원</h4>
                                    </div>
                                </div>
                            </>
                        ) : (
                            <></>
                        )
                    }
                </div>
            </div>
            <div className={"main-container2-footer"}>
                <div>
                    <div className={"main-btn-false m-f-1 position-center"}>
                        <div>
                            <p>주문 거절</p>
                        </div>
                    </div>
                    <div className={"main-btn-true m-f-1 position-center"}>
                        <div>
                            <p>주문 받기</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default StoreOrderDetail;