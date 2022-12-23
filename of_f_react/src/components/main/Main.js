import "./../../styles/css/main/main.css";
import "../../styles/css/main/introduction.css";
import "../../styles/css/main/subscribe.css";
import "../../styles/css/main/inquiry.css";


import mainLogo from "./../../assets/icon/main_logo.svg";
import phoneLogo from "./../../assets/icon/connect_phone_logo.svg";
import platformImg from "./../../assets/icon/test_platform_img.svg";
import testSection1 from "../../assets/test/test_section1.png";
import testSection2 from "../../assets/test/test_section2.png";
import testSection3 from "../../assets/test/test_section3.png";


let Main = () => {
    return (
        <div className={"main-container"}>
            <div className={"main"}>
                <div className={"main-logo"}>
                    <img src={mainLogo} alt={"main logo"}/>
                </div>
                <div className={"main-center"}>
                    <div>
                        <p>사용자에게 가장 <span>효과적인</span><br/>
                            솔루션을 제공합니다.</p>
                    </div>
                    <div>
                        <small>
                            Experience the various functions<br/>
                            of various platforms.
                        </small>
                    </div>
                    <div className={"main-btn"}>
                        <div className={"experience-btn"}>
                            <div>Get 3 months free</div>
                        </div>
                        <div className={"connect-btn"}>
                            <div>
                                <img src={phoneLogo} alt={"connect logo"}/>
                                <div>Contact us</div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div>
                            <p>various platforms</p>
                        </div>
                    </div>
                    <div className={"main-bottom"}>
                        <div className={"platform-bottom"}>
                            <div className={"polygon-icon"}></div>
                            <div className={"platform-list"}>
                                <img src={platformImg} alt={"test"}>
                                </img>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className={"introduction"} id={"introduction"}>
                {/*<img src={testSection1} alt={"1"} style={{*/}
                {/*    width: '100%',*/}
                {/*    height: '100%'*/}
                {/*}}/>*/}
            </div>
            {/*<div className={"subscribe"} id={"subscribe"}>*/}
            {/*    <img src={testSection2} alt={"1"} style={{*/}
            {/*        width: '100%',*/}
            {/*        height: '100%'*/}
            {/*    }}/>*/}
            {/*</div>*/}
            {/*<div className={"inquiry"} id={"inquiry"}>*/}
            {/*    <img src={testSection3} alt={"1"} style={{*/}
            {/*        width: '100%',*/}
            {/*        height: '100%'*/}
            {/*    }}/>*/}
            {/*</div>*/}
        </div>
    );
}

export default Main;