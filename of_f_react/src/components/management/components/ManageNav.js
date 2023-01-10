import {Outlet} from "react-router-dom";
import "./../../../styles/css/management/manageDashBoard.css"
import chevronLeft from "./../../../assets/icon/chevron-left.svg";
import logo from "./../../../assets/logo1.svg";
import {ReactComponent as HomeLogo} from "./../../../assets/icon/view-grid.svg";

let ManageNav = () => {
    return (<>
        <div className={"manage-container"}>
            <div className={"manage-left-nav"}>
                <div className={"manage-nav-container"}>
                    <div className={"nav-list"}>
                        <div className={"nav-list-top"}>
                            <img src={logo} alt={"logo"}/>
                        </div>
                        <div className={"nav-list-card"}>
                            <div className={"card-container"}>
                                <div className={"card-name"}>
                                    <div className={"card-icon"}>
                                        <HomeLogo/>
                                    </div>
                                    <div className={"card-p"}>
                                        <p>HOME</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className={"nav-hr"}>
                            <div className={"nav-hr-container"}>
                                <div className={"nav-hr-line"}>
                                    <div></div>
                                </div>
                                <div className={"nav-hr-p"}>
                                    <p>menu</p>
                                </div>
                            </div>
                        </div>
                        <div className={"nav-list-card"}>
                            <div className={"card-container"}>
                                <div className={"card-name"}>
                                    <div className={"card-icon"}>
                                        <HomeLogo/>
                                    </div>
                                    <div className={"card-p"}>
                                        <p>HOME</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className={"nav-list-card"}>
                            <div className={"card-container"}>
                                <div className={"card-name"}>
                                    <div className={"card-icon"}>
                                        <HomeLogo/>
                                    </div>
                                    <div className={"card-p"}>
                                        <p>HOME</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className={"nav-list-card"}>
                            <div className={"card-container"}>
                                <div className={"card-name"}>
                                    <div className={"card-icon"}>
                                        <HomeLogo/>
                                    </div>
                                    <div className={"card-p"}>
                                        <p>HOME</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className={"nav-list-card"}>
                            <div className={"card-container"}>
                                <div className={"card-name"}>
                                    <div className={"card-icon"}>
                                        <HomeLogo/>
                                    </div>
                                    <div className={"card-p"}>
                                        <p>HOME</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <div className={"nav-btn"}>
                    <img src={chevronLeft} style={{
                        width: "100%",
                        height: "100%"
                    }}/>
                </div>
            </div>
            <Outlet/>
        </div>
    </>);
}
export default ManageNav;