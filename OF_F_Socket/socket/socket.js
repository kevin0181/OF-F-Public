exports.socket = (storeSpace) => {
    let i = 0;
    storeSpace.on('connection', (socket) => {
        i++;
        console.log('connection 성공 ' + i);

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
            socket.to(roomNumber).emit("room get", data);
        });
    });
}
