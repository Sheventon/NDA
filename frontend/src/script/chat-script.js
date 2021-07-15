let stompClient = null;
let users;
const CHAT_SERVICE = "http://localhost:8600";

let msgerChat = document.getElementById("messages");

function send() {
    let msg = document.getElementById("message").value;
    sendMessage(msg);
    document.getElementById("message").value = "";
}

function getUsersInfo() {
    $.ajax({
        cache: false,
        type: 'GET',
        setRequestHeader: '"Content-Type", "application/json"',
        url: CHAT_SERVICE + "/chat/2",
        contentType: "application/json",
        success: function (data) {
            users = JSON.parse(JSON.stringify(data));
            console.log(users.recipientId);
            console.log(users.senderId);
            loadMessage();
        }
    })
}

function scrollMessages() {
    $('#messages').stop().animate({
        scrollTop: $('#messages')[0].scrollHeight
    }, 800);
}

function connect() {
    SockJS = new SockJS(CHAT_SERVICE + "/ws");
    stompClient = Stomp.over(SockJS);
    stompClient.connect({}, onConnected, onError);
}

function onConnected() {
    console.log("connect succesfull");
    getUsersInfo();
    stompClient.subscribe(
        "/topic/" + 1 + "/queue/messages",
        onMessageReceived
    );
}

function sendMessage(msg) {
    if (msg.trim() !== "") {
        const message = {
            senderId: users.senderId,
            recipientId: users.recipientId,
            message: msg,
            date: new Date(),
        };
        stompClient.send("/app/chat", {}, JSON.stringify(message));
        console.log('message send!');
        let sendMess = '<div style="background: #86A8E7; color: white;"><div class="msg-info-name" style = "color: black;"><b>' + users.senderFirstName + ' ' + users.senderLastName + ' </b></div><div class="msg-text">' + msg + '</div></div>';
        $(sendMess).appendTo("#messages");
        scrollMessages();
    }
}

function onMessageReceived(msg) {
    let mess = '<div style="background: #D16BA5; color: white;"><div class="msg-info-name" style = "color: black;"><b>' + users.recipientFirstName + ' ' + users.recipientLastName + ' </b></div><div class="msg-text">' + JSON.parse(msg.body).message + '</div></div>';
    $(mess).appendTo("#messages");
    scrollMessages();
}

function onError() {
    console.log("error during connection!!!");
}


function loadMessage() {

    $("#messages").empty();
    console.log('message load! recipiendId = ' + users.recipientId);
    $.ajax({
        cache: false,
        type: "GET",
        url:  CHAT_SERVICE + "/users/messages/" + users.recipientId,
        contentType: "application/json",
        success: function (data) {
            $(function () {
                for (let i = 0; i < data.length; i++) {
                    let test;
                    if (data[i].senderId === users.senderId.toString()) {
                        test = '<div style="background: #86A8E7; color: white;"><div class="msg-info-name" style = "color: black;"><b>' + users.senderFirstName + ' ' + users.senderLastName + ' </b></div><div class="msg-text">' + data[i].message + '</div></div>';
                    } else {
                        test = '<div style="background: #D16BA5; color: white;"><div class="msg-info-name" style = "color: black;"><b>' + users.recipientFirstName + ' ' + users.recipientLastName + ' </b></div><div class="msg-text">' + data[i].message + '</div></div>';
                    }
                    $(test).appendTo("#messages");

                    scrollMessages();
                }
            })
        }
    });
}
