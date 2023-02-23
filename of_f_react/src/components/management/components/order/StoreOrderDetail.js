import React, {useEffect, useState} from "react";
import {useRecoilState} from "recoil";
import {selectStoreInfoRecoil} from "../../../../store/management/storeInfo";
import {ReactComponent as CheckCircle} from "./../../../../assets/icon/check-circle.svg";
import {tokenStoreAdminAxios} from "../../../../Config/customStoreAdminAjax";
import {useQuery} from "../../../../Config/getQuery";

let StoreOrderDetail = ({storeOrder, setStoreOrder, storeOrderList}) => {

    const [store, setStore] = useRecoilState(selectStoreInfoRecoil); //선택된 가게 정보
    const query = useQuery();

    let orderStatusOnClickGetOrder = (statusId) => { //주문 받기, 주문 완료

        let status = false;

        switch (statusId) {
            case 1:
                // eslint-disable-next-line no-restricted-globals
                status = confirm("주문을 받으시겠습니까?");
                break;
            case 2:
                // eslint-disable-next-line no-restricted-globals
                status = confirm("주문을 완료하시겠습니까?");
                break;
            default:
                status = false;
                break;
        }

        if (!status) {
            return;
        }

        tokenStoreAdminAxios({
            url: "/api/v1/store/admin/order/status/change?statusId=" + statusId,
            method: "POST",
            data: storeOrder
        }).then(res => {
            switch (statusId) {
                case 1 :
                    let changeOrderData = {
                        ...storeOrder,
                        status: statusId
                    };
                    let orderData = storeOrderList.filter(data => {
                        return data.seq !== storeOrder.seq
                    });
                    orderData.splice(Number(query.get("f")), 0, changeOrderData);
                    setStoreOrder(orderData)
                    break;
                case 2:
                    let deleteOrderData = storeOrderList.filter(data => {
                        return data.seq !== storeOrder.seq
                    })
                    setStoreOrder(deleteOrderData);
                    break;
                default:
                    alert("잘못된 접근입니다.");
                    break;
            }
        }).catch(err => {
            console.log(err);
            alert("주문 상태 변경을 실패하였습니다. 관리자에게 문의 주세요.");
            return;
        })
    }

    let cancelOrder = () => {
        console.log(storeOrder);
        tokenStoreAdminAxios({
            url: "/api/v1/store/admin/order/cancel/negative",
            method: "POST",
            data: {
                merchant_uid: storeOrder.storeOrderPgInfo.merchantUid, // 주문번호
                cancel_request_amount: Number(storeOrder.totalPrice), // 환불금액
                reason: "주문 거절" // 환불사유
            }
        }).then(res => {
            console.log(res)
        }).catch(err => {
            console.log(err)
        })
    }

    return (
        <div>
            {
                storeOrder !== undefined ? (
                    <>
                        {
                            storeOrder.status === 0 & storeOrder.payStatus === 1 ? (
                                <div className={"category-status"}>
                                    <p className={"warning-p"} style={{
                                        fontSize: "12px"
                                    }}>새로 들어온 주문 입니다. 주문을 받아주세요.</p>
                                </div>
                            ) : (
                                <div className={"category-status"}>
                                    <CheckCircle/>
                                    <p>주문 진행 중..</p>
                                </div>
                            )
                        }
                        <div className={"main-container2-top"} style={{
                            height: "7%"
                        }}>
                            <h2>주문 상세보기</h2>
                        </div>
                        <div className={"main-container2-body"}>
                            <div className={"order-detail-body"}>
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
                                                                    <div className={"order-detail-menu"}
                                                                         key={index}>
                                                                        <div
                                                                            className={"order-detail-side-part"}>
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
                            </div>
                        </div>
                        <div className={"main-container2-footer"}>
                            <div>
                                {
                                    storeOrder.status === 0 & storeOrder.payStatus === 1 ? (
                                        <>
                                            <div className={"main-btn-false m-f-1 position-center"}
                                                 onClick={cancelOrder}>
                                                <div>
                                                    <p>주문 거절</p>
                                                </div>
                                            </div>
                                            <div className={"main-btn-true m-f-1 position-center"}
                                                 onClick={() => {
                                                     orderStatusOnClickGetOrder(1)
                                                 }}>
                                                <div>
                                                    <p>주문 받기</p>
                                                </div>
                                            </div>
                                        </>
                                    ) : (
                                        <>
                                            <div className={"main-btn-false m-f-1 position-center"}
                                                 onClick={cancelOrder}>
                                                <div>
                                                    <p>주문 취소</p>
                                                </div>
                                            </div>
                                            <div className={"main-btn-true m-f-1 position-center"} onClick={() => {
                                                orderStatusOnClickGetOrder(2)
                                            }}>
                                                <div>
                                                    <p>주문 완료</p>
                                                </div>
                                            </div>
                                        </>
                                    )
                                }
                            </div>
                        </div>
                    </>
                ) : (
                    <></>
                )
            }
        </div>
    );
}

export default StoreOrderDetail;