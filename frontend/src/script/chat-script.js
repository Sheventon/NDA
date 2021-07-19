let stompClient = null;
let users;
let recId;
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
        type: 'POST',
        headers:{
            'Authorization': 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzIiwicm9sZXMiOlsiVVNFUiJdLCJleHAiOjE2MjY3MDIzNTAsImlhdCI6MTYyNjY5ODc1MCwiZW1haWwiOiJuZnNrYTNAZ21haWwuY29tIn0.TNd5DGoosFw4KtBcS8goc7vKF6t8Ew8OCyzZZCWNif0maLMPwOSfJwK41O-Rw-vw7fNDa0IFQoTg1Q6xn94fFQ'
        },
        setRequestHeader: '"Content-Type", "application/json"',
        url: CHAT_SERVICE + "/messenger/chat/" + recId,
        contentType: "application/json",
        success: function (data) {
            users = JSON.parse(JSON.stringify(data));
            console.log(users.recipientId);
            console.log(users.senderId);
            loadMessage();
            insertName();
        }
    })
}

function scrollMessages() {
    $('#messages').stop().animate({
        scrollTop: $('#messages')[0].scrollHeight
    }, 800);
}

function connect(id) {
    recId = id;
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

function insertName() {
    let test = users.recipientFirstName + ' ' + users.recipientLastName;
    console.log(test);
    document.getElementById('ms_name').innerText = test;

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
        let sendMess = '<div class="msg right-msg"><div class="msg-bubble"><div class="msg-info"> <div class="msg-info-name">' + users.senderFirstName + ' ' + users.senderLastName + '</div><div class="msg-info-time"></div></div><div class="msg-text">' + msg + '</div></div></div>';
        $(sendMess).appendTo("#messages");
        scrollMessages();
    }
}

function onMessageReceived(msg) {
    let mess = '<div class="msg left-msg"><div class="msg-bubble"><div class="msg-info"><div class="msg-info-name">' + users.recipientFirstName + ' ' + users.recipientLastName + '</div><div class="msg-info-time"></div></div><div class="msg-text">' + JSON.parse(msg.body).message + '</div></div></div>';
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
        type: "POST",
        headers:{
            'Authorization': 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzIiwicm9sZXMiOlsiVVNFUiJdLCJleHAiOjE2MjY3MDIzNTAsImlhdCI6MTYyNjY5ODc1MCwiZW1haWwiOiJuZnNrYTNAZ21haWwuY29tIn0.TNd5DGoosFw4KtBcS8goc7vKF6t8Ew8OCyzZZCWNif0maLMPwOSfJwK41O-Rw-vw7fNDa0IFQoTg1Q6xn94fFQ'
        },
        url: CHAT_SERVICE + "/messenger/users/messages/" + users.recipientId,
        contentType: "application/json",
        success: function (data) {
            $(function () {
                for (let i = 0; i < data.length; i++) {
                    let test;
                    if (data[i].senderId === users.senderId.toString()) {
                        test = '<div class="msg right-msg"><div class="msg-bubble"><div class="msg-info"> <div class="msg-info-name">' + users.senderFirstName + ' ' + users.senderLastName + '</div><div class="msg-info-time"></div></div><div class="msg-text">' + data[i].message + '</div></div></div>';
                    } else {
                        test = '<div class="msg left-msg"><div class="msg-bubble"><div class="msg-info"> <div class="msg-info-name">' + users.recipientFirstName + ' ' + users.recipientLastName + '</div><div class="msg-info-time"></div></div><div class="msg-text">' + data[i].message + '</div></div></div>';
                    }
                    $(test).appendTo("#messages");

                    scrollMessages();
                }
            })
        }
    });
}
