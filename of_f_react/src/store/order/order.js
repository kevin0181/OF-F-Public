import {atom} from "recoil";

export const order = atom({
    key: "order",
    default: {
        seq: "",
        storeSeq: "",
        storeQRIdSeq: "",
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

