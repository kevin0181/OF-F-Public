import "./../../styles/css/header/header.css";
import logo2 from "./../../assets/logo2.svg";
import logo1 from "./../../assets/logo1.svg";

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
                        <li>홈</li>
                        <li>소개</li>
                        <li>구독료</li>
                    </ul>
                </div>
            </div>
        </div>
    );
}

export default Header;