import {useRecoilState} from "recoil";
import {selectStoreInfoRecoil} from "../../../../../store/management/storeInfo";
import {useQuery} from "../../../../../Config/getQuery";
import {useNavigate} from "react-router-dom";
import {useEffect, useRef, useState} from "react";
import {tokenStoreAdminAxios} from "../../../../../Config/customStoreAdminAjax";
import {ReactComponent as Photograph} from "../../../../../assets/icon/photograph.svg";
import {resDeleteSideMenu, resUpdateMenu} from "../../../../../service/management/menu/menu";
import React from 'react';


let SideMenuDetail = ({sideMenu}) => {


    const [store, setStore] = useRecoilState(selectStoreInfoRecoil);
    const query = useQuery();
    const imgRef = useRef([]);

    const navigate = useNavigate();

    const [sideMenuDetail, setSideMenuDetail] = useState({
        seq: "",
        name: "",
        price: "",
        soldOutStatus: false,
        status: false,
        storeSideCategorySeq: "",
        storeSideImgs: [],
    });

    const [demoImgUrl, setDemoImgUrl] = useState([]);

    useEffect(() => {
        if (sideMenu !== undefined && Object.keys(sideMenu).length !== 0) {
            setSideMenuDetail(sideMenu);
        }
    }, [sideMenu]);

    useEffect(() => {
        console.log(sideMenuDetail);
    }, [sideMenuDetail]);

    let onChangeSideMenuDetail = (e) => {
        setSideMenuDetail({
            ...sideMenuDetail,
            [e.target.name]: e.target.value
        })
    }

    let onChangeSideMenuStatusToggle = (e) => {
        setSideMenuDetail({
            ...sideMenuDetail,
            status: !sideMenuDetail.status
        })
    }

    let onChangeSideMenuSoldOutToggle = () => {
        setSideMenuDetail({
            ...sideMenuDetail,
            soldOutStatus: !sideMenuDetail.soldOutStatus
        })
    }

    let onClickSideMenuUpdate = () => { //메뉴 수정

        if (sideMenuDetail.name === "") {
            alert("이름을 입력해주세요.");
            return;
        }
        if (sideMenuDetail.price === "") {
            alert("가격을 입력해주세요.");
            return;
        }

        if (isNaN(sideMenuDetail.price)) {
            alert("가격은 숫자이어야합니다.");
            return;
        }

        if (sideMenuDetail.price.length > 7) {
            alert("가격의 최대 제한은 7글자 입니다.");
            return;
        }

        let formData = new FormData();

        formData.append("menu", new Blob([JSON.stringify(sideMenuDetail)], {type: "application/json"}));

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
            alert("사이드 메뉴를 수정할 수 없습니다.");
            return;
        });
    }

    let onClickSideMenuDelete = () => { // 사이드 메뉴 삭제
        // eslint-disable-next-line no-restricted-globals
        if (confirm(sideMenuDetail.name + "를 삭제하시겠습니까?")) {

            let formData = new FormData();

            formData.append("side-menu", new Blob([JSON.stringify(sideMenuDetail)], {type: "application/json"}));

            tokenStoreAdminAxios({
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
                method: "POST",
                url: "/api/v1/store/admin/side/menu?status=delete",
                data: formData
            }).then(async res => {
                console.log(res);
                await resDeleteSideMenu(res, store, query, setStore);
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

    let deleteMenuImg = (seq) => { //메뉴 사이드 이미지 삭제
        // eslint-disable-next-line no-restricted-globals
        if (confirm("해당 이미지를 삭제하시겠습니까?")) {
            let deleteMenuImg = sideMenuDetail.storeMenuImgs.filter(data => {
                return seq === data.seq
            });

            let afterMenuImg = sideMenuDetail.storeMenuImgs.filter(data => {
                return seq !== data.seq
            });

            deleteMenuImg[0] = {
                ...deleteMenuImg[0],
                status: false
            }

            setSideMenuDetail({
                ...sideMenuDetail,
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
                <h2>사이드 메뉴 상세</h2>
            </div>
            <div className={"main-container2-body"}>
                <div>
                    <div className={"add-input-part"}>
                        <span>이름</span>
                        <input className={"m-input"} type={"text"} name={"name"} value={sideMenuDetail.name || ""}
                               onChange={onChangeSideMenuDetail}/>
                    </div>
                    <div className={"add-input-part"}>
                        <span>가격</span>
                        <input className={"m-input"} type={"text"} name={"price"} value={sideMenuDetail.price || ""}
                               onChange={onChangeSideMenuDetail}/>
                    </div>
                    <div className={"add-img-part"}>
                        <span style={{
                            margin: "0px 0px 3px 0px"
                        }}>이미지</span>
                        <div className={"add-img-container m-scroll2"}>
                            {
                                sideMenuDetail.storeSideImgs !== null && sideMenuDetail.storeSideImgs !== undefined && sideMenuDetail.storeSideImgs.length !== 0 ? (<>
                                    <div>
                                        {
                                            sideMenuDetail.storeSideImgs.map((data, index) => (
                                                <React.Fragment key={index}>
                                                    {
                                                        data.status === null || data.status === undefined || data.status === true ? (
                                                            <img alt={"view img"} onClick={() => {
                                                                deleteMenuImg(data.seq)
                                                            }}
                                                                 src={`${process.env.REACT_APP_SERVER_URL_PORT}/api/v1/img/get?name=${data.name}&kind=side&store=${store.name}`}/>

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
                                <input type="checkbox" name={"status"} checked={sideMenuDetail.status || false}
                                       onChange={onChangeSideMenuStatusToggle}/>
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
                                       checked={sideMenuDetail.soldOutStatus || false}
                                       onChange={onChangeSideMenuSoldOutToggle}/>
                                <span className="slider round"></span>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div className={"main-container2-footer"}>
                <div>
                    <div className={"main-btn-false m-f-1 position-center"}>
                        <div onClick={onClickSideMenuDelete}>
                            <p>삭제하기</p>
                        </div>
                    </div>
                    <div className={"main-btn-true m-f-1 position-center"}>
                        <div onClick={onClickSideMenuUpdate}>
                            <p>수정하기</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default SideMenuDetail;