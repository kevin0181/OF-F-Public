import "../styles/css/login/auth.css";
import logo1 from "./../assets/logo1.svg";
import logo2 from "./../assets/logo2.svg";
import loginLogo from "./../assets/icon/userLoginLogo.svg";
import {useLocation, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import {notTokenAxios} from "../Config/customAxios";

let SignUp = () => {

    const navigate = useNavigate();

    const [emailStatus, setEmailStatus] = useState(false);

    const query = useQuery();

    function useQuery() {
        return new URLSearchParams(useLocation().search);
    }

    useEffect(() => {
        if (query.get("email") !== null) {
            setAuth({
                ...auth,
                email: query.get("email")
            })
        }
    }, [])

    const [auth, setAuth] = useState({
        email: "",
        password: "",
        rePassword: "",
        name: "",
        phoneNumber: "",
        emailReceiveStatus: false,
        phoneNumberReceiveStatus: false
    });

    const [errorMsg, setErrorMsg] = useState("");

    let onChangeAuth = (e) => {

        if (e.target.type === "checkbox") {
            setAuth({
                ...auth,
                [e.target.name]: document.getElementById(e.target.id).checked
            })
            return;
        }

        if (e.target.name === "email") {
            setEmailStatus(true);
        }

        if (e.target.name === "email" && e.target.value === "") {
            setEmailStatus(false);
        }

        setErrorMsg("")
        setAuth({
            ...auth,
            [e.target.name]: e.target.value
        });
    }

    let signUpBtn = async () => {

        await signUpValidation();

        await notTokenAxios({
            method: 'post',
            url: '/api/v1/auth/n/signIn',
            data: {
                email: auth.email,
                password: auth.password,
                rePassword: auth.rePassword,
                name: auth.name,
                phoneNumber: auth.phoneNumber,
                emailReceiveStatus: auth.emailReceiveStatus,
                phoneNumberReceiveStatus: auth.phoneNumberReceiveStatus
            }
        }).then(res => {
            console.log(res);
        }).catch(err => {
            setErrorMsg(err.response.data.detail);
        });
    }

    let signUpValidation = () => {

        if (auth.email === "") {
            setErrorMsg("이메일을 입력하세요.");
            return;
        }
        if (auth.password === "") {
            setErrorMsg("비밀번호를 입력하세요.");
            return;
        }
        if (auth.rePassword === "") {
            setErrorMsg("비밀번호 확인을 입력하세요.");
            return;
        }
        if (auth.name === "") {
            setErrorMsg("이름을 입력하세요.");
            return;
        }
        if (auth.phoneNumber === "") {
            setErrorMsg("전화번호를 입력하세요.");
            return;
        }
        if (auth.emailReceiveStatus === false) {
            setErrorMsg("이메일 수신 동의가 필요합니다.");
            return;
        }
        if (auth.phoneNumberReceiveStatus === false) {
            setErrorMsg("sms 수신 동의가 필요합니다.");
            return;
        }
        if (auth.password !== auth.rePassword) {
            setErrorMsg("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return;
        }

    }

    let sendEmailCertification = () => {
        notTokenAxios({
            method: 'get',
            url: '/api/v1/auth/n/email/check?email=' + auth.email,
        }).then(res => {
            console.log(res)
        }).catch(err => {
            console.log(err)
        });
    }


    return (
        <div className={"login-Container"}>
            <div className={"login-left-container"}>
                <div className={"login-logo"} onClick={() => {
                    navigate("/");
                }}>
                    <div>
                        <img src={logo2} alt={"login logo"}/>
                    </div>
                    <div>
                        <img src={logo1} alt={"login logo"}/>
                    </div>
                </div>
                <div className={"login-form"}>
                    <div style={{
                        padding: "10px 0px 0px 0px"
                    }}>
                        <p>이메일</p>
                        <input className={"login-input m-input "} onChange={onChangeAuth} type={"text"} name={"email"}
                               value={auth.email}/>
                    </div>
                    <div style={{
                        padding: "0",
                        fontSize: "12px"
                    }}>
                        {
                            emailStatus === true ? (<>
                                <p
                                    onClick={sendEmailCertification}
                                    style={{
                                        padding: "3px 1px",
                                        width: "50%"
                                    }} className={"email-p"}>이메일 인증하기</p>
                            </>) : (<></>)
                        }
                    </div>
                    {
                        query.get("id") !== null ? (<>
                            <div>
                                <p>비밀번호</p>
                                <input className={"login-input m-input"} onChange={onChangeAuth} type={"password"}
                                       value={auth.password}
                                       name={"password"}/>
                            </div>
                            <div>
                                <p>비밀번호 확인</p>
                                <input className={"login-input m-input"} onChange={onChangeAuth} type={"password"}
                                       value={auth.rePassword}
                                       name={"rePassword"}/>
                            </div>
                            <div>
                                <p>이름</p>
                                <input className={"login-input m-input"} onChange={onChangeAuth} type={"text"}
                                       name={"name"}
                                       value={auth.name}/>
                            </div>
                            <div>
                                <p>전화번호</p>
                                <input className={"login-input m-input"} onChange={onChangeAuth} type={"text"}
                                       value={auth.phoneNumber}
                                       name={"phoneNumber"}/>
                            </div>
                            <div className={"input-checkbox-div"}>
                                <p className={"input-checkbox"}>이메일 수신 동의</p>
                                <input className={"m-checkbox-signUp"} id={"emailReceiveStatus"} onChange={onChangeAuth}
                                       type={"checkbox"}
                                       value={true}
                                       name={"emailReceiveStatus"}/>
                            </div>
                            <div className={"input-checkbox-div"}>
                                <p className={"input-checkbox"}>sms 수신 동의</p>
                                <input className={"m-checkbox-signUp"} id={"phoneNumberReceiveStatus"}
                                       onChange={onChangeAuth} type={"checkbox"}
                                       value={true}
                                       name={"phoneNumberReceiveStatus"}/>
                            </div>
                            <div className={"error-msg"}>
                                <p>{errorMsg}</p>
                            </div>
                            <div className={"login-btn-div"}>
                                <div className={"login-btn"} onClick={signUpBtn}>
                                    <p>
                                <span style={{
                                    paddingRight: "5px"
                                }}>
                                <img src={loginLogo} alt={"login Logo"}/>
                                </span>
                                        회원가입</p>
                                </div>
                            </div>
                        </>) : (<></>)
                    }
                </div>
            </div>
            <div className={"login-right-container"}>
            </div>
        </div>
    );
}
export default SignUp;