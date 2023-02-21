import "./../../styles/css/management/manageDashBoard.css";
import {useQuery} from "../../Config/getQuery";
import {useEffect, useState} from "react";
import Home from "./main/Home";
import {useRecoilState} from "recoil";
import navStatusState from "../../store/management/navStatus";
import 'animate.css';
import Category from "./main/Category";
import Menu from "./main/Menu";
import SideCategory from "./main/SideCategory";
import SideMenu from "./main/SideMenu";
import OrderStart from "./main/OrderStart";
import socketIoClient from "socket.io-client";
import {selectStoreInfoRecoil} from "../../store/management/storeInfo";


let ManageDashBoard = () => {
    const query = useQuery();

    const [kindStatus, setKindStatus] = useState("");

    const [navStatus, setNavStatus] = useRecoilState(navStatusState);

    useEffect(() => {
        setKindStatus(query.get("kind"));
    }, [query]);


    const [socketStore, setSocketStore] = useState();
    const [socketStoreOrder, setSocketStoreOrder] = useState({}); //선택된 가게 주문 정보
    const [store, setStore] = useRecoilState(selectStoreInfoRecoil);

    useEffect(() => {
        const socket = socketIoClient(`${process.env.REACT_APP_NODE_SERVER_URL_PORT}/store`);
        setSocketStore(socket);
    }, [])

    useEffect(() => {
        if (socketStore !== undefined) {
            socketStore.on("room get", (data) => {
                setSocketStoreOrder(data)
            })
        }
    }, [socketStore]);

    useEffect(() => {
        if (store.seq !== undefined && store.seq !== null) {
            socketStore.emit("insert room", String(store.seq)); // websocket 방참가
        }
    }, [store]);

    const MainPage = () => {
        switch (kindStatus) {
            case null:
                return <Home/>
            case "Home":
                return <Home/>
            case "orderStart":
                return <OrderStart socketStoreOrder={socketStoreOrder}/>
            case "orderHistory":
                return <></>
            case "Category":
                return <Category/>
            case "Menu":
                return <Menu/>
            case "sideCategory":
                return <SideCategory/>
            case "sideMenu":
                return <SideMenu/>
            case "Info":
                return <></>
            case "Setting":
                return <></>
        }
    }

    return (<>
        <div
            className={"manage-dash-board-container animate__animated " + (navStatus === true ? 'animate__backInLeft' : '')}>
            <MainPage/>
        </div>
    </>);
}
export default ManageDashBoard;