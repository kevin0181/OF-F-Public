import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from "./User/Login";
import {CookiesProvider} from "react-cookie";
import "./styles/css/default.css"
import Header from "./components/header/Header";
import Main from "./components/main/Main";

function App() {
    return (
        <CookiesProvider>
            <BrowserRouter>
                <div className={"container"}>
                    <Header/>
                    <Routes>
                        <Route path={"/"} element={<Main/>}/>
                        <Route path={"/login"} element={<Login/>}/>
                    </Routes>
                </div>
            </BrowserRouter>
        </CookiesProvider>
    );
}

export default App;
