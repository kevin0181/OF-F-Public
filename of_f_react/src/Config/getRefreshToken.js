import {refreshTokenAxios} from "./customAxios";
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

    localStorage.setItem("l-st", true);


    return accessToken;

}

export default getRefreshToken;