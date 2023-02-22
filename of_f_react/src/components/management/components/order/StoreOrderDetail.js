let StoreOrderDetail = () => {

    return (
        <div>
            <div className={"main-container2-top"}>
                <h2>주문 상세보기</h2>
            </div>
            <div className={"main-container2-body"}>
                <div className={"order-detail-body"}>
                    <div className={"order-detail-part"}>
                        <div className={"order-detail-img"}>
                            <img src={""} alt={"주문 메뉴 이미지"}/>
                        </div>
                        <div className={"order-detail-content"}>
                            <div>
                                <h4>메뉴 이름</h4>
                                <h4>-</h4>
                                <span>떡볶이</span>
                            </div>
                        </div>
                    </div>
                    <div className={"order-detail-part"}>
                        <div className={"order-detail-img"}>
                        </div>
                        <div className={"order-detail-content"}>
                            <div>
                                <h4></h4>
                                <h4 style={{
                                    fontSize: "16px"
                                }}>SIDE</h4>
                                <span>떡볶이</span>
                            </div>
                        </div>
                    </div>
                    <hr/>
                </div>
            </div>
            <div className={"main-container2-footer"}>
                <div>
                    <div className={"main-btn-false m-f-1 position-center"}>
                        <div>
                            <p>취소하기</p>
                        </div>
                    </div>
                    <div className={"main-btn-true m-f-1 position-center"}>
                        <div>
                            <p>주문 받기</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default StoreOrderDetail;