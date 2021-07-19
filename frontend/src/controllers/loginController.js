import { __dirname} from "../common/constants.js";

let loginController = function (req, res){
    res.render("login-page.pug", {
        authenticated: false
    })
}

export default loginController;