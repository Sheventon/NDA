import path from 'path';
import { __dirname} from "../common/constants.js";

let homeController = function (req, res){
    res.render("welcome-page.pug", {
        authenticated: false
    })
}

export default homeController;