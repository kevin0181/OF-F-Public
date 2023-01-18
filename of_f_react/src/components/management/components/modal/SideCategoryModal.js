import "./../../../../styles/css/management/sideModal.css";
import {ReactComponent as XBtn} from "./../../../../assets/icon/x.svg";
import {useNavigate} from "react-router-dom";
import {useQuery} from "../../../../Config/getQuery";

let SideCategoryModal = () => {

    const navigate = useNavigate();
    const query = useQuery();

    return (
        <div className={"side-modal"}>
            <div className={"side-modal-container animate__animated animate__backInLeft"}>
                <div className={"side-modal-close-btn"} onClick={() => {
                    navigate(`/manage/store?kind=${query.get("kind")}&f=${query.get("f")}&status=${query.get("status")}`);
                }}>
                    <XBtn/>
                </div>
            </div>
        </div>
    )
}
export default SideCategoryModal;