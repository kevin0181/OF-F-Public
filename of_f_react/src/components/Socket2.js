import {useEffect, useState} from "react";
import socketIoClient from "socket.io-client";

let Socket2 = () => {

    let socketStore;

    useEffect(() => {
        socketStore = socketIoClient(`${process.env.REACT_APP_NODE_SERVER_URL_PORT}/store`);
        socketStore.on("room get", (data) => {
            console.log(data);
        });
    }, [])

    const [roomName, setRoomName] = useState("1");

    return (
        <div>
            <input value={roomName} onChange={(e) => {
                setRoomName(e.target.value);
            }}/>
            <button onClick={() => {
                socketStore.emit("insert room", roomName);
            }}>
                방참가
            </button>
            <button onClick={() => {
                socketStore.emit("room send", "123", roomName);
            }}>보내기
            </button>
        </div>
    )
}

export default Socket2;