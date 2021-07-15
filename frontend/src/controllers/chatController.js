import path from "path";
import {__dirname} from "../common/constants.js";

let chatController = function(req ,res){
    let userId = req.params.id;
    res.sendFile(path.join(__dirname + '/public/chat.html'));
}

export {
    chatController
}

