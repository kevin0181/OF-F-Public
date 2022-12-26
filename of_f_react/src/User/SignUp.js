import "../styles/css/login/auth.css";
import logo1 from "./../assets/logo1.svg";
import logo2 from "./../assets/logo2.svg";
import loginLogo from "./../assets/icon/userLoginLogo.svg";
import {useNavigate} from "react-router-dom";

let SignUp = () => {

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
                    <div>
                        <p>비밀번호</p>
                        <input className={"login-input m-input"}/>
                    </div>
                    <div>
                        <p>비밀번호 확인</p>
                        <input className={"login-input m-input"}/>
                    </div>
                    <div>
                        <p>이름</p>
                        <input className={"login-input m-input"}/>
                    </div>
                    <div>
                        <p>전화번호</p>
                        <input className={"login-input m-input"}/>
                    </div>
                    <div className={"login-btn-div"}>
                        <div className={"login-btn"}>
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