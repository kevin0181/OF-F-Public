import {atom, selector} from "recoil";

export const storeInfoRecoil = atom({
    key: "storeInfo",
    default: {}
});

export const storeListSelector = selector({
    key: "storeInfoSelector",
    get: ({get}) => {
        let storeInfo = get(storeInfoRecoil);
        return storeInfo.stores;
    }
});