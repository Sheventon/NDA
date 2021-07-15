import path from 'path';
import { __dirname} from "../common/constants.js";

let homeController = function (req, res){
    console.log(__dirname);
    res.sendFile(path.join(__dirname +'/public/index.html'));
}

export default homeController;