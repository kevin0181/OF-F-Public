import {setCookie} from "../Config/cookie";
import {notTokenAxios} from "../Config/customAxios";
import "./../styles/css/login/login.css";
import logo1 from "./../assets/logo1.svg";
import logo2 from "./../assets/logo2.svg";
import loginLogo from "./../assets/icon/userLoginLogo.svg";
import {useNavigate} from "react-router-dom";

let Login = () => {

    const navigate = useNavigate();

    let loginBtn = () => {
        notTokenAxios({
            method: 'post',
            url: '/api/v1/auth/n/login',
            data: {
                email: 'test1@test1.com',
                password: 'test1234@'
            }
        }).then(res => {

            const expires = new Date();
            expires.setMinutes(expires.getMinutes() + 30);

            setCookie("accessToken", res.data.data.accessToken, {
                path: "/",
                expires
            });

            setCookie("l-st", true, {
                path: "/",
                expires
            });

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
                        <p>아이디</p>
                        <input className={"login-input m-input "}/>
                    </div>
                    <div style={{
                        padding: "10px 0px 0px 0px"
                    }}>
                        <p>비밀번호</p>
                        <input className={"login-input m-input"}/>
                    </div>
                    <div style={{
                        padding: "3px 3px",
                        fontSize: "12px"
                    }}>
                        <span>아이디 찾기</span>&nbsp;&nbsp;||&nbsp;&nbsp;
                        <span>비밀번호 찾기</span>
                    </div>
                    <div className={"login-btn-div"}>
                        <div className={"login-btn"}>
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