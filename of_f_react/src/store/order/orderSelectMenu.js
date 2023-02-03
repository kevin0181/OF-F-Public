import {atom} from "recoil";

export const selectOrderMenu = atom({
    key: "selectOrderMenu",
    default: {
        name: "",
        price: 0,
        seq: "",
        soldOutStatus: false,
        status: false,
        storeCategorySeq: "",
        storeMSs: [],
        storeMenuImgs: [{
            name: ""
        }]
    }
});


export const clickMenuStatus = atom({
    key: "clickMenuStatus",
    default: false
});
