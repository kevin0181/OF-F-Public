import axios from "axios";

let Login = () => {
    axios.defaults.baseURL = 'http://localhost:8080';

    let loginBtn = () => {
        axios({
            method: 'post',
            url: '/api/v1/auth/n/login',
            data: {
                email: 'test1@test1.com',
                password: 'test1234@'
            }
        }).then(res => {
            console.log(res)
        });
    }

    return (
        <>
            <button onClick={loginBtn}>11</button>
        </>
    );
}
export default Login;