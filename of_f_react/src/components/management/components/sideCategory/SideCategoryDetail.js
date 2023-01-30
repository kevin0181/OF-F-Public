import {useEffect, useState} from "react";
import {tokenStoreAdminAxios} from "../../../../Config/customStoreAdminAjax";
import {useRecoilState} from "recoil";
import {selectStoreInfoRecoil} from "../../../../store/management/storeInfo";
import {useQuery} from "../../../../Config/getQuery";
import {useNavigate} from "react-router-dom";

let SideCategoryDetail = ({sideCategory}) => {

    const [store, setStore] = useRecoilState(selectStoreInfoRecoil);
    const query = useQuery();

    const navigate = useNavigate();

    const [sideCategoryDetail, setSideCategoryDetail] = useState({
        seq: "",
        name: "",
        status: false,
        storeMenus: [],
        storeSeq: ""
    });

    useEffect(() => {
        if (sideCategory !== undefined) {
            setSideCategoryDetail(sideCategory);
        }
    }, [sideCategory]);

    let onChangeSideCategoryDetail = (e) => {
        setSideCategoryDetail({
            ...sideCategoryDetail,
            [e.target.name]: e.target.value
        })
    }

    let onChangeSideCategoryStatusToggle = (e) => {
        setSideCategoryDetail({
            ...sideCategoryDetail,
            status: !sideCategoryDetail.status
        })
    }

    let onClickSideCategoryUpdate = () => {

        tokenStoreAdminAxios({
            method: "POST",
            url: "/api/v1/store/admin/side/category?status=update",
            data: sideCategoryDetail
        }).then(res => {
            let resData = res.data.data;
            let updateSideCategories = [...store.storeSideCategories];

            updateSideCategories[Number(query.get("f"))] = resData;

            setStore({
                ...store,
                storeSideCategories: updateSideCategories
            });

        }).catch(err => {
            console.error(err);
            alert("카테고리를 수정할 수 없습니다.");
            return;
        });
    }

    let onClickSideCategoryDelete = () => {
        // eslint-disable-next-line no-restricted-globals
        if (confirm(sideCategoryDetail.name + "를 삭제하시겠습니까?")) {
            tokenStoreAdminAxios({
                method: "POST",
                url: "/api/v1/store/admin/side/category?status=delete",
                data: sideCategoryDetail
            }).then(res => {

                console.log(res.data.data);

                let deleteAfterSideCategories = store.storeSideCategories.filter(data => {
                    return data.seq !== res.data.data.seq;
                });

                setStore({
                    ...store,
                    storeSideCategories: deleteAfterSideCategories
                });

                navigate("/manage/store?kind=sideCategory");

            }).catch(err => {
                console.error(err);
                alert("카테고리 삭제를 실패했습니다.");
                return;
            });
        } else {
            return;
        }

    }


    return (
        <div className={"detail-container"}>
            <div className={"main-container2-top"}>
                <h2>사이드 카테고리 상세</h2>
            </div>
            <div className={"main-container2-body"}>
                <div>
                    <div className={"add-input-part"}>
                        <span>이름</span>
                        <input className={"m-input"} type={"text"} name={"name"} value={sideCategoryDetail.name || ""}
                               onChange={onChangeSideCategoryDetail}/>
                    </div>
                    <div className={"add-input-part position-left"} style={{
                        padding: "0px 10px"
                    }}>
                        <div>
                            <label className="switch">
                                <input type="checkbox" name={"status"} checked={sideCategoryDetail.status || false}
                                       onChange={onChangeSideCategoryStatusToggle}/>
                                <span className="slider round"></span>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div className={"main-container2-footer"}>
                <div>
                    <div className={"main-btn-false m-f-1 position-center"}>
                        <div onClick={onClickSideCategoryDelete}>
                            <p>삭제하기</p>
                        </div>
                    </div>
                    <div className={"main-btn-true m-f-1 position-center"}>
                        <div onClick={onClickSideCategoryUpdate}>
                            <p>수정하기</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default SideCategoryDetail;