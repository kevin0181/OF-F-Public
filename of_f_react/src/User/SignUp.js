import "../styles/css/login/auth.css";
import logo1 from "./../assets/logo1.svg";
import logo2 from "./../assets/logo2.svg";
import loginLogo from "./../assets/icon/userLoginLogo.svg";
import {useNavigate} from "react-router-dom";
import {useState} from "react";
import {notTokenAxios} from "../Config/customAxios";

let SignUp = () => {

    const navigate = useNavigate();

    const [auth, setAuth] = useState({
        email: "",
        password: "",
        rePassword: "",
        name: "",
        phoneNumber: ""
    });

    const [errorMsg, setErrorMsg] = useState("");

    let onChangeAuth = (e) => {
        setErrorMsg("")
        setAuth({
            ...auth,
            [e.target.name]: e.target.value
        })
    }

    let signUpBtn = () => {
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

        if (auth.password !== auth.rePassword) {
            setErrorMsg("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return;
        }


        notTokenAxios({
            method: 'post',
            url: '/api/v1/auth/n/signIn',
            data: {
                email: auth.email,
                password: auth.password,
                rePassword: auth.rePassword,
                name: auth.name,
phoneNumber:auth.phoneNumber,
                emailReceiveStatus:auth.emailReceiveStatus,

            }
        })
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
                    <div>
                        <p>이메일</p>
                        <input className={"login-input m-input "} onChange={onChangeAuth} type={"text"} name={"email"}
                               value={auth.email}/>
                    </div>
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
                        <input className={"login-input m-input"} onChange={onChangeAuth} type={"text"} name={"name"}
                               value={auth.name}/>
                    </div>
                    <div>
                        <p>전화번호</p>
                        <input className={"login-input m-input"} onChange={onChangeAuth} type={"text"}
                               value={auth.phoneNumber}
                               name={"phoneNumber"}/>
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
                </div>
            </div>
            <div className={"login-right-container"}>
            </div>
        </div>
    );
}
export default SignUp;