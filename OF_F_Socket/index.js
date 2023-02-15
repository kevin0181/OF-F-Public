const express = require('express');
const app = express();
const http = require('http');
const server = http.createServer(app);
const {Server} = require("socket.io");
const PORT = 8000;

const io = new Server(server, {
    cors: {
        origin: `${process.env.REACT_APP_PORT}`
    }
});

const storeSpace = io.of("/store");

storeSpace.on('connection', (socket) => {
    console.log('connection 성공');

    socket.on('send', (data) => {
        console.log(data)
        socket.emit("get", "서버->리엑트");
    });

    socket.on("insert room", (data) => {
        console.log(data + " 방 참가 완료")
        socket.join(data);
    });

    socket.on("room send", (data, roomNumber) => {
        console.log(data, roomNumber);
        socket.to(roomNumber).emit("room get", roomNumber + " 방 서버 -> 리엑트");
    });
});

app.use(express.json());
app.use(express.urlencoded({ extended: true}));

app.get("/", (req, res) => {
    res.send("Hello World");
});

server.listen(PORT, () => {
    console.log('listening on *:8000');
});