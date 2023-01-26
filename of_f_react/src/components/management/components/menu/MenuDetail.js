import {useRecoilState} from "recoil";
import {selectStoreInfoRecoil} from "../../../../store/management/storeInfo";
import {useQuery} from "../../../../Config/getQuery";
import {useNavigate} from "react-router-dom";
import {useEffect, useRef, useState} from "react";
import {tokenStoreAdminAxios} from "../../../../Config/customStoreAdminAjax";
import {ReactComponent as Photograph} from "../../../../assets/icon/photograph.svg";

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

    useEffect(() => {
        if (menu !== undefined && Object.keys(menu).length !== 0) {
            setMenuDetail(menu);
        }
    }, [menu]);

    useEffect(() => {
        console.log(store);
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

    let onClickMenuUpdate = () => {

        tokenStoreAdminAxios({
            method: "POST",
            url: "/api/v1/store/admin/menu?status=update",
            data: menuDetail
        }).then(res => {
            console.log(res);
        }).catch(err => {
            console.error(err);
            alert("메뉴를 수정할 수 없습니다.");
            return;
        });
    }

    let onClickMenuDelete = () => {
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

    let onClickImg = () => {
        imgRef.current.click();
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
                    {
                        menuDetail.storeMSs !== undefined && menuDetail.storeMSs.length !== 0 ? (
                            <div className={"add-side-part"}>
                                <span>사이드<br/>카테고리</span>
                                <div>
                                    <div className={"side-select-list-part"}>
                                        {
                                            menuDetail.storeMSs.map((data, index) => ( //사이드 카테고리 부분
                                                <div key={index}>
                                                    <div
                                                        className={"side-mini-select"}>
                                                        <div className={"side-mini-top"}>
                                                            <div>{data.storeSideCategory.name}</div>
                                                        </div>
                                                        <div className={"side-mini-body"}>
                                                            <div className={"side-mini-select-btn"}>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            ))
                                        }
                                    </div>
                                </div>
                            </div>
                        ) : (<></>)
                    }
                    <div className={"add-img-part"}>
                        <span style={{
                            margin: "0px 0px 3px 0px"
                        }}>이미지</span>
                        <div className={"add-img-container m-scroll2"} onClick={onClickImg}>
                            {
                                menuDetail.storeMenuImgs !== undefined && menuDetail.storeMenuImgs.length !== 0 ? (<>
                                    <div>
                                        {
                                            menuDetail.storeMenuImgs.map((data, index) => (
                                                <img alt={"view img"} key={index}
                                                     src={`${process.env.REACT_APP_SERVER_URL_PORT}/api/v1/img/get?name=${data.name}&kind=menu&store=${store.name}`}/>
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
                        {/*<input type={"file"} ref={imgRef} multiple={true} className={"add-img-input"}*/}
                        {/*       accept='image/*'*/}
                        {/*       onChange={onChangeUploadImg}*/}
                        {/*       name='storeMenuImgs'/>*/}
                    </div>
                    <div className={"add-input-part position-left"} style={{
                        padding: "0px 10px"
                    }}>
                        <div>
                            <label className="switch">
                                <input type="checkbox" name={"status"} checked={menuDetail.status || false}
                                       onChange={onChangeMenuStatusToggle}/>
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