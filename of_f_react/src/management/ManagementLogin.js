import "./../styles/css/management/managementLogin.css";
import logo from "./../assets/logo1.svg";
import userLogo from "./../assets/icon/user.svg";

let ManagementLogin = () => {
    return (<>
        <div className={"management-container"}>
            <div className={"management-login-form"}>
                <div className={"management-login-form-top"}>
                    <img src={logo} className={"manage-login-logo"}/><h2>관리자 로그인</h2>
                </div>
                <div className={"management-login-form-body"}>
                    <div className={"management-login-form-div"}>
                        <input className={"m-input"} placeholder={"email"}/>
                    </div>
                    <div className={"management-login-form-div"}>
                        <input className={"m-input"} placeholder={"password"}/>
                    </div>
                </div>
                <div className={"management-login-form-bottom"}>
                    <div className={"manage-login-btn"}>
                        <div className={"login-btn"}>
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