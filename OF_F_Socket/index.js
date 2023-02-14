const {Server} = require("socket.io");
const SOCKET_PORT = 8000;

require('dotenv').config();

const io = new Server(SOCKET_PORT, {
    cors: {
        origin: `${process.env.REACT_APP_PORT}`
    }
});

io.on('connection', (socket) => {
    console.log('A user has connected');

    socket.on('message', (data) => {
        console.log(data.text);
    });

});