import {tokenAxios} from "./customAxios";

let getRefreshToken = () => {
    tokenAxios({
        method: 'post',
        url: '/api/v1/auth/y/refresh-token',
    }).then(res => {
        console.log("리프레시")
    })
}

export default getRefreshToken;