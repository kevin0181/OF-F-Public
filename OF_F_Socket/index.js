const express = require('express');
const app = express();
const http = require('http');
const server = http.createServer(app);
const {Server} = require("socket.io");
const cors = require("cors");
const PORT = 8000;
const mongoose = require("mongoose");
const {socket} = require("./socket/socket");

const storeRouter = require("./store/store");

mongoose.set("strictQuery", false);
mongoose.connect('mongodb://off_user:kevin1141@localhost:27017/off', {dbName: 'off'}, function (err) {
    // console.log(err);
});

// db 연결 실패
mongoose.connection.on('error', function () {
    console.error('db 연결 실패!');
});

// db 연결 성공
mongoose.connection.once('open', function () {
    console.log('db 연결 성공!');
});

// const io = new Server(server, {
//     cors: {
//         origin: `${process.env.REACT_APP_PORT}`
//     }
// });
//
// const storeSpace = io.of("/store");
//
// socket(storeSpace);

// app.use(cors({
//     origin: `${process.env.REACT_APP_PORT}`,
// }));
app.use(cors())

app.use(express.json());
app.use(express.urlencoded({extended: true}));

app.use("/store", storeRouter);

server.listen(PORT, () => {
    console.log('listening on *:8000');
});