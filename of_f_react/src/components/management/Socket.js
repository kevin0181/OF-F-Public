import socketIoClient from "socket.io-client";
import React from "react";

export const socket = socketIoClient(`${process.env.REACT_APP_NODE_SERVER_URL_PORT}/store`);
export const SocketContext = React.createContext();
