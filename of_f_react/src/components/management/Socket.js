import {Outlet} from "react-router-dom";
import {useEffect} from "react";
import socketIoClient from "socket.io-client";
import {useRecoilState} from "recoil";
import {selectStoreInfoRecoil} from "../../store/management/storeInfo";

let Socket = () => {

    const [store, setStore] = useRecoilState(selectStoreInfoRecoil);

    useEffect(() => {

        if (store.seq !== undefined && store.seq !== null) {
            const socketStore = socketIoClient(`${process.env.REACT_APP_NODE_SERVER_URL_PORT}/store`);
            socketStore.emit("insert room", store.seq);
            socketStore.on("room get", (data) => {
                console.log(data);
            });
        }

    }, [store])

    return (
        <Outlet/>
    )
}

export default Socket;