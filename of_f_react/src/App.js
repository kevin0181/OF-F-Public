import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from "./User/Login";
import {CookiesProvider} from "react-cookie";
import "./styles/css/default.css"
import Header from "./components/header/Header";
import Main from "./components/main/Main";
import SignUp from "./User/SignUp";

function App() {
    return (
        <CookiesProvider>
            <BrowserRouter>
                <div className={"container"}>
                    <Routes>
                        <Route element={<Header/>}>
                            <Route path={"/*"} element={<Main/>}/>
                        </Route>
                        <Route path={"/login"} element={<Login/>}/>
                        <Route path={"/signUp"} element={<SignUp/>}/>
                    </Routes>
                </div>
            </BrowserRouter>
        </CookiesProvider>
    );
}

export default App;
