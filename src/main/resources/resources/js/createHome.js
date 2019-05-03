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
    var keyword = document.getElementById("userKW").value; 
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
    var keyword = document.getElementById("userKW").value;
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
    document.getElementById("error6").style.display = "block";
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

function privateGame(user2, user3, user4){
    var keyword = document.getElementById("priKW").value;
    $.ajax({
        url: "createGame.html/privateGame",
        type: "post",
        async: false,
        data: {username:username, keyword:keyword, user2:user2, user3:user3, user4:user4},
        success: function (data) {
            $.cookie("comidId",data.gameId);
            $.cookie("panelNo",data.panelNo);
        }
    });
    document.location.href = "./createGame.html";  
}

