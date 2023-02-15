import {useEffect, useState} from "react";
import socketIoClient from "socket.io-client";

let Socket = () => {
    const socketStore = socketIoClient(`${process.env.REACT_APP_SOCKET_PORT}/store`);

    useEffect(() => {
        // socketStore.on("room get", (data) => {
        //     console.log(data);
        // });
    }, [])

    const [roomName, setRoomName] = useState("");

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
                socketStore.on("room get", (data) => {
                    console.log(data);
                });
            }}>
                열기
            </button>
            <button onClick={() => {
                socketStore.emit("room send", "방 리엑트 -> 서버", roomName);
            }}>보내기
            </button>
        </div>
    )
}

export default Socket;