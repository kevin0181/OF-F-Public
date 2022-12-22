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
                <Header/>
                <Routes>
                    <Route path={"/"} element={<Main/>}/>
                    <Route path={"/login"} element={<Login/>}/>
                </Routes>
            </BrowserRouter>
        </CookiesProvider>
    );
}

export default App;
