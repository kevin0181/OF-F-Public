import {ReactComponent as ExclamationCircle} from "../../../../assets/icon/exclamation-circle.svg";
import {useEffect, useState} from "react";
import {useRecoilState} from "recoil";
import storeIdState from "../../../../store/management/storeId";
import {selectStoreInfoRecoil, storeInfoRecoil} from "../../../../store/management/storeInfo";
import {tokenStoreAdminAxios} from "../../../../Config/customStoreAdminAjax";

let AddCategory = () => {

    const [store, setStore] = useRecoilState(selectStoreInfoRecoil);

    const [addCategory, setAddCategory] = useState({
        storeSeq: "",
        name: "",
        status: false
    });

    useEffect(() => {
        if (Object.keys(store).length !== 0) {
            setAddCategory({
                ...addCategory,
                storeSeq: store.seq
            })
        }
    }, [store]);

    let onChangeAddCategory = (e) => {
        setAddCategory({
            ...addCategory,
            [e.target.name]: e.target.value
        })
    }

    let onClickAddCategory = () => {

        if (addCategory.storeSeq === "") {
            alert("가게를 선택해주세요.");
            return;
        }

        if (addCategory.name === null || addCategory.name === "") {
            alert("카테고리 이름을 입력해주세요.");
            return;
        }

        tokenStoreAdminAxios({
            method: "post",
            url: "/api/v1/store/admin/category?status=insert",
            data: addCategory
        }).then((res) => {

            let data = res.data.data;
            try {

                setStore({
                    ...store,
                    storeCategories: [
                        ...store.storeCategories,
                        {
                            name: data.name,
                            seq: data.seq,
                            status: data.status,
                            storeSeq: data.storeSeq,
                            storeMenus: []
                        }
                    ]
                });

            } catch (e) {
                console.log(e)
            }
        }).catch((e) => {
            console.log(e)
            alert("카테고리를 추가하지 못했습니다. 관리자에게 문의 주세요.");
        }).finally(() => {

            setAddCategory({ //카테고리 초기화
                ...addCategory,
                name: ""
            });

        })
    }

    return (
        <div>
            <div className={"main-container2-top"}>
                <h2>카테고리 추가</h2>
            </div>
            <div className={"main-container2-body"}>
                <div>
                    <div className={"add-input-part"}>
                        <span>이름</span>
                        <input className={"m-input"} type={"text"} name={"name"} onChange={onChangeAddCategory}
                               value={addCategory.name}/>
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
                        <small>카테고리는 기본적으로 비활성화 상태로 생성됩니다.</small>
                    </div>
                </div>
            </div>
            <div className={"main-container2-footer"}>
                <div>
                    <div className={"main-btn-false m-f-1 position-center"}>
                        <div onClick={() => {
                            setAddCategory({
                                ...addCategory,
                                name: ""
                            })
                        }}>
                            <p>되돌리기</p>
                        </div>
                    </div>
                    <div className={"main-btn-true m-f-1 position-center"}>
                        <div onClick={onClickAddCategory}>
                            <p>추가하기</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default AddCategory;