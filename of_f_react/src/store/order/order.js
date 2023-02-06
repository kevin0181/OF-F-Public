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
        totalPrice: "0",
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
        storeOrderSeq: "",
        storeMenuSeq: "",
        size: 1,
        price: 0,
        storeMenu: {},
        storeOrderSides: [
            // {
            //     seq: "",
            //     storeSideMenuSeq: "",
            //     storeOrderMenuSeq: "",
            //     storeSideMenu: []
            // }
        ]
    }
});

