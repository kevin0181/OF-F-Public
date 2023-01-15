let CategoryDetail = () => {
    return (
        <div className={"category-detail-container"}>
            <div className={"main-container2-top"}>
                <h2>카테고리 상세</h2>
            </div>
            <div className={"main-container2-body"}>
                <div>
                    <div className={"category-add-input-part"}>
                        <span>이름</span>
                        <input className={"m-input"} type={"text"} name={"name"}/>
                    </div>
                    <div className={"category-add-input-part position-left"} style={{
                        padding: "10px 0px"
                    }}>
                        <div>
                            <label className="switch toggle-disable">
                                <input type="checkbox"/>
                                <span className="slider round"></span>
                            </label>
                        </div>
                    </div>
                    <div className={"category-add-input-part position-right"}>
                        <small>카테고리는 기본적으로 비활성화 상태로 생성됩니다.</small>
                    </div>
                </div>
            </div>
            <div className={"main-container2-footer"}>
                <div>
                    <div className={"main-btn-false m-f-1 position-center"}>
                        <div>
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

export default CategoryDetail;