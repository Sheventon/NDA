import { __dirname} from "../common/constants.js";

let signupController = function (req, res){
    res.render("signup-page.pug", {
        authenticated: false
    })
}

export default signupController;