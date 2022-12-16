import axios, {AxiosInstance} from 'axios';

export const customAxios: AxiosInstance = axios.create({
    baseURL: process.env.REACT_APP_SERVER_URL_PORT, // 기본 서버 주소 입력
    headers: {
        'Access-Control-Allow-Origin': 'http://localhost:8080'	// 서버 domain
    },
    withCredentials: true
});