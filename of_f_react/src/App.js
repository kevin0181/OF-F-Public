import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from "./User/Login";
import {CookiesProvider, useCookies} from "react-cookie";
import "./styles/css/default.css"
import Header from "./components/header/Header";
import Main from "./components/main/Main";
import SignUp from "./User/SignUp";
import FindId from "./User/FindId";
import FindPwd from "./User/FindPwd";
import {RecoilRoot} from "recoil";
import ManagementLogin from "./management/ManagementLogin";
import PrivateRouter from "./components/PrivateRouter";
import ManageDashBoard from "./management/ManageDashBoard";
import ErrorPage from "./components/exception/ErrorPage";

function App() {

    const loginStatus = localStorage.getItem("l-st")
    const [cookies, setCookie, removeCookie] = useCookies(['accessToken']);

    return (
        <RecoilRoot>
            <CookiesProvider>
                <BrowserRouter>
                    <div className={"container"}>
                        <Routes>
                            <Route element={<Header/>}>
                                <Route path={"/*"} element={<Main/>}/>
                            </Route>
                            <Route path={"/login"} element={<Login/>}/>
                            <Route path={"/signUp"} element={<SignUp/>}/>
                            <Route path={"/find/id"} element={<FindId/>}/>
                            <Route path={"/find/pwd"} element={<FindPwd/>}/>
                            <Route path={"/error/:code"} element={<ErrorPage/>}/>

                            {/* ---------- store ---------- */}
                            <Route path={"/manage/login"} element={<ManagementLogin/>}/>
                            <Route element={<PrivateRouter loginStatus={loginStatus} cookies={cookies}/>}>
                                <Route path={"/manage/store"} element={<ManageDashBoard/>}/>
                            </Route>
                        </Routes>
                    </div>
                </BrowserRouter>
            </CookiesProvider>
        </RecoilRoot>
    );
}

export default App;
