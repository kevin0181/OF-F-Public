import {useRecoilState} from "recoil";
import {selectStoreInfoRecoil} from "../../../../../store/management/storeInfo";
import {useQuery} from "../../../../../Config/getQuery";
import {useNavigate} from "react-router-dom";
import {useEffect, useRef, useState} from "react";
import {tokenStoreAdminAxios} from "../../../../../Config/customStoreAdminAjax";
import {ReactComponent as Photograph} from "../../../../../assets/icon/photograph.svg";
import {ReactComponent as XBtn} from "../../../../../assets/icon/x.svg";
import {ReactComponent as PlusBtn} from "../../../../../assets/icon/plus.svg";
import {resAddMenu, resDeleteMenu, resUpdateMenu} from "../../../../../service/management/menu/menu";
import SideSelectVar from "./SideSelectVar";
import React from 'react';


let MenuDetail = ({menu}) => {


    const [store, setStore] = useRecoilState(selectStoreInfoRecoil);
    const query = useQuery();
    const imgRef = useRef([]);

    const navigate = useNavigate();

    const [menuDetail, setMenuDetail] = useState({
        seq: "",
        name: "",
        price: "",
        soldOutStatus: false,
        status: false,
        storeCategorySeq: "",
        storeMSs: [],
        storeMenuImgs: [],
    });

    const [demoImgUrl, setDemoImgUrl] = useState([]);

    const [sideVarStatus, setSideVarStatus] = useState(false);

    useEffect(() => {
        if (menu !== undefined && Object.keys(menu).length !== 0) {
            setMenuDetail(menu);
        }
    }, [menu]);

    useEffect(() => {
        console.log(menuDetail);
    }, [menuDetail]);

    let onChangeMenuDetail = (e) => {
        setMenuDetail({
            ...menuDetail,
            [e.target.name]: e.target.value
        })
    }

    let onChangeMenuStatusToggle = (e) => {
        setMenuDetail({
            ...menuDetail,
            status: !menuDetail.status
        })
    }

    let onChangeMenuSoldOutToggle = () => {
        setMenuDetail({
            ...menuDetail,
            soldOutStatus: !menuDetail.soldOutStatus
        })
    }

    let onClickMenuUpdate = () => { //메뉴 수정

        if (menuDetail.name === "") {
            alert("이름을 입력해주세요.");
            return;
        }
        if (menuDetail.price === "") {
            alert("가격을 입력해주세요.");
            return;
        }

        if (isNaN(menuDetail.price)) {
            alert("가격은 숫자이어야합니다.");
            return;
        }

        if (menuDetail.price.length > 7) {
            alert("가격의 최대 제한은 7글자 입니다.");
            return;
        }

        let formData = new FormData();

        formData.append("menu", new Blob([JSON.stringify(menuDetail)], {type: "application/json"}));

        for (let i = 0; i < imgRef.current.files.length; i++) {
            formData.append("img", imgRef.current.files[i]);
        }

        tokenStoreAdminAxios.post('/api/v1/store/admin/menu?status=update', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        }).then(async res => {
            let c_id = await resUpdateMenu(res, store, query, setStore); //메뉴 추가하는 함수
            navigate(`/manage/store?kind=${query.get("kind")}&f=${query.get("f")}&c=${c_id}`);
        }).catch(err => {
            console.error(err);
            alert("메뉴를 수정할 수 없습니다.");
            return;
        });
    }

    let onClickMenuDelete = () => { // 메뉴 삭제
        // eslint-disable-next-line no-restricted-globals
        if (confirm(menuDetail.name + "를 삭제하시겠습니까?")) {

            let formData = new FormData();

            formData.append("menu", new Blob([JSON.stringify(menuDetail)], {type: "application/json"}));

            tokenStoreAdminAxios({
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
                method: "POST",
                url: "/api/v1/store/admin/menu?status=delete",
                data: formData
            }).then(async res => {
                await resDeleteMenu(res, store, query, setStore);
                navigate(`/manage/store?kind=${query.get("kind")}&f=${query.get("f")}`);
            }).catch(err => {
                console.error(err);
                alert("메뉴 삭제를 실패했습니다.");
                return;
            });
        } else {
            return;
        }

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

    let deleteSideCategory = (index, data) => { //사이드 카테고리 삭제

        let name = data === null ? "지워진" : data.name;
        // eslint-disable-next-line no-restricted-globals
        if (confirm(name + " 사이드를 삭제하시겠습니까?")) {

            let deleteMenuDetail = menuDetail.storeMSs.filter((data, indexS) => {
                return index !== indexS
            });

            setMenuDetail({
                ...menuDetail,
                storeMSs: deleteMenuDetail
            });

        }
    }

    let deleteMenuImg = (seq) => { //메뉴 이미지 삭제
        // eslint-disable-next-line no-restricted-globals
        if (confirm("해당 이미지를 삭제하시겠습니까?")) {
            let deleteMenuImg = menuDetail.storeMenuImgs.filter(data => {
                return seq === data.seq
            });

            let afterMenuImg = menuDetail.storeMenuImgs.filter(data => {
                return seq !== data.seq
            });

            deleteMenuImg[0] = {
                ...deleteMenuImg[0],
                status: false
            }

            setMenuDetail({
                ...menuDetail,
                storeMenuImgs: [
                    ...afterMenuImg,
                    deleteMenuImg[0]
                ]
            });
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
                               onChange={onChangeMenuDetail}/>
                    </div>
                    <div className={"add-input-part"}>
                        <span>가격</span>
                        <input className={"m-input"} type={"text"} name={"price"} value={menuDetail.price || ""}
                               onChange={onChangeMenuDetail}/>
                    </div>

                    <div className={"add-side-part"}>
                        <span>사이드<br/>카테고리</span>
                        <div>
                            {
                                menuDetail.storeMSs !== undefined && menuDetail.storeMSs.length !== 0 ? (
                                    <div className={"side-select-list-part"}>
                                        {
                                            menuDetail.storeMSs.map((data, index) => ( //사이드 카테고리 부분
                                                <div key={index}>
                                                    <div
                                                        className={"side-mini-select"}>
                                                        <div className={"side-mini-top"}>
                                                            {
                                                                data.storeSideCategory === null || data.storeSideCategory === undefined ?
                                                                    (<div style={{
                                                                        color: "red"
                                                                    }}>
                                                                        지워진 사이드
                                                                    </div>) : (<div>{data.storeSideCategory.name}</div>)
                                                            }
                                                        </div>
                                                        <div className={"side-mini-body"}>
                                                            <div className={"side-mini-select-btn"}
                                                                 onClick={() => {
                                                                     deleteSideCategory(index, data.storeSideCategory)
                                                                 }}>
                                                                <XBtn/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            ))
                                        }
                                        <div>
                                            <div
                                                className={"side-mini-select"} onClick={() => {
                                                setSideVarStatus(!sideVarStatus);
                                            }}>
                                                <PlusBtn/>
                                            </div>
                                        </div>
                                    </div>
                                ) : (
                                    <>
                                        <div className={"non-side-plus-btn"}>
                                            <div
                                                className={"side-mini-select"} onClick={() => {
                                                setSideVarStatus(!sideVarStatus);
                                            }}>
                                                <PlusBtn/>
                                            </div>
                                        </div>
                                    </>
                                )}
                        </div>
                    </div>
                    {
                        sideVarStatus ? (
                            <div className={"add-side-part  animate__animated animate__slideInDown"}>
                                <span>사이드 카테고리 추가</span>
                                <SideSelectVar setSideVarStatus={setSideVarStatus} menuDetail={menuDetail}
                                               setMenuDetail={setMenuDetail}/>
                            </div>) : (<></>)
                    }
                    <div className={"add-img-part"}>
                        <span style={{
                            margin: "0px 0px 3px 0px"
                        }}>이미지</span>
                        <div className={"add-img-container m-scroll2"}>
                            {
                                menuDetail.storeMenuImgs !== undefined && menuDetail.storeMenuImgs.length !== 0 ? (<>
                                    <div>
                                        {
                                            menuDetail.storeMenuImgs.map((data, index) => (
                                                <React.Fragment key={index}>
                                                    {
                                                        data.status === null || data.status === undefined || data.status === true ? (
                                                            <img alt={"view img"} onClick={() => {
                                                                deleteMenuImg(data.seq)
                                                            }}
                                                                 src={`${process.env.REACT_APP_SERVER_URL_PORT}/api/v1/img/get?name=${data.name}&kind=menu&store=${store.name}`}/>

                                                        ) : (<></>)
                                                    }
                                                </React.Fragment>
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
                    <div
                        className={"add-img-p"}><p onClick={onClickImg}>이미지 추가하기</p>
                    </div>
                    {
                        demoImgUrl.length !== 0 ? (
                            <div className={"add-img-part"}>
                        <span style={{
                            margin: "0px 0px 3px 0px"
                        }}>추가될 이미지</span>
                                <div className={"add-img-container m-scroll2"}>
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
                                            </>
                                        )
                                    }
                                </div>
                            </div>
                        ) : (<></>)
                    }
                    <div className={"add-input-part position-left"} style={{
                        padding: "0px 10px"
                    }}>
                        <div style={{
                            display: "flex",
                            justifyContent: "space-around",
                            alignItems: "center"
                        }}>
                            <p>메뉴 상태</p>
                            <label className="switch">
                                <input type="checkbox" name={"status"} checked={menuDetail.status || false}
                                       onChange={onChangeMenuStatusToggle}/>
                                <span className="slider round"></span>
                            </label>
                        </div>
                    </div>
                    <div className={"add-input-part position-left"} style={{
                        padding: "10px 10px"
                    }}>
                        <div style={{
                            display: "flex",
                            justifyContent: "space-around",
                            alignItems: "center"
                        }}>
                            <p>품절 상태</p>
                            <label className="switch">
                                <input type="checkbox" name={"soldOutStatus"}
                                       checked={menuDetail.soldOutStatus || false}
                                       onChange={onChangeMenuSoldOutToggle}/>
                                <span className="slider round"></span>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div className={"main-container2-footer"}>
                <div>
                    <div className={"main-btn-false m-f-1 position-center"}>
                        <div onClick={onClickMenuDelete}>
                            <p>삭제하기</p>
                        </div>
                    </div>
                    <div className={"main-btn-true m-f-1 position-center"}>
                        <div onClick={onClickMenuUpdate}>
                            <p>수정하기</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default MenuDetail;