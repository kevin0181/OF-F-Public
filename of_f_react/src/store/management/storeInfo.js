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


export const storeOrderList = atom({
    key: "storeOrderList",
    default: [
        // {
        //     seq: "",
        //     cancelAfterPrice: "",
        //     comment: "",
        //     date: "",
        //     email: "",
        //     emailReceiveStatus: false,
        //     id: "",
        //     kind: "",
        //     orderNumber: "",
        //     payStatus: "",
        //     phoneNumber: "",
        //     phoneNumberReceiveStatus: false,
        //     place: "",
        //     status: "",
        //     storeOrderMenus: [],
        //     storeOrderPgInfo: {},
        //     storeOrderVanInfo: {},
        //     storeQRId: "",
        //     storeSeq: "",
        //     totalPrice: "",
        //     user: {}
        // }
    ]
});
