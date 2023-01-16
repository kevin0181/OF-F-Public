import {useEffect, useState} from "react";

let CategoryDetail = ({category}) => {

    const [categoryDetail, setCategoryDetail] = useState({
        seq: "",
        name: "",
        status: false,
        storeMenus: [],
        storeSeq: ""
    });

    useEffect(() => {
        if (category !== undefined) {
            setCategoryDetail(category);
        }
    }, [category]);

    useEffect(() => {
        console.log(categoryDetail);
    }, [categoryDetail]);

    let onChangeCategoryDetail = (e) => {
        setCategoryDetail({
            ...categoryDetail,
            [e.target.name]: e.target.value
        })
    }

    let onChangeCategoryStatusToggle = (e) => {
        setCategoryDetail({
            ...categoryDetail,
            status: !categoryDetail.status
        })
    }


    return (
        <div className={"category-detail-container"}>
            <div className={"main-container2-top"}>
                <h2>카테고리 상세</h2>
            </div>
            <div className={"main-container2-body"}>
                <div>
                    <div className={"category-add-input-part"}>
                        <span>이름</span>
                        <input className={"m-input"} type={"text"} name={"name"} value={categoryDetail.name || ""}
                               onChange={onChangeCategoryDetail}/>
                    </div>
                    <div className={"category-add-input-part position-left"} style={{
                        padding: "10px 0px"
                    }}>
                        <div>
                            <label className="switch">
                                <input type="checkbox" name={"status"} checked={categoryDetail.status || false}
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
                        <div>
                            <p>삭제하기</p>
                        </div>
                    </div>
                    <div className={"main-btn-true m-f-1 position-center"}>
                        <div>
                            <p>수정하기</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default CategoryDetail;