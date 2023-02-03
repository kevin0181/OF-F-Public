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

