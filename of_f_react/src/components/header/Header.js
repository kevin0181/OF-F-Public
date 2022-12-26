import "./../../styles/css/header/header.css";
import logo2 from "./../../assets/logo2.svg";
import logo1 from "./../../assets/logo1.svg";
import startIcon from "./../../assets/icon/start_icon.svg";
import startIconHover from "./../../assets/icon/start_icon_hover.svg";
import {useNavigate} from "react-router-dom"
import {Outlet} from "react-router-dom";

let Header = () => {

    const navigate = useNavigate();

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
                                <a className={"start-btn"}><span className={"start-img"}><img src={startIcon}
                                                                                              alt={"start icon"}
                                                                                              className={"start-icon"}/></span>시작하기</a>
                                <ul className="auth-btn-hide">
                                    <li><a onClick={() => {
                                        navigate("/login")
                                    }}><span className={"start-img"}><img src={startIconHover}
                                                                          alt={"start icon"}
                                                                          className={"start-icon-blue"}/></span>로그인</a>
                                    </li>
                                    <li><a><span className={"start-img"}><img src={startIconHover}
                                                                              alt={"start icon"}
                                                                              className={"start-icon-blue"}/></span>회원가입</a>
                                    </li>
                                </ul>
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