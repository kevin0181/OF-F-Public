import {ReactComponent as ExclamationCircle} from "../../../../../assets/icon/exclamation-circle.svg";
import {useEffect, useRef, useState} from "react";
import {useRecoilState} from "recoil";
import {selectStoreInfoRecoil} from "../../../../../store/management/storeInfo";
import {ReactComponent as Photograph} from "../../../../../assets/icon/photograph.svg";
import {ReactComponent as Plus} from "../../../../../assets/icon/plus.svg";
import {ReactComponent as Check} from "../../../../../assets/icon/check.svg";
import "../../../../../styles/css/management/menu.css"
import {useNavigate} from "react-router-dom";
import {useQuery} from "../../../../../Config/getQuery";
import {tokenStoreAdminAxios} from "../../../../../Config/customStoreAdminAjax";
import {resAddMenu, resAddSideMenu} from "../../../../../service/management/menu/menu";


let AddSideMenu = () => {

    const navigate = useNavigate();

    const query = useQuery();

    const [store, setStore] = useRecoilState(selectStoreInfoRecoil); //선택된 가게 정보
    const imgRef = useRef([]);

    const [demoImgUrl, setDemoImgUrl] = useState([]);


    useEffect(() => {

        if (Object.keys(store).length !== 0) {
            setAddSideMenu({
                ...addSideMenu,
                storeSideCategorySeq: store.storeSideCategories[Number(query.get("f"))].seq
            })
        }

    }, [store]);

    const [addSideMenu, setAddSideMenu] = useState({
        name: "",
        price: 0,
        seq: "",
        soldOutStatus: false,
        status: false,
        storeSideCategorySeq: "",
        storeSideImgs: []
    });

    let onChangeAddSideMenu = (e) => {
        setAddSideMenu({
            ...addSideMenu,
            [e.target.name]: e.target.value
        })
    }

    let onClickImg = () => {
        imgRef.current.click();
    }

    let onChangeUploadImg = (e) => {

        let newFileArr = [];

        for (let i = 0; i < e.target.files.length; i++) {
            newFileArr.push(URL.createObjectURL(e.target.files[i]));
        }

        setDemoImgUrl(newFileArr);

    }

    let onClickAddSideMenuBtn = () => {
        if (addSideMenu.name === "") {
            alert("이름을 입력해주세요.");
            return;
        }
        if (addSideMenu.price === "") {
            alert("가격을 입력해주세요.");
            return;
        }

        if (isNaN(addSideMenu.price)) {
            alert("가격은 숫자이어야합니다.");
            return;
        }

        if (addSideMenu.price.length > 7) {
            alert("가격의 최대 제한은 7글자 입니다.");
            return;
        }

        let formData = new FormData();

        formData.append("side-menu", new Blob([JSON.stringify(addSideMenu)], {type: "application/json"}));

        for (let i = 0; i < imgRef.current.files.length; i++) {
            formData.append("side-img", imgRef.current.files[i]);
        }

        tokenStoreAdminAxios.post('/api/v1/store/admin/side/menu?status=insert', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        }).then(async res => {
            let c_id = await resAddSideMenu(res, store, query, setStore); //메뉴 추가하는 함수
            navigate(`/manage/store?kind=${query.get("kind")}&f=${query.get("f")}&c=${c_id}`);
        }).catch(err => {
            alert(err.response.data.detail);
            return;
        })
    }


    return (
        <div>
            <div className={"main-container2-top"}>
                <h2>사이드 메뉴 추가</h2>
            </div>
            <div className={"main-container2-body"}>
                <div>
                    <div className={"add-input-part"}>
                        <span>이름</span>
                        <input className={"m-input m-60"} type={"text"} name={"name"} onChange={onChangeAddSideMenu}
                               value={addSideMenu.name}/>
                    </div>
                    <div className={"add-input-part"}>
                        <span>가격</span>
                        <input className={"m-input m-60"} type={"text"} name={"price"} onChange={onChangeAddSideMenu}
                               value={addSideMenu.price}/>
                    </div>
                    <div className={"add-img-part"}>
                        <span style={{
                            margin: "0px 0px 3px 0px"
                        }}>이미지</span>
                        <div className={"add-img-container m-scroll2"} onClick={onClickImg}>
                            {
                                demoImgUrl.length !== 0 ? (<>
                                    <div>
                                        {
                                            demoImgUrl.map((data, index) => (
                                                <img alt={"view img"} key={index} src={data}/>
                                            ))
                                        }
                                    </div>
                                </>) : (
                                    <>
                                        <Photograph/>
                                    </>
                                )
                            }
                        </div>
                        <input type={"file"} ref={imgRef} multiple={true} className={"add-img-input"}
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
                        <small>사이드 메뉴는 기본적으로 비활성화 상태로 생성됩니다.</small>
                    </div>
                </div>
            </div>
            <div className={"main-container2-footer"}>
                <div>
                    <div className={"main-btn-false m-f-1 position-center"}>
                        <div onClick={() => {
                            setAddSideMenu({
                                ...addSideMenu,
                                name: "",
                                price: 0,
                                storeSideImgs: []
                            })
                        }}>
                            <p>되돌리기</p>
                        </div>
                    </div>
                    <div className={"main-btn-true m-f-1 position-center"}>
                        <div onClick={onClickAddSideMenuBtn}>
                            <p>추가하기</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddSideMenu;