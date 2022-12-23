import "./../../styles/css/header/header.css";
import logo2 from "./../../assets/logo2.svg";
import logo1 from "./../../assets/logo1.svg";
import startIcon from "./../../assets/icon/start_icon.svg";

let Header = () => {
    return (
        <div className={"header"}>
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
                            <a href="#!">홈</a>
                        </li>
                        <li>
                            <a href="#!">소개</a>
                        </li>
                        <li>
                            <a href="#!">구독료</a>
                        </li>
                    </ul>
                </div>
                <div className={"auth-btn"}>
                    <div>
                        <div>
                            <img src={startIcon} alt={"start icon"}/>
                        </div>
                        <div>시작하기</div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Header;