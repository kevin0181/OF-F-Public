import "./../../styles/css/header/header.css";
import logo2 from "./../../assets/logo2.svg";
import logo1 from "./../../assets/logo1.svg";
import startIcon from "./../../assets/icon/start_icon.svg";
import startIconHover from "./../../assets/icon/start_icon_hover.svg";
import {Outlet, useNavigate} from "react-router-dom";
import {useRecoilState} from "recoil";
import authState from "../../store/management/auth";
import {useEffect} from "react";
import {tokenAxios} from "../../Config/customAxios";
import {useCookies} from "react-cookie";

let Header = () => {


    const [cookies, setCookie, removeCookie] = useCookies(['accessToken']);

    const navigate = useNavigate();

    let [auth, setAuth] = useRecoilState(authState);

    useEffect(() => {

        if (localStorage.getItem("l-st")) {
            setAuth(true);
        }

    }, [setAuth]);

    let logout = () => {

        tokenAxios({
            method: 'post',
            url: '/api/v1/auth/y/logout',
        }).then(res => {
            if (res.data.data) {
                removeCookie("accessToken", {
                    path: "/"
                });
                localStorage.removeItem("l-st");

                window.location.reload();
            }
        });
    }

    return (
        <>
            <div className={"header"} id={"header"}>
                <div>
                    <div className={"logo"}>
                        <div>
                            <img src={logo2} alt={"logo"}/>
                        </div>
                        <div>
                            <img src={logo1} alt={"logo"}/>
                        </div>
                    </div>
                    <div className={"nav"}>
                        <ul>
                            <li>
                                <a href="#header">홈</a>
                            </li>
                            <li>
                                <a href="#introduction">소개</a>
                            </li>
                            <li>
                                <a href="#subscribe">구독료</a>
                            </li>
                        </ul>
                    </div>
                    <div className={"auth-btn"}>
                        <ul className="auth-btn-start">
                            <li>
                                <a href={"/"} className={"start-btn"}><span className={"start-img"}><img src={startIcon}
                                                                                                         alt={"start icon"}
                                                                                                         className={"start-icon"}/></span>시작하기</a>
                                {
                                    auth === false ? (
                                        <>
                                            <ul className="auth-btn-hide">
                                                <li><a href={"/login"}><span className={"start-img"}><img
                                                    src={startIconHover}
                                                    alt={"start icon"}
                                                    className={"start-icon-blue"}/></span>로그인</a>
                                                </li>
                                                <li><a href={"/signUp"}><span className={"start-img"}><img
                                                    src={startIconHover}
                                                    alt={"start icon"}
                                                    className={"start-icon-blue"}/></span>회원가입</a>
                                                </li>
                                            </ul>
                                        </>
                                    ) : (
                                        <>
                                            <ul className="auth-btn-hide">
                                                <li><a href={"/login"}><span className={"start-img"}><img
                                                    src={startIconHover}
                                                    alt={"start icon"}
                                                    className={"start-icon-blue"}/></span>관리자</a>
                                                </li>
                                                <li><a href={"#!"} onClick={logout}><span className={"start-img"}><img
                                                    src={startIconHover}
                                                    alt={"start icon"}
                                                    className={"start-icon-blue"}/></span>로그아웃</a>
                                                </li>
                                            </ul>
                                        </>
                                    )
                                }
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <Outlet/>
        </>
    );
}

export default Header;