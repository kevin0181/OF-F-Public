import {useParams} from "react-router-dom";
import Error from "./Error";

let ErrorPage = () => {
    let {code} = useParams();
    let ErrorSwitch = () => {
        switch (code) {
            default:
                return <Error/>
        }
    }
    return (<ErrorSwitch/>);
}

export default ErrorPage;