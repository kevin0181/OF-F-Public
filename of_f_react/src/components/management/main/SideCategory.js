import {useQuery} from "../../../Config/getQuery";
import {useNavigate} from "react-router-dom";
import {useRecoilState} from "recoil";
import {selectStoreInfoRecoil} from "../../../store/management/storeInfo";
import {useEffect, useState} from "react";
import {ReactComponent as PlusCircle} from "../../../assets/icon/plus-circle.svg";
import {ReactComponent as CheckCircle} from "../../../assets/icon/check-circle.svg";
import {ReactComponent as XCircle} from "../../../assets/icon/x-circle.svg";
import AddSideCategory from "../components/sideCategory/AddSideCategory";
import SideCategoryDetail from "../components/sideCategory/SideCategoryDetail";

let SideCategory = () => {

    const query = useQuery();

    const navigate = useNavigate();

    const [store, setStore] = useRecoilState(selectStoreInfoRecoil);

    const [sideCategories, setSideCategories] = useState([]);
    const [sideCategory, setSideCategory] = useState({});

    useEffect(() => {
        if (Object.keys(store).length !== 0) { //key가 존재하는지 확인하고 있으면 데이터 넣기
            setSideCategories(store.storeSideCategories);
        }

    }, [store]);

    useEffect(() => {
        setSideCategory(sideCategories[Number(query.get("f"))]);
    }, [query])

    return (
        <div className={"manage-main-container"}>
            <div className={"f-line m-scroll "}>
                <div>
                    <div className={"name-card "}>
                        <div className={"name-card-btn"}>
                            <div
                                className={"name-card-part " + (query.get("status") === 'add' ? 'active addIcon' : '')}
                                onClick={() => {
                                    navigate("/manage/store?kind=sideCategory&status=add");
                                }}>
                                <PlusCircle/>
                            </div>
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
                    {
                        sideCategories.map((data, index) => (
                            <div
                                className={"name-card "} id={index + "-category"}
                                key={index}>
                                <div className={"name-card-btn"}>
                                    <div
                                        className={"name-card-part " + (query.get("f") === String(index) ? 'active' : '')}
                                        onClick={() => {
                                            navigate("/manage/store?kind=sideCategory&f=" + index)
                                        }}
                                    >
                                        <p>{data.name}</p>
                                    </div>
                                </div>
                                <div style={{
                                    width: "3%"
                                }}>
                                    {
                                        query.get("f") === String(index) ? (
                                            <div className={"name-card-active"}>
                                            </div>) : (
                                            <></>
                                        )
                                    }
                                </div>
                            </div>
                        ))
                    }

                </div>
            </div>
            {
                query.get("f") !== null & query.get("status") === null ? (
                    <div
                        className={"l-line m-scroll m-70"}>
                        <div
                            className={"main-container2 m-100 animate__animated " + (query.get("f") !== null ? 'animate__slideInLeft' : '')}>
                            <div>
                                {
                                    sideCategory !== undefined && sideCategory.status ? (
                                        <div className={"category-status"}>
                                            <CheckCircle/>
                                            <p>Active...</p>
                                        </div>
                                    ) : (
                                        <div className={"category-status"}>
                                            <XCircle/>
                                            <p className={"warning-p"}>Disabled...</p>
                                        </div>
                                    )
                                }
                                <SideCategoryDetail sideCategory={sideCategory}/>
                            </div>
                        </div>
                    </div>) : (
                    <></>
                )
            }

            {/* --------------------------- 사이드 카테고리 추가 --------------------------*/}
            {
                query.get("status") !== null & query.get("f") === null ? (
                    <div
                        className={"l-line m-scroll m-70"}>
                        <div
                            className={"main-container2 m-100 animate__animated " + (query.get("status") !== null ? 'animate__slideInLeft' : '')}>
                            <AddSideCategory/>
                        </div>
                    </div>) : (
                    <></>
                )
            }
        </div>
    )
}

export default SideCategory;