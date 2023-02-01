import {atom} from "recoil";

export const orderStore = atom({
    key: "orderStore",
    default: {}
});


export const orderStoreCategory = atom({
    key: "orderStoreCategory",
    default: [{
        name: "",
        storeMenus: [],
        seq: "",
        status: false,
        storeSeq: ""
    }]
});

export const orderStoreSideCategory = atom({
    key: "orderStoreSideCategory",
    default: []
});