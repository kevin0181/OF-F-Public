import "./../../styles/css/order/search.css";
import {useState} from "react";
import {notTokenAxios} from "../../Config/customAxios";
import {useNavigate} from "react-router-dom";

let OrderStoreSearch = () => {

    const navigate = useNavigate();

    let [searchText, setSearchText] = useState("");

    let [show, setShow] = useState(false);

    let [storeList, setStoreList] = useState([]);

    let onClickSearchStoreBtn = () => {

        if (searchText === "") {
            alert("가게 이름을 검색해주세요.");
            return;
        }

        notTokenAxios({
            method: "get",
            url: `/api/v1/store/order/search?storeName=${searchText}`,
        }).then(res => {
            setStoreList(res.data.data);
            setShow(true);
        }).catch(err => {
            console.log(err);
        });
    }

    let onKeyPressEnterSearchStoreBtn = (e) => {
        if (e.key === "Enter") {
            onClickSearchStoreBtn();
        }
    }

    return (
        <div className={"search-container"}>
            <div className={"search-var-container"}>
                <div>
                    <input className={"search-input m-input"} onKeyPress={onKeyPressEnterSearchStoreBtn}
                           onChange={(e) => {
                               setSearchText(e.target.value);
                           }}/>
                    <div className={"search-btn"}>
                        <div onClick={onClickSearchStoreBtn}>
                            검색
                        </div>
                    </div>
                </div>
            </div>
            {
                show ?
                    (<div className={"store-search-list-container"}>
                        <div>
                            {
                                storeList.map((data, index) => (
                                    <div className={"search-list m-input"} key={index} onClick={() => {
                                        let qrId = prompt("QR 스티커에 존재하는 코드를 입력해주세요.");
                                        navigate(`/store/${data.seq}/${qrId}/main`);
                                    }}>
                                        <h3 className={"search-part search-storeName"}>{data.name}</h3>
                                        <p className={"search-part search-storeAddress"}>{data.address} {data.detailAddress}</p>

                                        {
                                            data.status === 0 ? (
                                                <small className={"search-part search-storeActive"} style={{
                                                    color: "blue"
                                                }}>활성화</small>) : (
                                                <small className={"search-part search-storeActive"} style={{
                                                    color: "red"
                                                }}>비활성화</small>
                                            )
                                        }
                                    </div>
                                ))
                            }
                        </div>
                    </div>) : (
                        <div>
                            <small>가게 이름을 입력해주세요.</small>
                        </div>
                    )
            }
        </div>
    )
}
export default OrderStoreSearch;