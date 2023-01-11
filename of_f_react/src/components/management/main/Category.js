import "./../../../styles/css/management/main.css";
import "./../../../styles/css/management/category.css";
import {useQuery} from "../../../Config/getQuery";
import {useNavigate} from "react-router-dom";


let Category = () => {

    const query = useQuery();

    const navigate = useNavigate();

    return (
        <div className={"manage-main-container"}>
            <div className={"f-line m-scroll "}>
                <div>
                    <div className={"name-card"}>

                    </div>
                    <div className={"name-card"}>

                    </div>
                    <div className={"name-card"}>

                    </div>
                    <div className={"name-card"}>

                    </div>
                </div>
            </div>
            {
                query.get("c") !== null ? (
                    <div
                        className={"c-line m-scroll animate__animated " + (query.get("c") !== null ? 'animate__slideInLeft' : '')}>
                        <div>

                        </div>
                    </div>) : (
                    <></>
                )
            }
            {
                query.get("l") !== null ? (
                    <div
                        className={"l-line m-scroll animate__animated " + (query.get("l") !== null ? 'animate__slideInLeft' : '')}>
                        <div>

                        </div>
                    </div>
                ) : (<></>)
            }
        </div>
    )
}

export default Category;