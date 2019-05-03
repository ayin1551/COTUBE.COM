function playRandom(){
    $.ajax({
        url: "createGame.html/playRandom",
        type: "post",
        async: false,
        data: {username:username},
        success: function (data) {
            $.cookie("comidId",data.gameId);
            $.cookie("panelNo",data.panelNo);
        }
    });
    document.location.href = "./createGame.html";
}

function customCreate(){
    var keyword; // TODO: put keyword here
    $.ajax({
        url: "createGame.html/customCreate",
        type: "post",
        async: false,
        data: {username:username, keyword:keyword},
        success: function (data) {
            $.cookie("comidId",data.gameId);
            $.cookie("panelNo",data.panelNo);
        }
    });
    document.location.href = "./createGame.html";
}

function customExist(){
    var keyword; // TODO: put keyword here
    $.ajax({
        url: "createGame.html/customExist",
        type: "post",
        async: false,
        data: {username:username, keyword:keyword},
        success: function (data) {
            if(data.exist){
                $.cookie("comidId",data.gameId);
                $.cookie("panelNo",data.panelNo);
                document.location.href = "./createGame.html";
            }

        }
    });
    // TODO: SHOW ERROR MESSAGE HERE 
    // No Game exist for keyword "xxxx"
}

function randomKeyword(){
    var keyword;
    $.ajax({
        url: "createGame.html/randomKeyword",
        type: "post",
        async: false,
        data: {},
        success: function (data) {
            keyword = data;
        }
    });
    $('input#priKW').val(keyword);
}

function privateGame(){
    // TODO: PUT ALL INFORMATION HERE
    // Keyword and usernameS
    var keyword;
    $.ajax({
        url: "createGame.html/privateGame",
        type: "post",
        async: false,
        data: {username:username, keyword:keyword, user2:xxx, user3:xxx, user4:xxx},
        success: function (data) {
            $.cookie("comidId",data.gameId);
            $.cookie("panelNo",data.panelNo);
        }
    });
    document.location.href = "./createGame.html";  
}