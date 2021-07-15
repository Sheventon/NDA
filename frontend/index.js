const express = require('express');
const app = express();
const path = require('path');
const router = express.Router();
let homeController = function (req, res){
    res.sendFile(path.join(__dirname+'/public/index.html'));
}

let userId = 'test';

let chatController = function(req,res){
    console.log(req.param.id);
    userId = req.param.id;
    res.sendFile(path.join(__dirname+'/public/chat.html'));

}

router.get('/', homeController);

router.get('/messenger/chat/:id', chatController);
app.use("/script", express.static(path.join(__dirname, "script")))
app.use('/', router);
app.listen(process.env.port || 3000);

console.log('Running at Port 3000');


exports.users = userId;
