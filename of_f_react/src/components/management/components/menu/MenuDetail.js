import {useRecoilState} from "recoil";
import {selectStoreInfoRecoil} from "../../../../store/management/storeInfo";
import {useQuery} from "../../../../Config/getQuery";
import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import {tokenStoreAdminAxios} from "../../../../Config/customStoreAdminAjax";

let MenuDetail = ({menu}) => {


    const [store, setStore] = useRecoilState(selectStoreInfoRecoil);
    const query = useQuery();

    const navigate = useNavigate();

    const [menuDetail, setMenuDetail] = useState({
        seq: "",
        name: "",
        status: false,
        storeMenus: [],
        storeSeq: ""
    });

    useEffect(() => {
        if (menu !== undefined) {
            setMenuDetail(menu);
        }
    }, [menu]);

    useEffect(() => {
        console.log(menuDetail);
    }, [menuDetail]);

    let onChangeCategoryDetail = (e) => {
        setMenuDetail({
            ...menuDetail,
            [e.target.name]: e.target.value
        })
    }

    let onChangeCategoryStatusToggle = (e) => {
        setMenuDetail({
            ...menuDetail,
            status: !menuDetail.status
        })
    }

    let onClickCategoryUpdate = () => {

        tokenStoreAdminAxios({
            method: "POST",
            url: "/api/v1/store/admin/category?status=update",
            data: menuDetail
        }).then(res => {
            let resData = res.data.data;
            let updateCategories = [...store.storeCategories];

            updateCategories[Number(query.get("f"))] = resData;

            setStore({
                ...store,
                storeCategories: updateCategories
            });

        }).catch(err => {
            console.error(err);
            alert("카테고리를 수정할 수 없습니다.");
            return;
        });
    }

    let onClickCategoryDelete = () => {
        // eslint-disable-next-line no-restricted-globals
        if (confirm(menuDetail.name + "를 삭제하시겠습니까?")) {
            tokenStoreAdminAxios({
                method: "POST",
                url: "/api/v1/store/admin/category?status=delete",
                data: menuDetail
            }).then(res => {
                let deleteAfterCategories = store.storeCategories.filter(data => {
                    return data.seq !== res.data.data.seq;
                });

                setStore({
                    ...store,
                    storeCategories: deleteAfterCategories
                });

                navigate("/manage/store?kind=Category");

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
                <h2>메뉴 상세</h2>
            </div>
            <div className={"main-container2-body"}>
                <div>
                    <div className={"add-input-part"}>
                        <span>이름</span>
                        <input className={"m-input"} type={"text"} name={"name"} value={menuDetail.name || ""}
                               onChange={onChangeCategoryDetail}/>
                    </div>
                    <div className={"add-input-part position-left"} style={{
                        padding: "0px 10px"
                    }}>
                        <div>
                            <label className="switch">
                                <input type="checkbox" name={"status"} checked={menuDetail.status || false}
                                       onChange={onChangeCategoryStatusToggle}/>
                                <span className="slider round"></span>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div className={"main-container2-footer"}>
                <div>
                    <div className={"main-btn-false m-f-1 position-center"}>
                        <div onClick={onClickCategoryDelete}>
                            <p>삭제하기</p>
                        </div>
                    </div>
                    <div className={"main-btn-true m-f-1 position-center"}>
                        <div onClick={onClickCategoryUpdate}>
                            <p>수정하기</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default MenuDetail;