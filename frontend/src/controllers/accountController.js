import { __dirname} from "../common/constants.js";

let accountController = function (req, res){
    res.render("account-page.pug", {
        authenticated: false
    })
}

export default accountController;