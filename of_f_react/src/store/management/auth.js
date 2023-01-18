import {atom} from "recoil";

const auth = atom({
    key: "auth",
    default: false
});

export default auth;