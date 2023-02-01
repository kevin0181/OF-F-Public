import {atom} from "recoil";

export const orderStore = atom({
    key: "orderStore",
    default: {}
});


export const orderStoreCategory = atom({
    key: "orderStoreCategory",
    default: [{
        name: "",
        storeMenus: [
            {
                name: "",
                price: 0,
                seq: "",
                soldOutStatus: false,
                status: false,
                storeCategorySeq: "",
                storeMSs: [],
                storeMenuImgs: [
                //     {
                    //     date: "",
                    //     extension: "",
                    //     name: "",
                    //     seq: "",
                    //     status: false,
                    //     storeMenuSeq: "",
                    //     url: ""
                    // }
                ]
            }
        ],
        seq: "",
        status: false,
        storeSeq: ""
    }]
});

export const orderStoreSideCategory = atom({
    key: "orderStoreSideCategory",
    default: []
});