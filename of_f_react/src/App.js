import {BrowserRouter, Navigate, Outlet, Route, Routes} from "react-router-dom";
import Login from "./User/Login";
import {CookiesProvider, useCookies} from "react-cookie";
import "./styles/css/default.css"
import Header from "./components/header/Header";
import Main from "./components/main/Main";
import SignUp from "./User/SignUp";
import FindId from "./User/FindId";
import FindPwd from "./User/FindPwd";
import {RecoilRoot} from "recoil";
import ManagementLogin from "./components/management/ManagementLogin";
import PrivateRouter from "./components/PrivateRouter";
import ManageDashBoard from "./components/management/ManageDashBoard";
import ErrorPage from "./components/exception/ErrorPage";
import ManageNav from "./components/management/components/ManageNav";
import OrderStoreCheck from "./components/order/OrderStoreCheck";
import OrderStoreSearch from "./components/order/OrderStoreSearch";
import StoreOrder from "./components/order/component/StoreOrder";
import StoreOrderHeader from "./components/order/component/StoreOrderHeader";
import MenuBasketList from "./components/order/component/MenuBasketList";
import PayInfo from "./components/order/component/PayInfo";
import PayRedirect from "./components/order/component/PayRedirect";
import {socket, SocketContext} from "./components/management/Socket";
import Socket2 from "./components/Socket2";

function App() {

    const loginStatus = localStorage.getItem("l-st")
    const [cookies, setCookie, removeCookie] = useCookies(['accessToken']);

    return (
        <SocketContext.Provider value={socket}>
            <RecoilRoot>
                <CookiesProvider>
                    <BrowserRouter>
                        <div className={"container"}>
                            <Routes>
                                <Route element={<Header/>}>
                                    <Route path={"/"} element={<Main/>}/>
                                </Route>
                                <Route path={"/login"} element={<Login/>}/>
                                <Route path={"/signUp"} element={<SignUp/>}/>
                                <Route path={"/find/id"} element={<FindId/>}/>
                                <Route path={"/find/pwd"} element={<FindPwd/>}/>
                                <Route path={"/error/:code"} element={<ErrorPage/>}/>

                                {/*socket */}
                                <Route path={"/socket"} element={<Socket2/>}/>

                                {/*-------------- order ------------*/}
                                <Route path={"/store"} element={<Outlet/>}>
                                    <Route path={"*"} index element={<Navigate to={"/store/search"}/>}/>
                                    <Route path={"search"} element={<OrderStoreSearch/>}/> {/*검색*/}
                                    <Route path={":storeId"}>  {/*정상적인 가게 접근인지 확인*/}
                                        <Route path={"*"} index element={<Navigate to={"/store/search"}/>}/>
                                        <Route path={":qrId"} element={<OrderStoreCheck/>}>
                                            <Route path={"basket"} element={<MenuBasketList/>}/>
                                            <Route path={"payInfo"} element={<PayInfo/>}/>
                                            <Route element={<StoreOrderHeader/>}>
                                                <Route path={"main"} element={<StoreOrder/>}/>
                                            </Route>
                                        </Route>
                                        <Route path={"pay"} element={<Outlet/>}>  {/*결제 후*/}
                                            <Route path={"*"} index element={<Navigate to={"/"}/>}/>
                                            <Route path={":qrId"} element={<Outlet/>}>
                                                <Route path={"*"} index element={<Navigate to={"/"}/>}/>
                                                <Route path={"redirect"} element={<PayRedirect/>}/>
                                            </Route>
                                        </Route>
                                    </Route>
                                </Route>

                                {/* ---------- management ---------- */}
                                <Route path={"/manage/login"} element={<ManagementLogin/>}/>
                                <Route element={<PrivateRouter loginStatus={loginStatus} cookies={cookies}/>}>
                                    <Route element={<ManageNav/>}>
                                        <Route path={"/manage/store"} element={<ManageDashBoard/>}/>
                                    </Route>
                                </Route>
                                <Route path={"/*"} element={<Navigate to={"/"}/>}/>
                            </Routes>
                        </div>
                    </BrowserRouter>
                </CookiesProvider>
            </RecoilRoot>
        </SocketContext.Provider>
    );
}

export default App;
