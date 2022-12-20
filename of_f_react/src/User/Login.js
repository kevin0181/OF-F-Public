import {setCookie} from "../Config/cookie";
import {notTokenAxios} from "../Config/customAxios";
import test from "../Config/test";

let Login = () => {

    let loginBtn = () => {
        notTokenAxios({
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
            });

            setCookie("l-st", true, {
                path: "/",
                expires
            })

        });
    }

    return (
        <>
            <button onClick={loginBtn}>11</button>
            <button onClick={() => {
                test();
            }}>test
            </button>
        </>
    );
}
export default Login;