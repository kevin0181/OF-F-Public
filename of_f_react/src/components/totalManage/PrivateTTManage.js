import {Navigate, Outlet} from "react-router-dom";
import getRefreshToken from "../../Config/getRefreshToken";
import jwtDecode from "jwt-decode";
import {useCookies} from "react-cookie";

let PrivateTTManage = () => {

    const loginStatus = localStorage.getItem("l-st");
    const [cookies, setCookie, removeCookie] = useCookies(['accessToken']);

    let CheckAuth = () => {

        if (loginStatus != "true") {
            return <Navigate to={"/manage/login"} replace/>;
        }

        if (cookies.accessToken === undefined || cookies.accessToken === null) {
            getRefreshToken().then(() => {
                window.location.reload();
            }).catch(() => {
                // alert("로그인 오류입니다. 재로그인을 해주세요.");
                return <Navigate to={"/manage/login"} replace/>;
            });
        }


        let arrRole = jwtDecode(cookies.accessToken).auth.split(',');

        let TT_ROLE = arrRole.indexOf("ROLE_TT_ADMIN");

        if (TT_ROLE !== -1) { //관리자니깐 그냥 통과
            return <Outlet/>;
        } else if (TT_ROLE === -1) { //관리자가 아니라면?
            return <Navigate to={"/error/403"} replace/>;
        }
    }

    return (
        <CheckAuth/>
    )

}

export default PrivateTTManage;