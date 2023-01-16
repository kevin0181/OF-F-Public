import axios, {AxiosInstance} from "axios";
import {getCookie} from "./cookie";
import getRefreshToken from "./getRefreshToken";


export const tokenStoreAdminAxios: AxiosInstance = axios.create({ // 토큰을 가지고 보내는 axios
    baseURL: process.env.REACT_APP_SERVER_URL_PORT, // 기본 서버 주소 입력
    headers: {
        'Access-Control-Allow-Origin': process.env.REACT_APP_SERVER_URL_PORT,	// 서버 domain
        'Authorization': 'Bearer ' + getCookie("accessToken")
    },
    withCredentials: true,
});


tokenStoreAdminAxios.interceptors.request.use(
    async config => {

        let accessToken = getCookie("accessToken");
        let status = getCookie("l-st");

        if (accessToken === null && status === null) {
            alert("로그인 후 이용해주세요.");
            return Promise.reject("Should be login");
        }

        return config;
    },
    error => {
        return Promise.reject(error);
    }
)

tokenStoreAdminAxios.interceptors.response.use(
    async config => {
        return config;
    },
    async err => {

        const {config} = err;

        console.log(err)

        if (err.response.data.code == "401" && err.response.data.errorCode == "TO0001") { // accessToken이 유효하지 않을때
            let accessToken = await getRefreshToken();

            if (accessToken) {
                config.headers.Authorization = `Bearer ${accessToken}`;
            }

            return axios(config);
        }
        return Promise.reject(err);
    },
);