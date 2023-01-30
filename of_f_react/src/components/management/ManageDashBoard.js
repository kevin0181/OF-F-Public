import "./../../styles/css/management/manageDashBoard.css";
import {useQuery} from "../../Config/getQuery";
import {useEffect, useState} from "react";
import Home from "./main/Home";
import {useRecoilState} from "recoil";
import navStatusState from "../../store/management/navStatus";
import 'animate.css';
import Category from "./main/Category";
import Menu from "./main/Menu";
import SideCategoryModal from "./components/modal/SideCategoryModal";
import SideCategory from "./main/SideCategory";
import SideMenu from "./main/SideMenu";


let ManageDashBoard = () => {
    const query = useQuery();

    const [kindStatus, setKindStatus] = useState("");

    const [navStatus, setNavStatus] = useRecoilState(navStatusState);

    useEffect(() => {
        setKindStatus(query.get("kind"));
    }, [query]);

    const MainPage = () => {
        switch (kindStatus) {
            case null:
                return <Home/>
            case "Home":
                return <Home/>
            case "orderStart":
                return <></>
            case "orderHistory":
                return <></>
            case "Category":
                return <Category/>
            case "Menu":
                return <Menu/>
            case "sideCategory":
                return <SideCategory/>
            case "sideMenu":
                return <SideMenu/>
            case "Info":
                return <></>
            case "Setting":
                return <></>
        }
    }

    return (<>
        <div
            className={"manage-dash-board-container animate__animated " + (navStatus === true ? 'animate__backInLeft' : '')}>
            <MainPage/>
        </div>
    </>);
}
export default ManageDashBoard;