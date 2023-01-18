import {atom} from "recoil";

const navStatus = atom({
    key: "navStatus",
    default: true
});

export default navStatus;