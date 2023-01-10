import "./../../styles/css/management/manageDashBoard.css";
import {useQuery} from "../../Config/getQuery";
import {useEffect, useState} from "react";

let ManageDashBoard = () => {
    const query = useQuery();

    const [kindStatus, setKindStatus] = useState("");

    useEffect(() => {
        setKindStatus(query.get("kind"));
    }, [query]);

    return (<>
        <div className={"manage-dash-board-container"}>
        </div>
    </>);
}
export default ManageDashBoard;