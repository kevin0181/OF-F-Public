import "./../../styles/css/main/main.css";
import "../../styles/css/main/introduction.css";
import "../../styles/css/main/subscribe.css";
import "../../styles/css/main/inquiry.css";
import "../../styles/css/main/contactUs.css";


import mainLogo from "./../../assets/icon/main_logo.svg";
import phoneLogo from "./../../assets/icon/connect_phone_logo.svg";
import platformImg from "./../../assets/icon/test_platform_img.svg";
import ipad from "./../../assets/icon/ipad.svg";
import subBackIcon from "./../../assets/icon/subscribe_back_icon.svg";
import checkCircle from "./../../assets/icon/check-circle.svg";
import CustomSelect from "../custom/CustomSelect";


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
            <div className={"background-color-gradation"}>
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
                    <div className={"sub-back-icon"}>
                        <img src={subBackIcon} alt={"icon2"}/>
                    </div>
                    <div className={"sub-body"}>
                        <div>
                            <h2>
                                구독
                            </h2>
                        </div>
                        <div style={{
                            fontSize: "16px"
                        }}
                        >가게에따라 다른 구독을 제공합니다.
                        </div>
                        <div className={"sub-card-container"}>
                            <div style={{
                                top: "20px"
                            }}>
                                <div className={"sub-card card-sky"}>
                                    <div className={"sub-card-header"}>
                                        <div>
                                            <h1>$15</h1>
                                        </div>
                                        <div>
                                            <p>Small business</p>
                                            <small>Perfect for small / medium sized<br/> businesses.</small>
                                        </div>
                                    </div>
                                    <div className={"sub-card-body"}>
                                        <div className={"sub-card-btn"}>
                                            <p>Get started</p>
                                        </div>
                                    </div>
                                    <div className={"sub-card-footer"}>
                                        <div className={"sub-list"}>
                                            <div>
                                                <img src={checkCircle} alt={"check-circle"}/>
                                            </div>
                                            <div>
                                                <p>Send 25 quotes and invoices</p>
                                            </div>
                                        </div>
                                        <div className={"sub-list"}>
                                            <div>
                                                <img src={checkCircle} alt={"check-circle"}/>
                                            </div>
                                            <div>
                                                <p>Connect up to 5 bank accounts</p>
                                            </div>
                                        </div>
                                        <div className={"sub-list"}>
                                            <div>
                                                <img src={checkCircle} alt={"check-circle"}/>
                                            </div>
                                            <div>
                                                <p>Track up to 50 expenses per month</p>
                                            </div>
                                        </div>
                                        <div className={"sub-list"}>
                                            <div>
                                                <img src={checkCircle} alt={"check-circle"}/>
                                            </div>
                                            <div>
                                                <p>Automated payroll support</p>
                                            </div>
                                        </div>
                                        <div className={"sub-list"}>
                                            <div>
                                                <img src={checkCircle} alt={"check-circle"}/>
                                            </div>
                                            <div>
                                                <p>Export up to 12 reports</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style={{
                                top: "-27px"
                            }}>
                                <div className={"sub-card card-blue"}>
                                    <div className={"sub-card-header"}>
                                        <div>
                                            <h1>$15</h1>
                                        </div>
                                        <div>
                                            <p>Small business</p>
                                            <small>Perfect for small / medium sized<br/> businesses.</small>
                                        </div>
                                    </div>
                                    <div className={"sub-card-body"}>
                                        <div className={"sub-card-btn"}>
                                            <p>Get started</p>
                                        </div>
                                    </div>
                                    <div className={"sub-card-footer"}>
                                        <div className={"sub-list"}>
                                            <div>
                                                <img src={checkCircle} alt={"check-circle"}/>
                                            </div>
                                            <div>
                                                <p>Send 25 quotes and invoices</p>
                                            </div>
                                        </div>
                                        <div className={"sub-list"}>
                                            <div>
                                                <img src={checkCircle} alt={"check-circle"}/>
                                            </div>
                                            <div>
                                                <p>Connect up to 5 bank accounts</p>
                                            </div>
                                        </div>
                                        <div className={"sub-list"}>
                                            <div>
                                                <img src={checkCircle} alt={"check-circle"}/>
                                            </div>
                                            <div>
                                                <p>Track up to 50 expenses per month</p>
                                            </div>
                                        </div>
                                        <div className={"sub-list"}>
                                            <div>
                                                <img src={checkCircle} alt={"check-circle"}/>
                                            </div>
                                            <div>
                                                <p>Automated payroll support</p>
                                            </div>
                                        </div>
                                        <div className={"sub-list"}>
                                            <div>
                                                <img src={checkCircle} alt={"check-circle"}/>
                                            </div>
                                            <div>
                                                <p>Export up to 12 reports</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style={{
                                top: "56px"
                            }}>
                                <div className={"sub-card card-sky"}>
                                    <div className={"sub-card-header"}>
                                        <div>
                                            <h1>$15</h1>
                                        </div>
                                        <div>
                                            <p>Small business</p>
                                            <small>Perfect for small / medium sized<br/> businesses.</small>
                                        </div>
                                    </div>
                                    <div className={"sub-card-body"}>
                                        <div className={"sub-card-btn"}>
                                            <p>Get started</p>
                                        </div>
                                    </div>
                                    <div className={"sub-card-footer"}>
                                        <div className={"sub-list"}>
                                            <div>
                                                <img src={checkCircle} alt={"check-circle"}/>
                                            </div>
                                            <div>
                                                <p>Send 25 quotes and invoices</p>
                                            </div>
                                        </div>
                                        <div className={"sub-list"}>
                                            <div>
                                                <img src={checkCircle} alt={"check-circle"}/>
                                            </div>
                                            <div>
                                                <p>Connect up to 5 bank accounts</p>
                                            </div>
                                        </div>
                                        <div className={"sub-list"}>
                                            <div>
                                                <img src={checkCircle} alt={"check-circle"}/>
                                            </div>
                                            <div>
                                                <p>Track up to 50 expenses per month</p>
                                            </div>
                                        </div>
                                        <div className={"sub-list"}>
                                            <div>
                                                <img src={checkCircle} alt={"check-circle"}/>
                                            </div>
                                            <div>
                                                <p>Automated payroll support</p>
                                            </div>
                                        </div>
                                        <div className={"sub-list"}>
                                            <div>
                                                <img src={checkCircle} alt={"check-circle"}/>
                                            </div>
                                            <div>
                                                <p>Export up to 12 reports</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className={"contact-us"} id={"contact-us"}>
                    <div>
                        <div className={"contact-us-header"}>
                            <div>
                                <h1>문의하기</h1>
                                <p>문의를 통해 보내주시면 등록해주신 이메일 및 번호를 통해
                                    답변을 드립니다.</p>
                            </div>
                        </div>
                        <div className={"contact-us-body"}>
                            <div className={"contact-card"}>
                                <div className={"contact-card-left"}>
                                    <div>
                                        <p>이름</p>
                                        <input className={"m-input"}/>
                                    </div>
                                    <div>
                                        <p>이메일</p>
                                        <input className={"m-input"}/>
                                    </div>
                                    <div>
                                        <p>전화번호</p>
                                        <div className={"contact-card-phoneNumber"}>
                                            <div>
                                                <CustomSelect defaultData={{
                                                    seq: 1,
                                                    name: "123"
                                                }}/>
                                            </div>
                                            &nbsp; - &nbsp;
                                            <div>
                                                <input className={"m-input"}/>
                                            </div>
                                            &nbsp;- &nbsp;
                                            <div>
                                                <input className={"m-input"}/>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <p>문의 종류</p>
                                        <input className={"m-input"}/>
                                    </div>
                                </div>
                                <div className={"contact-card-right"}></div>
                            </div>
                        </div>
                        <div className={"contact-us-footer"}></div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Main;