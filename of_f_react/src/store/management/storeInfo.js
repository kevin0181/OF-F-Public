import {atom} from "recoil";

export const storeInfoRecoil = atom({
    key: "storeInfo",
    default: {}
});

export const selectStoreInfoRecoil = atom({
    key: "selectStore",
    default: {}
});


export const storeStatus = atom({
    key: "storeStatus",
    default: {
        storeSeq: "",
        status: false
    }
});
