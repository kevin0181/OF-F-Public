import logo2 from "../assets/logo2.svg";
import logo1 from "../assets/logo1.svg";
import {Link, useNavigate} from "react-router-dom";
import loginLogo from "../assets/icon/userLoginLogo.svg";
import "../styles/css/login/auth.css";

let FindPwd = () => {

    const navigate = useNavigate();

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
                        <span><Link to={"/find/id"}>아이디 찾기</Link></span>
                    </div>
                    <div className={"login-btn-div"}>
                        <div className={"login-btn"}>
                            <p>
                                <span style={{
                                    paddingRight: "5px"
                                }}>
                                <img src={loginLogo} alt={"login Logo"}/>
                                </span>
                                비밀번호 찾기</p>
                        </div>
                    </div>
                </div>
            </div>
            <div className={"login-right-container"}>
            </div>
        </div>
    );
}

export default FindPwd;