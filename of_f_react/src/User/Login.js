import {notTokenAxios} from "../Config/customAxios";
import "../styles/css/login/auth.css";
import logo1 from "./../assets/logo1.svg";
import logo2 from "./../assets/logo2.svg";
import loginLogo from "./../assets/icon/userLoginLogo.svg";
import {Link, useNavigate} from "react-router-dom";
import {useState} from "react";
import {useCookies} from "react-cookie";

let Login = () => {

    const [cookies, setCookie, removeCookie] = useCookies(['accessToken']);

    const navigate = useNavigate();

    const [auth, setAuth] = useState({
        email: "test1@test1.com",
        password: "test1234@"
    });

    const [errorMsg, setErrorMsg] = useState("");

    let onChangeAuth = (e) => {
        setErrorMsg("");
        setAuth({
            ...auth,
            [e.target.name]: e.target.value
        })
    }

    let loginBtn = () => {

        if (auth.email === "") {
            setErrorMsg("이메일을 입력하세요.");
            return;
        }

        if (auth.password === "") {
            setErrorMsg("비밀번호를 입력하세요.");
            return;
        }


        notTokenAxios({
            method: 'post',
            url: '/api/v1/auth/n/login',
            data: {
                email: auth.email,
                password: auth.password
            }
        }).then(res => {

            const expires = new Date();
            expires.setMinutes(expires.getMinutes() + 30);

            setCookie("accessToken", res.data.data.accessToken, {
                path: "/",
                expires
            });

            localStorage.setItem("l-st", true); // -> 로그인 된 상태


        }).catch((err) => {
            console.log(err);
            setErrorMsg(err.response.data.detail);
        }).finally(() => {
            window.location.replace("/");
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
                    <div>
                        <p>이메일</p>
                        <input type={"text"} className={"login-input m-input "} name={"email"} onChange={onChangeAuth}
                               value={auth.email}/>
                    </div>
                    <div style={{
                        padding: "10px 0px 0px 0px"
                    }}>
                        <p>비밀번호</p>
                        <input type={"password"} className={"login-input m-input"} name={"password"}
                               onChange={onChangeAuth}
                               value={auth.password}/>
                    </div>
                    <div style={{
                        padding: "3px 3px",
                        fontSize: "12px"
                    }}>
                        <span><Link to={"/find/id"}>아이디 찾기</Link></span>&nbsp;&nbsp;||&nbsp;&nbsp;
                        <span><Link to={"/find/pwd"}>비밀번호 찾기</Link></span>
                    </div>
                    <div className={"error-msg"}>
                        <p>{errorMsg}</p>
                    </div>
                    <div className={"login-btn-div"}>
                        <div className={"login-btn"} onClick={loginBtn}>
                            <p>
                                <span style={{
                                    paddingRight: "5px"
                                }}>
                                <img src={loginLogo} alt={"login Logo"}/>
                                </span>
                                로그인</p>
                        </div>
                    </div>
                </div>
            </div>
            <div className={"login-right-container"}>
            </div>
        </div>
    );
}
export default Login;