import "./../../styles/css/management/manageDashBoard.css";
import {useQuery} from "../../Config/getQuery";
import {useEffect, useState} from "react";
import Home from "./main/Home";

let ManageDashBoard = () => {
    const query = useQuery();

    const [kindStatus, setKindStatus] = useState("");

    useEffect(() => {
        setKindStatus(query.get("kind"));
    }, [query]);

    const MainPage = () => {
        switch (kindStatus) {
            case "Home":
                return <Home/>
            case "orderStart":
                return <></>
            case "orderHistory":
                return <></>
            case "Category":
                return <></>
            case "Menu":
                return <></>
            case "sideCategory":
                return <></>
            case "sideMenu":
                return <></>
            case "Info":
                return <></>
            case "Setting":
                return <></>
        }
    }

    return (<>
        <div className={"manage-dash-board-container"}>
            <MainPage/>
        </div>
    </>);
}
export default ManageDashBoard;