import {Outlet} from "react-router-dom";
import "./../../../styles/css/management/manageDashBoard.css"
import chevronLeft from "./../../../assets/icon/chevron-left.svg";
import logo from "./../../../assets/logo1.svg";
import {ReactComponent as HomeIcon} from "./../../../assets/icon/view-grid.svg";
import {ReactComponent as PlayIcon} from "./../../../assets/icon/play.svg";
import {ReactComponent as HistoryIcon} from "./../../../assets/icon/document-text.svg";
import {ReactComponent as BookMarkIcon} from "./../../../assets/icon/bookmark.svg";
import {ReactComponent as BookMarkAlt} from "./../../../assets/icon/bookmark-alt.svg";
import {ReactComponent as CogIcon} from "./../../../assets/icon/cog.svg";
import {ReactComponent as LockIcon} from "./../../../assets/icon/lock-open.svg";


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
                                        <HomeIcon/>
                                    </div>
                                    <div className={"card-p"}>
                                        <p>Home</p>
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
                                    <p>ORDER</p>
                                </div>
                            </div>
                        </div>
                        <div className={"nav-list-card"}>
                            <div className={"card-container"}>
                                <div className={"card-name"}>
                                    <div className={"card-icon"}>
                                        <PlayIcon/>
                                    </div>
                                    <div className={"card-p"}>
                                        <p>Order Start</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className={"nav-list-card"}>
                            <div className={"card-container"}>
                                <div className={"card-name"}>
                                    <div className={"card-icon"}>
                                        <HistoryIcon/>
                                    </div>
                                    <div className={"card-p"}>
                                        <p>Order History</p>
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
                                    <p>MENU</p>
                                </div>
                            </div>
                        </div>
                        <div className={"nav-list-card"}>
                            <div className={"card-container"}>
                                <div className={"card-name"}>
                                    <div className={"card-icon"}>
                                        <BookMarkIcon/>
                                    </div>
                                    <div className={"card-p"}>
                                        <p>Category</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className={"nav-list-card"}>
                            <div className={"card-container"}>
                                <div className={"card-name"}>
                                    <div className={"card-icon"}>
                                        <BookMarkAlt/>
                                    </div>
                                    <div className={"card-p"}>
                                        <p>Menu</p>
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
                                    <p>SIDE</p>
                                </div>
                            </div>
                        </div>
                        <div className={"nav-list-card"}>
                            <div className={"card-container"}>
                                <div className={"card-name"}>
                                    <div className={"card-icon"}>
                                        <BookMarkIcon/>
                                    </div>
                                    <div className={"card-p"}>
                                        <p>Side Category</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className={"nav-list-card"}>
                            <div className={"card-container"}>
                                <div className={"card-name"}>
                                    <div className={"card-icon"}>
                                        <BookMarkAlt/>
                                    </div>
                                    <div className={"card-p"}>
                                        <p>Side Menu</p>
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
                                    <p>SET</p>
                                </div>
                            </div>
                        </div>
                        <div className={"nav-list-card"}>
                            <div className={"card-container"}>
                                <div className={"card-name"}>
                                    <div className={"card-icon"}>
                                        <LockIcon/>
                                    </div>
                                    <div className={"card-p"}>
                                        <p>Info</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className={"nav-list-card"}>
                            <div className={"card-container"}>
                                <div className={"card-name"}>
                                    <div className={"card-icon"}>
                                        <CogIcon/>
                                    </div>
                                    <div className={"card-p"}>
                                        <p>Setting</p>
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