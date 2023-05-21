import {ReactComponent as ExclamationCircle} from "../../../../assets/icon/exclamation-circle.svg";
import {useEffect, useState} from "react";
import {useRecoilState} from "recoil";
import {selectStoreInfoRecoil, storeInfoRecoil} from "../../../../store/management/storeInfo";
import {tokenStoreAdminAxios} from "../../../../Config/customStoreAdminAjax";

let AddSideCategory = () => {

    const [store, setStore] = useRecoilState(selectStoreInfoRecoil);

    const [addSideCategory, setAddSideCategory] = useState({
        storeSeq: "",
        name: "",
        status: false
    });

    useEffect(() => {
        if (Object.keys(store).length !== 0) {
            setAddSideCategory({
                ...addSideCategory,
                storeSeq: store.seq
            })
        }
    }, [store]);

    let onChangeAddSideCategory = (e) => {
        setAddSideCategory({
            ...addSideCategory,
            [e.target.name]: e.target.value
        })
    }

    let onClickAddSideCategory = () => {

        if (addSideCategory.storeSeq === "") {
            alert("가게를 선택해주세요.");
            return;
        }

        if (addSideCategory.name === null || addSideCategory.name === "") {
            alert("사이드 카테고리 이름을 입력해주세요.");
            return;
        }

        tokenStoreAdminAxios({
            method: "post",
            url: "/api/v1/store/admin/side/category?status=insert",
            data: addSideCategory
        }).then((res) => {

            let data = res.data.data;
            try {

                setStore({
                    ...store,
                    storeSideCategories: [
                        ...store.storeSideCategories,
                        {
                            name: data.name,
                            seq: data.seq,
                            status: data.status,
                            storeSeq: data.storeSeq,
                            storeSideMenus: []
                        }
                    ]
                });

            } catch (e) {
                console.log(e)
            }
        }).catch((e) => {
            console.log(e)
            alert("사이드 카테고리를 추가하지 못했습니다. 관리자에게 문의 주세요.");
        }).finally(() => {

            setAddSideCategory({ //카테고리 초기화
                ...addSideCategory,
                name: ""
            });

        })
    }

    return (
        <div>
            <div className={"main-container2-top"}>
                <h2>사이드 카테고리 추가</h2>
            </div>
            <div className={"main-container2-body"}>
                <div>
                    <div className={"add-input-part"}>
                        <span>이름</span>
                        <input className={"m-input"} type={"text"} name={"name"} onChange={onChangeAddSideCategory}
                               value={addSideCategory.name}/>
                    </div>
                    <div className={"add-input-part position-left"} style={{
                        padding: "0px 10px"
                    }}>
                        <div>
                            <label className="switch toggle-disable">
                                <input type="checkbox"/>
                                <span className="slider round"></span>
                            </label>
                        </div>
                    </div>
                    <div className={"add-input-part position-right"}>
                        <ExclamationCircle/>
                        <small>사이드 카테고리는 기본적으로 비활성화 상태로 생성됩니다.</small>
                    </div>
                </div>
            </div>
            <div className={"main-container2-footer"}>
                <div>
                    <div className={"main-btn-false m-f-1 position-center"}>
                        <div onClick={() => {
                            setAddSideCategory({
                                ...addSideCategory,
                                name: ""
                            })
                        }}>
                            <p>되돌리기</p>
                        </div>
                    </div>
                    <div className={"main-btn-true m-f-1 position-center"}>
                        <div onClick={onClickAddSideCategory}>
                            <p>추가하기</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default AddSideCategory;