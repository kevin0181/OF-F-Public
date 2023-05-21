import "./../../../styles/css/management/loading.css";

let Loading = () => {
    return (
        <div className={"store-admin-loading-container"}>
            <div>
                <div className="lds-ellipsis">
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
            </div>
        </div>
    )
}
export default Loading;