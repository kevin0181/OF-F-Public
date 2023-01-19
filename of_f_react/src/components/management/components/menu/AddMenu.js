import {ReactComponent as ExclamationCircle} from "../../../../assets/icon/exclamation-circle.svg";
import {useEffect, useRef, useState} from "react";
import {useRecoilState} from "recoil";
import {selectStoreInfoRecoil} from "../../../../store/management/storeInfo";
import {ReactComponent as Photograph} from "./../../../../assets/icon/photograph.svg";
import "./../../../../styles/css/management/menu.css"
import {useNavigate} from "react-router-dom";
import {useQuery} from "../../../../Config/getQuery";


let AddMenu = () => {

    const navigate = useNavigate();

    const query = useQuery();

    const [store, setStore] = useRecoilState(selectStoreInfoRecoil); //선택된 가게 정보
    const imgRef = useRef();

    const [demoImgUrl, setDemoImgUrl] = useState("");

    const [sideCategory, setSideCategory] = useState([]);

    useEffect(() => {
        if (store.storeSideCategories !== undefined) {
            setSideCategory(store.storeSideCategories);
        }
    }, [store]);

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

    let onClickImg = () => {
        imgRef.current.click();
    }

    let onChangeUploadImg = (e) => {
        setDemoImgUrl(URL.createObjectURL(e.target.files[0]));
    }

    let onClickAddMenuBtn = () => {

    }

    let onClickSideModal = () => {

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
                        <input className={"m-input m-60"} type={"text"} name={"price"} onChange={onChangeAddMenu}
                               value={addMenu.name}/>
                    </div>
                    <div className={"add-side-part"}>
                        <span>사이드<br/>카테고리</span>
                        <div>
                            <div className={"side-select-list-part"}>
                                {
                                    sideCategory.map((data, index) => (
                                        <div key={index}>
                                            <div className={"side-mini-select"}>
                                                <div className={"side-mini-top"}>
                                                    <div>{data.name}</div>
                                                </div>
                                                <div className={"side-mini-body"}>

                                                </div>
                                            </div>
                                        </div>
                                    ))
                                }
                            </div>
                        </div>
                    </div>
                    <div className={"add-img-part"}>
                        <span style={{
                            margin: "0px 0px 3px 0px"
                        }}>이미지</span>
                        <div className={"add-img-container"} onClick={onClickImg}>
                            {
                                demoImgUrl !== "" ? (<>
                                    <div>
                                        <img alt={"view img"} src={demoImgUrl}/>
                                    </div>
                                </>) : (
                                    <>
                                        <Photograph/>
                                    </>
                                )
                            }
                        </div>
                        <input type={"file"} ref={imgRef} className={"add-img-input"}
                               accept='image/*'
                               onChange={onChangeUploadImg}
                               name='storeMenuImgs'/>
                    </div>
                    <div className={"add-input-part position-left"} style={{
                        padding: "10px"
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
                                name: "",
                                price: "",
                            })
                        }}>
                            <p>되돌리기</p>
                        </div>
                    </div>
                    <div className={"main-btn-true m-f-1 position-center"}>
                        <div onClick={onClickAddMenuBtn}>
                            <p>추가하기</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddMenu;