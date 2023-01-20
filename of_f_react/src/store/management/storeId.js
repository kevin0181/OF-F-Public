import {atom} from "recoil";

const storeId = atom({
    key: "storeId",
    default: 0
});

export default storeId;