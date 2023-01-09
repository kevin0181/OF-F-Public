import {useParams} from "react-router-dom";

let ErrorPage = () => {
    let {code} = useParams();
    return (<>
        <div>
            {code} 오류입니다!!
        </div>
    </>);
}

export default ErrorPage;