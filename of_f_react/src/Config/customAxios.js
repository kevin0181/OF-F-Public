import axios, {AxiosInstance} from 'axios';
import {getCookie} from "./cookie";

const customAxios: AxiosInstance = axios.create({
    baseURL: process.env.REACT_APP_SERVER_URL_PORT, // 기본 서버 주소 입력
    headers: {
        'Access-Control-Allow-Origin': 'http://localhost:8080',	// 서버 domain
        'Authorization': 'Bearer ' + getCookie("accessToken")
    },
    withCredentials: true,
});

customAxios.interceptors.response.use(
    config => {
        return config;
    },
    err => {
        console.log(123);
        return Promise.reject(err);
    },
);


export default customAxios;
