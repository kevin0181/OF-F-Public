import "./../../../styles/css/management/main.css";
import "./../../../styles/css/management/category.css";
import {useQuery} from "../../../Config/getQuery";
import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import {useRecoilState} from "recoil";
import storeInfoState from "../../../store/storeInfo";
import storeIdState from "../../../store/storeId";
import {ReactComponent as CheckCircle} from "./../../../assets/icon/check-circle.svg";
import {ReactComponent as PlusCircle} from "./../../../assets/icon/plus-circle.svg";
import {ReactComponent as XCircle} from "./../../../assets/icon/x-circle.svg";
import {ReactComponent as ExclamationCircle} from "./../../../assets/icon/exclamation-circle.svg";


let Category = () => {

    const query = useQuery();

    const navigate = useNavigate();

    const [storeInfo, setStoreInfo] = useRecoilState(storeInfoState);

    const [storeId, setStoreId] = useRecoilState(storeIdState); // 선택된 가게 정보

    const [categories, setCategories] = useState([]);
    const [category, setCategory] = useState({});

    useEffect(() => {

        if (Object.keys(storeInfo).length !== 0) { //key가 존재하는지 확인하고 있으면 데이터 넣기
            setCategories(storeInfo.stores[storeId].storeCategories);
        }

    }, [storeInfo]);

    useEffect(() => {
        setCategory(categories[Number(query.get("f"))]);
        console.log(category)
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
                                    navigate("/manage/store?kind=Category&status=add");
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
                        categories.map((data, index) => (
                            <div
                                className={"name-card "}
                                key={index}>
                                <div className={"name-card-btn"}>
                                    <div
                                        className={"name-card-part " + (query.get("f") === String(index) ? 'active' : '')}
                                        onClick={() => {
                                            navigate("/manage/store?kind=Category&f=" + index)
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
                            className={"main-container m-100 animate__animated " + (query.get("f") !== null ? 'animate__slideInLeft' : '')}>
                            <div>
                                {
                                    category !== undefined && category.status ? (
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
                            </div>
                        </div>
                    </div>) : (
                    <></>
                )
            }

            {/* --------------------------- 카테고리 추가 --------------------------*/}
            {
                query.get("status") !== null & query.get("f") === null ? (
                    <div
                        className={"l-line m-scroll m-70"}>
                        <div
                            className={"main-container m-100 animate__animated " + (query.get("status") !== null ? 'animate__slideInLeft' : '')}>
                            <div>
                                <div className={"main-container-top"}>
                                    <h2>카테고리 추가</h2>
                                </div>
                                <div className={"main-container-body"}>
                                    <div>
                                        <div className={"category-add-input-part"}>
                                            <span>이름</span>
                                            <input className={"m-input"}/>
                                        </div>
                                        <div className={"category-add-input-part position-left"} style={{
                                            padding: "10px 0px"
                                        }}>
                                            <div>
                                                <label className="switch toggle-disable">
                                                    <input type="checkbox"/>
                                                    <span className="slider round"></span>
                                                </label>
                                            </div>
                                        </div>
                                        <div className={"category-add-input-part position-right"}>
                                            <ExclamationCircle/>
                                            <small>카테고리는 기본적으로 비활성화 상태로 생성됩니다.</small>
                                        </div>
                                    </div>
                                </div>
                                <div className={"main-container-footer"}>
                                    <div>
                                        <div className={"main-btn-false m-f-1 position-center"}>
                                            <div>
                                                <p>되돌리기</p>
                                            </div>
                                        </div>
                                        <div className={"main-btn-true m-f-1 position-center"}>
                                            <div>
                                                <p>추가하기</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>) : (
                    <></>
                )
            }
        </div>
    )
}

export default Category;