import "./../../styles/css/main/main.css";
import "../../styles/css/main/introduction.css";
import "../../styles/css/main/subscribe.css";
import "../../styles/css/main/inquiry.css";


import mainLogo from "./../../assets/icon/main_logo.svg";
import phoneLogo from "./../../assets/icon/connect_phone_logo.svg";
import platformImg from "./../../assets/icon/test_platform_img.svg";
import ipad from "./../../assets/icon/ipad.svg";


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
                <div>
                    <div className={"introduction-top"}>
                        <p>사용자에 따라 다르게 제공되는 서비스</p>
                        <small>OF_F는 다양한 플랫폼의 기능들을 제공하여 사용자들이 보다 편한게<br/>
                            서비스를 사용할 수 있도록 도와줍니다.</small>
                    </div>
                    <div className={"introduction-bottom"}>
                        <div>
                            <div className={"introduction-list"}>
                                <div className={"introduction-list-part"}>
                                    <div>
                                        <p>Payroll</p>
                                        <p>Keep track of everyone's salaries and whether or not they've been paid.
                                            Direct
                                            deposit not supported.</p>
                                    </div>
                                </div>
                                <div className={"introduction-list-part"}>
                                    <div>
                                        <p>Claim expenses</p>
                                        <p>All of your receipts organized into one place, as long as you don't mind
                                            typing
                                            in the data by hand.</p>
                                    </div>
                                </div>
                                <div className={"introduction-list-part"}>
                                    <div>
                                        <p>VAT handling</p>
                                        <p>We only sell our software to companies who don't deal with VAT at all, so
                                            technically we do all the VAT stuff they need.</p>
                                    </div>
                                </div>
                                <div className={"introduction-list-part"}>
                                    <div>
                                        <p>Reporting</p>
                                        <p>Easily export your data into an Excel spreadsheet where you can do whatever
                                            the
                                            hell you want with it.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className={"introduction-ipad"}>
                            <div>
                                <img src={ipad} alt={"아이패드"}/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className={"subscribe"} id={"subscribe"}>
            </div>
        </div>
    );
}

export default Main;