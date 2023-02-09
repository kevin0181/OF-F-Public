import React, {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {ReactComponent as XBtn} from "../../../assets/icon/x.svg";
import {ReactComponent as Check} from "../../../assets/icon/check.svg";
import {ReactComponent as ChevronDown} from "../../../assets/icon/chevron-down.svg";
import {ReactComponent as TossPayLogo} from "../../../assets/logo/logo-toss-pay.svg";
import NpayImg from "../../../assets/logo/npay_20.png";
import kakaoImg from "./../../../assets/logo/payment_icon_yellow_small.png";
import "./../../../styles/css/order/payInfo.css";
import {useRecoilState} from "recoil";
import {orderStore as orderStoreRecoil} from "../../../store/order/orderStore";
import {order as orderRecoil} from "../../../store/order/order";
import {notTokenAxios} from "../../../Config/customAxios";

let PayInfo = () => {

    const navigate = useNavigate();
    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보
    let {storeId, qrId} = useParams();
    const [order, setOrder] = useRecoilState(orderRecoil); //  주문 목록(장바구니)

    const [defaultPayWayStatus, setDefaultPayWayStatus] = useState(""); //기본 결제
    const [easyPayWayStatus, setEasyPayWayStatus] = useState(""); //간편 결제


    const [agreementBtnStatus, setAgreementBtnStatus] = useState({ //수신동의
        phoneNumberReceive: false,
        emailReceive: false
    })

    const [agreementStatus, setAgreementStatus] = useState({ // 개인정보 및 주의사항 클릭시 보여지는 버튼
        f: false,
        s: false
    })

    useEffect(() => {
        console.log(orderStore)
    }, [orderStore]);

    useEffect(() => {
        const jquery = document.createElement("script");
        jquery.src = "https://code.jquery.com/jquery-1.12.4.min.js";
        const iamport = document.createElement("script");
        iamport.src = "https://cdn.iamport.kr/js/iamport.payment-1.2.0.js";
        document.head.appendChild(jquery);
        document.head.appendChild(iamport);
        return () => {
            document.head.removeChild(jquery);
            document.head.removeChild(iamport);
        }
    }, []);

    const onClickPayDefaultWayBtn = (e) => { //결제 방식 선택
        if (e.target.getAttribute("pay-way-name") === null) {
            return;
        }
        setDefaultPayWayStatus(e.target.getAttribute("pay-way-name"));
        setEasyPayWayStatus("");
    }

    const onClickPayEasyWayBtn = (e) => { //결제 방식 선택
        if (e.target.getAttribute("pay-way-name") === null) {
            return;
        }
        setEasyPayWayStatus(e.target.getAttribute("pay-way-name"));
        setDefaultPayWayStatus("");
    }

    const onClickPayBtn = async () => { //결제 버튼 클릭

        if (defaultPayWayStatus === "" & easyPayWayStatus === "") {
            alert("결제 방식을 선택해주세요.");
            return;
        }

        console.log(easyPayWayStatus)
        console.log(defaultPayWayStatus)

        await notTokenAxios({
            method: "POST",
            url: `/api/v1/store/order/pay/before`,
            data: order
        }).then(res => {

            if (defaultPayWayStatus !== "" & easyPayWayStatus === "") { //기본결제
                payDefaultImport(res.data.data);
                return;
            }

            if (defaultPayWayStatus === "" & easyPayWayStatus !== "") { //간편 결제
                payEasyImport(res.data.data);
                return;
            }

            alert("결제 방식이 선택되지 않았습니다.");
            return;

        }).catch(err => {
            alert("결제 전 오류 입니다. 관리자에게 문의주세요.")
            return;
        });

    }

    const payEasyImport = (order) => {

        console.log(order);

        const IMP = window.IMP; // 생략 가능
        IMP.init("imp76725859");

        IMP.request_pay({ // param
            pg: easyPayWayStatus,
            pay_method: "card",
            merchant_uid: order.merchant_uid,
            name: orderStore.name,
            amount: Number(order.totalPrice),
            buyer_email: order.email,
            buyer_name: `${orderStore.name}의 고객`,
            buyer_tel: order.phoneNumber,
            buyer_addr: orderStore.address + " " + orderStore.detailAddress,
            m_redirect_url: "http://localhost:3000/store/1/1QR/payInfo"
        }, rsp => { // callback
            console.log(rsp);
            if (rsp.success) {
                console.log("간편 결제 성공")
            } else {
                console.log("간편 결제 실패")
            }
        });

    }

    const payDefaultImport = (order) => {

        console.log(order)

        const IMP = window.IMP; // 생략 가능
        IMP.init("imp76725859");

        IMP.request_pay({ // param
            pg: "uplus.tlgdacomxpay",
            pay_method: defaultPayWayStatus,
            merchant_uid: order.merchant_uid,
            name: orderStore.name,
            amount: Number(order.totalPrice),
            buyer_email: order.email,
            buyer_name: `${orderStore.name}의 고객`,
            buyer_tel: order.phoneNumber,
            buyer_addr: orderStore.address + " " + orderStore.detailAddress,
            m_redirect_url: "http://localhost:3000/store/1/1QR/payInfo"
        }, rsp => { // callback
            console.log(rsp)
            if (rsp.success) {
                alert("기본 결제 성공")
            } else {
                alert("기본 결제 실패")
            }
        });
    }

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
                            <div className={"payInfo-order-select-pay-way"}>
                                <h4>결제 방식 선택</h4>
                                <h5 style={{
                                    padding: "5px"
                                }}>기본 결제</h5>
                                <div className={"pay-list-div"}>
                                    <div className={"" + (defaultPayWayStatus === "card" ? 'pay-way-active' : '')}
                                         pay-way-name={"card"}
                                         onClick={onClickPayDefaultWayBtn}>
                                        <p>신용카드</p>
                                    </div>
                                    <div className={"" + (defaultPayWayStatus === "phone" ? 'pay-way-active' : '')}
                                         pay-way-name={"phone"} onClick={onClickPayDefaultWayBtn}>
                                        <p>휴대폰 소액결제</p>
                                    </div>
                                    <div className={"" + (defaultPayWayStatus === "vbank" ? 'pay-way-active' : '')}
                                         pay-way-name={"vbank"} onClick={onClickPayDefaultWayBtn}>
                                        <p>가상계좌</p>
                                    </div>
                                    <div className={"" + (defaultPayWayStatus === "trans" ? 'pay-way-active' : '')}
                                         pay-way-name={"trans"} onClick={onClickPayDefaultWayBtn}>
                                        <p>실시간 계좌이체</p>
                                    </div>
                                </div>
                                <h5 style={{
                                    padding: "5px"
                                }}>간편 결제</h5>
                                <div className={"pay-list-div"}>
                                    <div className={"" + (easyPayWayStatus === "kakaopay" ? 'pay-way-active' : '')}
                                         pay-way-name={"kakaopay"} onClick={onClickPayEasyWayBtn}>
                                        <div style={{
                                            width: "13%"
                                        }}>
                                            <img src={kakaoImg}/>
                                        </div>
                                        <p>카카오페이</p>
                                    </div>
                                    <div className={"" + (easyPayWayStatus === "tosspay" ? 'pay-way-active' : '')}
                                         pay-way-name={"tosspay"} onClick={onClickPayEasyWayBtn}>
                                        <div>
                                            <TossPayLogo/>
                                        </div>
                                        <p>토스</p>
                                    </div>
                                    {/*<div className={"" + (easyPayWayStatus === "naverpay" ? 'pay-way-active' : '')}*/}
                                    {/*     pay-way-name={"naverpay"} onClick={onClickPayEasyWayBtn}>*/}
                                    {/*    <div style={{*/}
                                    {/*        width: "13%"*/}
                                    {/*    }}>*/}
                                    {/*        <img src={NpayImg}/>*/}
                                    {/*    </div>*/}
                                    {/*    <p>네이버페이</p>*/}
                                    {/*</div>*/}
                                </div>
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
                    <div onClick={onClickPayBtn}>
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