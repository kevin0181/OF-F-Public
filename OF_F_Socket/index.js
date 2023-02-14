const {Server} = require("socket.io");
const SOCKET_PORT = 8000;

require('dotenv').config();

const io = new Server(SOCKET_PORT, {
    cors: {
        origin: `${process.env.REACT_APP_PORT}`
    }
});

const storeSpace = io.of("/store");

storeSpace.on('connection', (socket) => {
    console.log('A user has connected');

    socket.on('insert room', (data) => {
        socket.join('my-room')
        console.log("방 가입");
    })

    socket.on('message', (data) => {
        console.log(data);
        socket.to('my-room').emit('message2', "리엑트로!");
    });

});