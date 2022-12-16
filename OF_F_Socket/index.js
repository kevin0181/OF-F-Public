const {Server} = require("socket.io");
const SOCKET_PORT = 8000;

const io = new Server(SOCKET_PORT, {
    cors: {
        origin: "*"
    }
});