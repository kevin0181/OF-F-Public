import {ReactComponent as XBtn} from "../../../../assets/icon/x.svg";
import {ReactComponent as PlusBtn} from "../../../../assets/icon/plus.svg";
import {useRecoilState} from "recoil";
import {selectStoreInfoRecoil} from "../../../../store/management/storeInfo";
import {useEffect, useState} from "react";

let SideSelectVar = ({setSideVarStatus, menuDetail}) => {

    const [store, setStore] = useRecoilState(selectStoreInfoRecoil); //선택된 가게 정보
    const [beforeStoreMSs, setBeforeStoreMSs] = useState([{
        seq: "",
        name: "",
        status: false,
        storeSeq: "",
        storeSideMenus: []
    }]);

    useEffect(() => {
        let findData = [];
        let status = true;
        
        store.storeSideCategories.map((allSideData, indexF) => {
            menuDetail.storeMSs.map((selectSideData, indexS) => {
                console.log(selectSideData.storeSideCategory.seq, allSideData.seq);
                if (selectSideData.storeSideCategory.seq === allSideData.seq) {
                    status = false;
                    return;
                }
            });
            if (status) {
                findData.push(allSideData);
            }
            status = true;
        });

        setBeforeStoreMSs([
            ...findData
        ]);

    }, []);

    useEffect(() => {
        console.log(beforeStoreMSs);
    }, [beforeStoreMSs]);

    return (
        <div className={"sideVar-container"}>
            {
                store.storeSideCategories !== undefined && store.storeSideCategories.length !== 0 ? (
                    <div className={"side-select-list-part"}>
                        {
                            beforeStoreMSs.map((data, index) => ( //사이드 카테고리 부분
                                <div key={index}>
                                    <div
                                        className={"side-mini-select"}>
                                        <div className={"side-mini-top"}>
                                            <div>{data.name}</div>
                                        </div>
                                        <div className={"side-mini-body"}>
                                            <div className={"side-mini-select-btn"}>
                                                <XBtn/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ))
                        }
                    </div>
                ) : (
                    <>
                    </>
                )}
        </div>
    )
}
export default SideSelectVar;