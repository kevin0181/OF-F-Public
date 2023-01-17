import {ReactComponent as ExclamationCircle} from "../../../../assets/icon/exclamation-circle.svg";
import {useState} from "react";
import StoreCustomSelect from "../../../custom/StoreCustomSelect";
import {useRecoilState} from "recoil";
import {selectStoreInfoRecoil} from "../../../../store/storeInfo";
import {ReactComponent as Photograph} from "./../../../../assets/icon/photograph.svg";


let AddMenu = () => {

    const [store, setStore] = useRecoilState(selectStoreInfoRecoil); //선택된 가게 정보

    const [addMenu, setAddMenu] = useState({
        name: "",
        price: "",
        seq: "",
        soldOutStatus: false,
        status: false,
        storeCategorySeq: "",
        storeMSs: [],
        storeMenuImgs: []
    });

    let onChangeAddMenu = (e) => {
        setAddMenu({
            ...addMenu,
            [e.target.name]: e.target.value
        })
    }

    return (
        <div>
            <div className={"main-container2-top"}>
                <h2>메뉴 추가</h2>
            </div>
            <div className={"main-container2-body"}>
                <div>
                    <div className={"add-input-part"}>
                        <span>이름</span>
                        <input className={"m-input m-60"} type={"text"} name={"name"} onChange={onChangeAddMenu}
                               value={addMenu.name}/>
                    </div>
                    <div className={"add-input-part"}>
                        <span>가격</span>
                        <input className={"m-input m-60"} type={"text"} name={"name"} onChange={onChangeAddMenu}
                               value={addMenu.name}/>
                    </div>
                    <div className={"add-input-part"}>
                        <span>사이드<br/>카테고리</span>
                        <StoreCustomSelect
                            getData={store.storeSideCategories}/>
                    </div>
                    <div className={"add-img-part"}>
                        <span style={{
                            margin: "0px 0px 3px 0px"
                        }}>이미지</span>
                        <div className={"add-img-container"}>
                            <Photograph/>
                        </div>
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
                        <small>메뉴는 기본적으로 비활성화 상태로 생성됩니다.</small>
                    </div>
                </div>
            </div>
            <div className={"main-container2-footer"}>
                <div>
                    <div className={"main-btn-false m-f-1 position-center"}>
                        <div onClick={() => {
                            setAddMenu({
                                ...addMenu,
                                name: ""
                            })
                        }}>
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
    )
}

export default AddMenu;