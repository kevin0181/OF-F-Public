import {tokenAxios} from "./customAxios";

let test = () => {
    tokenAxios({
        method: 'post',
        url: '/api/v1/auth/y/test',
    }).then(res => {
        console.log("테스트 들어옴")
        console.log(res)
    })
}

export default test;