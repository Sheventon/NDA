let chatController = function(req ,res){
    let userId = req.params.id;
    res.render("chat.ejs", {
        userId: userId
    });
}

export {
    chatController
}
