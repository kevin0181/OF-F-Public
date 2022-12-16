import customAxios from "../Config/customAxios";
import {setCookie} from "../Config/cookie";

let Login = () => {

    let loginBtn = () => {
        customAxios({
            method: 'post',
            url: '/api/v1/auth/n/login',
            data: {
                email: 'test1@test1.com',
                password: 'test1234@'
            }
        }).then(res => {
            const expires = new Date();
            expires.setMinutes(expires.getMinutes() + 30);
            setCookie("accessToken", res.data.data.accessToken, {
                path: "/",
                expires
            })
        });
    }

    return (
        <>
            <button onClick={loginBtn}>11</button>
        </>
    );
}
export default Login;