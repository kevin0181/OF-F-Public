import {atom} from "recoil";

export const orderStore = atom({
    key: "orderStore",
    default: {}
});


export const orderStoreCategory = atom({
    key: "orderStoreCategory",
    default: []
});

export const orderStoreSideCategory = atom({
    key: "orderStoreSideCategory",
    default: []
});