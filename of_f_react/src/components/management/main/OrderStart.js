import {useQuery} from "../../../Config/getQuery";
import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import "./../../../styles/css/management/main.css";
import "./../../../styles/css/management/category.css";

let OrderStart = () => {
    const query = useQuery();

    const navigate = useNavigate();

    useEffect(() => {

    }, []);

    return (
        <div className={"manage-main-container"}>
            <div className={"f-line m-scroll "}>
                <div>
                    <div className={"name-card "}>
                        <div>
                            <button>시작</button>
                            <button>종료</button>
                        </div>
                        <div style={{
                            width: "3%"
                        }}>
                            {
                                query.get("status") === 'add' ? (
                                    <div className={"name-card-active"}>
                                    </div>) : (
                                    <></>
                                )
                            }
                        </div>
                    </div>
                </div>
            </div>
            <div
                className={"l-line m-scroll m-70"}>
                <div
                    className={"main-container2 m-100 animate__animated " + (query.get("f") !== null ? 'animate__slideInLeft' : '')}>
                    <div>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default OrderStart;