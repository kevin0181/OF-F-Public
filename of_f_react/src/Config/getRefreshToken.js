import {refreshTokenAxios, tokenAxios} from "./customAxios";
import {setCookie} from "./cookie";

let getRefreshToken = async () => {

    const data = await refreshTokenAxios({
        headers: {"Authorization": ""},
        method: 'post',
        url: '/api/v1/auth/n/refresh-token'
    });

    const {accessToken} = data.data.data;

    const expires = new Date();

    expires.setMinutes(expires.getMinutes() + 30);

    setCookie("accessToken", accessToken, {
        path: "/",
        expires
    });

    setCookie("l-st", true, {
        path: "/",
        expires
    });

    return accessToken;

}

export default getRefreshToken;