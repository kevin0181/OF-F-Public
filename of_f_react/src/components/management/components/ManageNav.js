import {Outlet, useNavigate} from "react-router-dom";
import "./../../../styles/css/management/manageDashBoard.css";
import chevronLeft from "./../../../assets/icon/chevron-left.svg";
import logo from "./../../../assets/logo1.svg";
import {ReactComponent as HomeIcon} from "./../../../assets/icon/view-grid.svg";
import {ReactComponent as PlayIcon} from "./../../../assets/icon/play.svg";
import {ReactComponent as HistoryIcon} from "./../../../assets/icon/document-text.svg";
import {ReactComponent as BookMarkIcon} from "./../../../assets/icon/bookmark.svg";
import {ReactComponent as BookMarkAlt} from "./../../../assets/icon/bookmark-alt.svg";
import {ReactComponent as CogIcon} from "./../../../assets/icon/cog.svg";
import {ReactComponent as LockIcon} from "./../../../assets/icon/lock-open.svg";
import chevronRight from "./../../../assets/icon/chevron-right.svg";
import {useEffect, useState} from "react";
import 'animate.css';
import {useQuery} from "../../../Config/getQuery";
import {useRecoilState} from "recoil";
import navStatusState from "../../../store/management/navStatus";
import {
    selectStoreInfoRecoil,
    storeInfoRecoil,
    storeStatus as storeStatusRecoil
} from "../../../store/management/storeInfo";
import storeIdState from "../../../store/management/storeId";
import {tokenStoreAdminAxios} from "../../../Config/customStoreAdminAjax";
import Loading from "./Loading";
import adminStoreLoading from "../../../store/management/adminStoreLoading";
import {nodeServerAxios} from "../../../Config/customAxios";

let ManageNav = () => {

    const navigate = useNavigate();

    const query = useQuery();

    const [navStatus, setNavStatus] = useRecoilState(navStatusState); //nav 활성화 비활성화
    const [kindStatus, setKindStatus] = useState(""); // 현재 선택한 nav 상태

    const [storeId, setStoreId] = useRecoilState(storeIdState); // 선택된 가게 id
    const [store, setStore] = useRecoilState(selectStoreInfoRecoil);
    const [storeInfo, setStoreInfo] = useRecoilState(storeInfoRecoil); // 로그인시 가져오는 전체 정보

    const [storeStatus, setStoreStatus] = useRecoilState(storeStatusRecoil); // 가게 상태 정보 몽고디비에서 가져옴

    let getStoreStatusData = () => { //가게 상태 가져옴
        console.log(store.seq)
        nodeServerAxios({
            method: "POST",
            url: "/store/status?storeSeq=" + store.seq
        }).then(res => {
            console.log(res);
            if (res.data !== null) {
                setStoreStatus({
                    storeSeq: res.data.storeSeq,
                    status: res.data.status
                });
            }
        }).catch(err => {
            alert("관리자 설정 오류입니다. 문의주세요.");
            return;
        });
    }

    const [loading, setLoading] = useRecoilState(adminStoreLoading); // 로딩

    useEffect(() => { //처음 가게 전체 데이터 가져옴
        tokenStoreAdminAxios({
            method: 'get',
            url: '/api/v1/store/admin',
        }).then(res => {
            setStoreInfo(res.data.data);
            setStore(res.data.data.stores[storeId]);
        });

    }, []);

    useEffect(() => {
        if (store.seq !== undefined && store.storeOrders === null) {
            getStoreStatusData();
        }
    }, [store]);

    useEffect(() => { // nav 종류

        if (query.get("kind") !== null) {
            setKindStatus(query.get("kind"));
        } else {
            setKindStatus("Home");
        }

    }, [query]);

    useEffect(() => { // store 부분 넣어줌 (가게에따라 현재 선택된 가게 정보 다르게)

        if (storeInfo.stores !== undefined && storeInfo.stores.length >= Number(query.get("storeId"))) {
            setStoreId(Number(query.get("storeId")))
        }

        if (storeInfo.stores !== undefined && query.get("storeId") !== undefined && query.get("storeId") !== null) {
            setStore(storeInfo.stores[storeId]);
        }

    }, [query.get("storeId")]);

    useEffect(() => { //가게 정보 변경시 적용되는 부분
        if (Object.keys(store).length !== 0) {
            setStoreInfo({
                ...storeInfo,
                stores: store
            });
        }
        console.log(store);
    }, [store]);

    let navOnClick = () => {
        setNavStatus(!navStatus);
    }

    let onClickNav = (kind) => {
        navigate("/manage/store?kind=" + kind);
    }

    // axios loading
    tokenStoreAdminAxios.interceptors.request.use(
        async config => {
            setLoading(true);
            return config;
        },
        error => {
            setLoading(false)
            return Promise.reject(error);
        }
    )
    tokenStoreAdminAxios.interceptors.response.use(
        async config => {
            setLoading(false)
            return config;
        },
        async err => {
            setLoading(false)
            return Promise.reject(err);
        },
    );

    return (<>
        <div className={"manage-container"}>
            {
                loading ? (<Loading/>) : (<></>)
            }
            <div
                className={"manage-left-nav animate__animated animate__slideInLeft " +
                    (navStatus === true ? '' : 'nav-close animate__slideInRight')}>
                <div style={{
                    position: "absolute",
                    left: "100%",
                    width: "24px",
                    height: "24px"
                }}>
                    <div
                        className={"nav-btn animate__animated " + (navStatus === true ? '' : 'animate__slideInRight')}
                        onClick={navOnClick}>
                        {
                            navStatus === true ? (<img
                                className={"animate__animated " + (navStatus === true ? 'animate__rotateIn' : '')}
                                src={chevronLeft} style={{
                                width: "100%",
                                height: "100%"
                            }}/>) : (
                                <img
                                    src={chevronRight} style={{
                                    width: "100%",
                                    height: "100%"
                                }}/>
                            )
                        }
                    </div>
                </div>
                <div className={"manage-nav-container"}>
                    <div className={"nav-list"}>
                        <div className={"nav-list-top"}>
                            <img src={logo} alt={"logo"}/>
                        </div>
                        <div className={"nav-list-card"}>
                            <div className={"card-container " + (kindStatus === "Home" ? ('active') : (''))}
                                 onClick={() => {
                                     onClickNav("Home")
                                 }}>
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
                            <div className={"card-container " + (kindStatus === "orderStart" ? ('active') : (''))}
                                 onClick={() => {
                                     onClickNav("orderStart")
                                 }}>
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
                            <div className={"card-container " + (kindStatus === "orderHistory" ? ('active') : (''))}
                                 onClick={() => {
                                     onClickNav("orderHistory")
                                 }}>
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
                            <div className={"card-container " + (kindStatus === "Category" ? ('active') : (''))}
                                 onClick={() => {
                                     onClickNav("Category")
                                 }}>
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
                            <div className={"card-container " + (kindStatus === "Menu" ? ('active') : (''))}
                                 onClick={() => {
                                     onClickNav("Menu")
                                 }}>
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
                            <div className={"card-container " + (kindStatus === "sideCategory" ? ('active') : (''))}
                                 onClick={() => {
                                     onClickNav("sideCategory")
                                 }}>
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
                            <div className={"card-container " + (kindStatus === "sideMenu" ? ('active') : (''))}
                                 onClick={() => {
                                     onClickNav("sideMenu")
                                 }}>
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
                            <div className={"card-container " + (kindStatus === "Info" ? ('active') : (''))}
                                 onClick={() => {
                                     onClickNav("Info")
                                 }}>
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
                            <div className={"card-container " + (kindStatus === "Setting" ? ('active') : (''))}
                                 onClick={() => {
                                     onClickNav("Setting")
                                 }}>
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
            <Outlet/>
        </div>
    </>);
}
export default ManageNav;