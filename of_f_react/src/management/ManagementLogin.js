import "./../styles/css/management/managementLogin.css";
import logo from "./../assets/logo1.svg";
import userLogo from "./../assets/icon/user.svg";
import {notTokenAxios} from "../Config/customAxios";
import {useEffect, useState} from "react";
import {useCookies} from "react-cookie";
import {useNavigate} from "react-router-dom";

let ManagementLogin = () => {

    const [cookies, setCookie, removeCookie] = useCookies(['accessToken']);

    const navigate = useNavigate();

    useEffect(() => {
        if (localStorage.getItem("l-st") === "true") { //이미 로그인 되어 있는 상태라면?
            navigate("/");
        }
    }, []);

    const [auth, setAuth] = useState({
        email: "",
        password: ""
    });

    let onChangeAuth = (e) => {
        setAuth({
            ...auth,
            [e.target.name]: e.target.value
        })
    }

    let onKeyPressLogin = (e) => {
        if (e.key === "Enter") {
            loginBtn();
        }
    }

    let loginBtn = () => {

        if (auth.email === "") {
            alert("이메일을 입력하세요.");
            return;
        }

        if (auth.password === "") {
            alert("비밀번호를 입력하세요.");
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

            if (res.data.code === 401) { //로그인 실패
                alert(res.data.detail);
                return;
            }

            const expires = new Date();
            expires.setMinutes(expires.getMinutes() + 30);

            setCookie("accessToken", res.data.data.accessToken, {
                path: "/",
                expires
            });

            localStorage.setItem("l-st", true); // -> 로그인 된 상태
            window.location.replace("/");

        }).catch((err) => {
            console.log(err);
            alert(err.response.data.detail);
        });
    }

    return (<>
        <div className={"management-container"}>
            <div className={"management-login-form"}>
                <div className={"management-login-form-top"}>
                    <img src={logo} className={"manage-login-logo"}/><h2>관리자 로그인</h2>
                </div>
                <div className={"management-login-form-body"}>
                    <div className={"management-login-form-div"}>
                        <input className={"m-input"} name={"email"} value={auth.email}
                               onChange={onChangeAuth}
                               placeholder={"email"}/>
                    </div>
                    <div className={"management-login-form-div"}>
                        <input className={"m-input"} onChange={onChangeAuth} onKeyPress={onKeyPressLogin} type={"password"}
                               name={"password"} value={auth.password} placeholder={"password"}/>
                    </div>
                </div>
                <div className={"management-login-form-bottom"}>
                    <div className={"manage-login-btn"}>
                        <div className={"login-btn"} onClick={loginBtn}>
                            <img src={userLogo} style={{
                                width: "20%",
                                height: "90%",
                                marginRight: "5px"
                            }}/> <p>로그인</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </>);
}

export default ManagementLogin;