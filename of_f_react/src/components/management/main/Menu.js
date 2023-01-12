import {useRecoilState} from "recoil";
import storeInfoState from "../../../store/storeInfo";
import storeIdState from "../../../store/storeId";
import {useEffect, useState} from "react";
import {useQuery} from "../../../Config/getQuery";
import {useNavigate} from "react-router-dom";

let Menu = () => {

    const [storeInfo, setStoreInfo] = useRecoilState(storeInfoState);

    const [storeId, setStoreId] = useRecoilState(storeIdState); // 선택된 가게 정보

    const [category, setCategory] = useState([]);

    useEffect(() => {

        if (Object.keys(storeInfo).length !== 0) { //key가 존재하는지 확인하고 있으면 데이터 넣기
            setCategory(storeInfo.stores[storeId].storeCategories);
        }

    }, [storeInfo]);

    useEffect(() => {
        console.log(category);
        console.log(category[Number(query.get("f"))])
    }, [category]);

    const query = useQuery();

    const navigate = useNavigate();

    return (
        <div className={"manage-main-container"}>
            <div className={"f-line m-scroll "}>
                <div>
                    {
                        category.map((data, index) => (
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
                                category.length !== 0 && category[Number(query.get("f"))] !== undefined ? (
                                    <>
                                        {
                                            category[Number(query.get("f"))].storeMenus.map((data, index) => (
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

                        </div>
                    </div>
                ) : (<></>)
            }
        </div>
    )
}

export default Menu;