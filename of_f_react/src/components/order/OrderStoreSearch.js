import "./../../styles/css/order/search.css";
import {useState} from "react";
import {notTokenAxios} from "../../Config/customAxios";

let OrderStoreSearch = () => {

    let [searchText, setSearchText] = useState("");

    let onClickSearchStoreBtn = () => {

        notTokenAxios({
            method: "get",
            url: `/api/v1/store/order/qr/search?storeName=${searchText}`,
        }).then(res => {
            console.log(res);
        }).catch(err => {
            console.log(err);
        })
    }

    return (
        <div className={"search-container"}>
            <div className={"search-var-container"}>
                <div>
                    <input className={"search-input m-input"} onChange={(e) => {
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
                searchText.length !== 0 ?
                    (<div className={"store-search-list-container"}>
                        <div>

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