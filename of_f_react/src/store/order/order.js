import {atom} from "recoil";

export const order = atom({
    key: "order",
    default: {
        seq: "",
        storeSeq: "",
        storeQRId: "",
        id: "",
        kind: "",
        orderNumber: "",
        totalPrice: "",
        cancelAfterPrice: "",
        date: "",
        place: "",
        status: "",
        payStatus: "",
        phoneNumber: "",
        email: "",
        emailReceiveStatus: "",
        phoneNumberReceiveStatus: "",
        storeOrderMenus: [],
        storeOrderPgInfo: {},
        storeOrderVanInfo: {},
        // store: "",
        // storeQRId: ""
    }
});


export const orderMenu = atom({
    key: "orderMenu",
    default: {
        seq: "",
        storeMenuSeq: "",
        storeOrderSeq: "",
        size: "",
        storeMenu: {},
        storeOrderSides: [{
            seq: "",
            storeSideMenuSeq: "",
            storeOrderMenuSeq: "",
            storeSideMenu: []
        }]
    }
});

