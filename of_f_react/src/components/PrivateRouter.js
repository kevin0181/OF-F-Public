import {Navigate, Outlet, useNavigate} from "react-router-dom";
import {useEffect} from "react";
import jwtDecode from "jwt-decode";

let PrivateRouter = ({loginStatus, cookies}) => {

    let CheckAuth = () => {

        if (loginStatus != "true") {
            return <Navigate to={"/manage/login"} replace/>;
        }

        console.log(jwtDecode(cookies.accessToken));

        let arrRole = jwtDecode(cookies.accessToken).auth.split(',');

        console.log(arrRole);

        let ST_ROLE = arrRole.indexOf("ROLE_ST_ADMIN");
        let TT_ROLE = arrRole.indexOf("ROLE_TT_ADMIN");

        if (TT_ROLE !== -1) { //관리자니깐 그냥 통과
            return <Outlet/>;
        } else if (TT_ROLE === -1) { //관리자가 아니라면?
            if (ST_ROLE !== -1) { //가맹점 관리자인지 확인
                return <Outlet/>;
            } else {
                return <Navigate to={"/error/403"} replace/>;
            }
        }
    }

    return (
        <CheckAuth/>
    )

}

export default PrivateRouter;