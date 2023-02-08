import React, {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {ReactComponent as XBtn} from "../../../assets/icon/x.svg";
import {ReactComponent as ChevronDown} from "../../../assets/icon/chevron-down.svg";
import "./../../../styles/css/order/payInfo.css";
import {useRecoilState} from "recoil";
import {orderStore as orderStoreRecoil} from "../../../store/order/orderStore";
import {ReactComponent as PlusBtn} from "./../../../assets/icon/plus.svg";
import {order as orderRecoil} from "../../../store/order/order";

let PayInfo = () => {

    const navigate = useNavigate();
    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보
    let {storeId, qrId} = useParams();
    const [order, setOrder] = useRecoilState(orderRecoil); //  주문 목록(장바구니)

    const [agreementStatus, setAgreementStatus] = useState({
        f: false,
        s: false
    })

    useEffect(() => {
        console.log(orderStore)
    }, [orderStore])
    return (
        <div className={"order-container"}>
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
                            <h3>주문하기</h3>
                        </div>
                    </div>
                </div>
                <div className={"basket-list-container-body"}>
                    <div>
                        <div className={"payInfo-body-top"}>
                            <h4>QR 주문</h4>
                            <p>{orderStore.name}</p>
                            <small>&bull;{orderStore.address}&nbsp;{orderStore.detailAddress}</small>
                            <br/>
                            <small>&bull;{orderStore.businessPhoneNumber}</small>
                        </div>
                        <div className={"payInfo-body-body1"}>
                            <small style={{
                                fontSize: "11px"
                            }}>가게에게 주문자의 정보를 제공합니다.</small>
                            <br/>
                        </div>
                        <div className={"payInfo-body-body2"}>
                            <h4>주문자의 정보</h4>
                            <div>
                                <div className={"payInfo-phoneNumber"}>
                                    <p>번호 : </p>
                                    <input type={"test"} className={"m-input"} placeholder={"01012341234"}/>
                                </div>
                                <div className={"payInfo-phoneNumberReceive"}>
                                    <small>수신 동의 : </small>
                                    <div className={"number-btn"}>
                                        <PlusBtn/>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div className={"payInfo-phoneNumber"}>
                                    <p>이메일 : </p>
                                    <input type={"test"} className={"m-input"} placeholder={"example@xxx.com"}/>
                                </div>
                                <div className={"payInfo-phoneNumberReceive"}>
                                    <small>이메일 수신 동의 : </small>
                                    <div className={"number-btn"}>
                                        <PlusBtn/>
                                    </div>
                                </div>
                            </div>
                            <div className={"payInfo-body-last"}>
                                <div>
                                    <small style={{
                                        fontSize: "12px"
                                    }}>번호 및 이메일을 수신동의해야 메뉴 완성 알림을 받을 수 있습니다.</small>
                                </div>
                            </div>
                            <div className={"payInfo-order-request"}>
                                <h4>요청 사항</h4>
                                <textarea className={"request-textarea m-input"} placeholder={"요청사항을 입력하세요."}/>
                            </div>
                            <div className={"payInfo-order-agreement"}>
                                <div className={"payInfo-agreement-div"}>
                                    <div>개인정보 제3자 제공</div>
                                    <div onClick={() => {
                                        setAgreementStatus({
                                            ...agreementStatus,
                                            f: !agreementStatus.f
                                        })
                                    }}>
                                        <ChevronDown/>
                                    </div>
                                </div>
                                {
                                    agreementStatus.f ? (<div className={"payInfo-agreement-div-scroll"}>
                                        개인정보 제공 내용
                                    </div>) : (<></>)
                                }
                                <div className={"payInfo-agreement-div"}>
                                    <div>OFF 이용 주의사항</div>
                                    <div onClick={() => {
                                        setAgreementStatus({
                                            ...agreementStatus,
                                            s: !agreementStatus.s
                                        })
                                    }}>
                                        <ChevronDown/>
                                    </div>
                                </div>
                                {
                                    agreementStatus.s ? (<div className={"payInfo-agreement-div-scroll"}>
                                        주의사항
                                    </div>) : (<></>)
                                }
                            </div>
                        </div>
                    </div>
                </div>
                <div className={"basket-list-container-footer"}>
                    <div onClick={() => {
                        navigate(`/store/${storeId}/${qrId}/payInfo`);
                    }}>
                        <p>
                            {order.totalPrice}원 결제하기
                        </p>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default PayInfo;