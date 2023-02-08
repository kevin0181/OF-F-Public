import React, {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {ReactComponent as XBtn} from "../../../assets/icon/x.svg";
import {ReactComponent as Check} from "../../../assets/icon/check.svg";
import {ReactComponent as ChevronDown} from "../../../assets/icon/chevron-down.svg";
import "./../../../styles/css/order/payInfo.css";
import {useRecoilState} from "recoil";
import {orderStore as orderStoreRecoil} from "../../../store/order/orderStore";
import {order as orderRecoil} from "../../../store/order/order";

let PayInfo = () => {

    const navigate = useNavigate();
    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보
    let {storeId, qrId} = useParams();
    const [order, setOrder] = useRecoilState(orderRecoil); //  주문 목록(장바구니)

    const [agreementBtnStatus, setAgreementBtnStatus] = useState({
        phoneNumberReceive: false,
        emailReceive: false
    })

    const [agreementStatus, setAgreementStatus] = useState({ // 개인정보 및 주의사항 클릭시 보여지는 버튼
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
                                    <div
                                        className={"agreement-btn " + (agreementBtnStatus.phoneNumberReceive ? "agreement-active" : "")}
                                        onClick={() => {
                                            setAgreementBtnStatus({
                                                ...agreementBtnStatus,
                                                phoneNumberReceive: !agreementBtnStatus.phoneNumberReceive
                                            });
                                        }}>
                                        <Check/>
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
                                    <div
                                        className={"agreement-btn " + (agreementBtnStatus.emailReceive ? "agreement-active" : "")}
                                        onClick={() => {
                                            setAgreementBtnStatus({
                                                ...agreementBtnStatus,
                                                emailReceive: !agreementBtnStatus.emailReceive
                                            });
                                        }}>
                                        <Check/>
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
                                        1. 개인정보 수집 항목
                                        <br/>
                                        &nbsp;&nbsp;&nbsp;off는 사용자의 전화번호 및 이메일을 수집합니다.
                                        <br/>
                                        2. 개인정보 수집 및 이용 목적
                                        <br/>
                                        &nbsp;&nbsp;&nbsp;수집된 개인정보는 주문 정보 및 데이터화를 위해 사용됩니다.
                                        <br/>
                                        3. 개인정보 보유 및 이용기간
                                        <br/>
                                        &nbsp;&nbsp;&nbsp;수집된 개인정보는 해당 가게의 데이터 삭제와 같이 파기됩니다.
                                        <br/>
                                        4. 동의를 거부할 권리 및 동의 거부에 따른 불이익
                                        <br/>
                                        &nbsp;&nbsp;&nbsp; 전화번호 및 이메일을 작성하지 않을 경우, 개인정보 수집 동의를 거부로 판단하여 off에서 제공하는 서비스가
                                        제한될 수 있습니다. (ex: 실시간 알림서비스 등)
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
                                        1. 사용자의 주문에 따라 음식점의 조리가 시작되므로 단순 변심으로 인한 환불이 불가능합니다.
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