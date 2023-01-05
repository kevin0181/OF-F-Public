import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from "./User/Login";
import {CookiesProvider} from "react-cookie";
import "./styles/css/default.css"
import Header from "./components/header/Header";
import Main from "./components/main/Main";
import SignUp from "./User/SignUp";
import FindId from "./User/FindId";
import FindPwd from "./User/FindPwd";
import {RecoilRoot} from "recoil";

function App() {
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
                        </Routes>
                    </div>
                </BrowserRouter>
            </CookiesProvider>
        </RecoilRoot>
    );
}

export default App;
