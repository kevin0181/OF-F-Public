import {useRecoilState} from "recoil";
import {selectStoreInfoRecoil} from "../../../store/storeInfo";
import storeIdState from "../../../store/storeId";
import {useEffect, useState} from "react";
import {useQuery} from "../../../Config/getQuery";
import {useNavigate} from "react-router-dom";
import {ReactComponent as PlusCircle} from "../../../assets/icon/plus-circle.svg";
import AddMenu from "../components/menu/AddMenu";

let Menu = () => {

    const [store, setStore] = useRecoilState(selectStoreInfoRecoil); //선택된 가게 정보

    const [storeId, setStoreId] = useRecoilState(storeIdState); // 선택된 가게 id

    const [categories, setCategories] = useState([]);

    useEffect(() => {

        if (Object.keys(store).length !== 0) { //key가 존재하는지 확인하고 있으면 데이터 넣기
            setCategories(store.storeCategories);
        }

    }, [store]);

    const query = useQuery();

    const navigate = useNavigate();

    return (
        <div className={"manage-main-container"}>
            <div className={"f-line m-scroll "}>
                <div>
                    {
                        categories.map((data, index) => (
                            <div
                                className={"name-card "}
                                key={index}>
                                <div className={"name-card-btn"}>
                                    <div
                                        className={"name-card-part " + (query.get("f") === String(index) ? 'active' : '')}
                                        onClick={() => {
                                            navigate(`/manage/store?kind=${query.get("kind")}&f=${index}`)
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
                query.get("f") !== null ? (
                    <div
                        className={"c-line m-scroll animate__animated " + (query.get("f") !== null ? 'animate__slideInLeft' : '')}>
                        <div>
                            {
                                categories.length !== 0 && categories[Number(query.get("f"))] !== undefined ? (
                                    <>
                                        <div className={"name-card "}>
                                            <div className={"name-card-btn"}>
                                                <div
                                                    className={"name-card-part " + (query.get("status") === 'add' ? 'active addIcon' : '')}
                                                    onClick={() => {
                                                        navigate(`/manage/store?kind=Menu&f=${query.get("f")}&status=add`);
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
                                            categories[Number(query.get("f"))].storeMenus.map((data, index) => (
                                                <div
                                                    className={"name-card "}
                                                    key={index}>
                                                    <div className={"name-card-btn"}>
                                                        <div
                                                            className={"name-card-part " + (query.get("c") === String(index) ? 'active' : '')}
                                                            onClick={() => {
                                                                navigate(`/manage/store?kind=${query.get("kind")}&f=${query.get("f")}&c=${index}`);
                                                            }}
                                                        >
                                                            <p>{data.name}</p>
                                                        </div>
                                                    </div>
                                                    <div style={{
                                                        width: "3%"
                                                    }}>
                                                        {
                                                            query.get("c") === String(index) ? (
                                                                <div className={"name-card-active"}></div>) : (
                                                                <></>
                                                            )
                                                        }
                                                    </div>
                                                </div>
                                            ))
                                        }
                                    </>
                                ) : (
                                    <></>
                                )
                            }
                        </div>
                    </div>) : (
                    <></>
                )
            }
            {
                query.get("c") !== null ? (
                    <div
                        className={"l-line m-scroll animate__animated " + (query.get("c") !== null ? 'animate__slideInLeft' : '')}>
                        <div>
                            메뉴123
                        </div>
                    </div>
                ) : (<></>)
            }

            {
                query.get("status") !== null & query.get("f") !== null ? (
                    <div
                        className={"l-line m-scroll"}>
                        <div
                            className={"main-container2 animate__animated " + (query.get("status") !== null ? 'animate__slideInLeft' : '')}>
                            <AddMenu/>
                        </div>
                    </div>) : (
                    <></>
                )
            }
        </div>
    )
}

export default Menu;