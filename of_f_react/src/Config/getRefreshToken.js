import {refreshTokenAxios} from "./customAxios";
import {removeCookie, setCookie} from "./cookie";

let getRefreshToken = async () => {

    try {

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
    } catch (error) {
        console.log(error)
        if (error.response.data.code == "404" && error.response.data.errorCode == "TO0003") {
            alert("로그아웃 된 상태입니다. 재로그인이 필요합니다.");
            removeCookie("accessToken", {
                path: "/"
            });
            localStorage.removeItem("l-st");

            window.location.reload();
        }
    }

}

export default getRefreshToken;