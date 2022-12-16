import axios, {AxiosInstance} from 'axios';

export const customAxios: AxiosInstance = axios.create({
    baseURL: process.env.REACT_APP_SERVER_URL_PORT, // 기본 서버 주소 입력
    headers: {
        // access_token: cookies.get('access_token'),
    },
});