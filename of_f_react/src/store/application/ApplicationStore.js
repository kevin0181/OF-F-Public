import logo2 from "../../assets/logo2.svg";
import logo1 from "../../assets/logo1.svg";
import {useNavigate} from "react-router-dom";
import loginLogo from "../../assets/icon/userLoginLogo.svg";
import {useCookies} from "react-cookie";
import {useEffect, useState} from "react";
import {ReactComponent as LogoIconWhite} from "./../../assets/logo/logo_icon_white.svg";
import {ReactComponent as LogoTextWhite} from "./../../assets/logo/logo_text_white.svg";


let ApplicationStore = () => {
    //가맹점 신청서 페이지
    const [cookies, setCookie, removeCookie] = useCookies(['accessToken']);

    const navigate = useNavigate();

    useEffect(() => {
        if (localStorage.getItem("l-st") === "false" || localStorage.getItem("l-st") === null) { //이미 로그인 되어 있는 상태라면?
            alert("로그인 후 이용이 가능합니다.");
            navigate("/login");
        }
    }, []);

    const [applicationData, setApplicationData] = useState({
        name: "",
        address: "",
        detail_address: "",
        business_number: "",
        account_bank_name: "",
        account_number: "",
        deposit_date: ""
    });


    return (
        <div className={"login-Container"}>
            <div className={"login-left-container"} style={{
                backgroundColor: "#1A72C3",
                opacity: "0.5"
            }}>
                <div className={"login-logo"} style={{
                    display: "flex",
                    justifyContent: "center"
                }} onClick={() => {
                    navigate("/");
                }}>
                    <div>
                        <LogoIconWhite/>
                    </div>
                    <div style={{
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "center"
                    }}>
                        <LogoTextWhite/>
                    </div>
                </div>
            </div>
            <div className={"login-right-container"} style={{
                backgroundColor: "white"
            }}>

                <div className={"login-form"} style={{
                    display: "block",
                    padding: "20px 5%"
                }}>
                    <div className={"application-store-form"} style={{
                        padding: "0px 10px"
                    }}>
                        <div style={{
                            marginTop: "30px"
                        }}>
                            <h2>가맹점 신청 페이지</h2>
                        </div>
                        <div>
                            <p className={"input-top-p"}>가게 이름</p>
                            <input type={"text"} className={"login-input m-input "} name={"name"}
                                   placeholder={"가게 이름을 작성해주세요."}/>
                        </div>
                        <div>
                            <p className={"input-top-p"}>가게 주소</p>
                            <div className={"input-flex"}>
                                <input type={"text"} className={"login-input m-input "} name={"address"}
                                       placeholder={"주소"}/>
                                <input type={"text"} className={"login-input m-input "} name={"detail_address"}
                                       placeholder={"상세 주소"}/>
                            </div>
                        </div>
                        <div>
                            <p className={"input-top-p"}>사업자 등록 번호</p>
                            <input type={"text"} className={"login-input m-input "} name={"business_number"}
                                   placeholder={"사업자 등록 번호를 작성해주세요."}/>
                        </div>
                        <div>
                            <p className={"input-top-p"}>입금 받으실 은행 이름</p>
                            <input type={"text"} className={"login-input m-input "} name={"account_bank_name"}
                                   placeholder={"입금을 원하시는 은행 이름 작성 (ex: 농협)"}/>
                        </div>
                        <div>
                            <p className={"input-top-p"}>입금 받으실 계좌번호</p>
                            <input type={"text"} className={"login-input m-input "} name={"account_number"}
                                   placeholder={"입금을 원하시는 계좌번호를 작성해주세요."}/>
                        </div>
                        <div>
                            <p className={"input-top-p"}>입금 날짜</p>
                            <input type={"date"} className={"log7in-input m-input "} name={"deposit_date"}/>
                        </div>
                        <div style={{
                            marginTop: "40px"
                        }}>
                            <h3>개인정보 수집 및 이용 동의</h3>
                        </div>
                        <div>
                            <input type={"checkbox"} name={""} id={"ccopi"}/>
                            <label htmlFor={"ccopi"}>&nbsp;가맹점 신청을 위한 개인정보 수집 및 이용에 동의<span style={{
                                color: "red"
                            }}>(필수)</span></label>
                        </div>
                        <div>
                            <small>
                                신청 후, 활성화까지 7일 정도 소모 됩니다.
                            </small>
                            <br/>
                            <small style={{
                                color: "red"
                            }}>정보가 다를 경우 신청이 반려됩니다. (별도 연락 없음)</small>
                        </div>
                        <div className={"login-btn-div"}>
                            <div className={"login-btn"}>
                                <p>
                                <span style={{
                                    paddingRight: "5px"
                                }}>
                                <img src={loginLogo} alt={"login Logo"}/>
                                </span>
                                    신청하기</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ApplicationStore;