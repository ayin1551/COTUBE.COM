function getInfo(){
    var info;
    // $.cookie('comicId', '43');
    // $.cookie('panelNo', '2');
    var gameid = $.cookie('comicId');
    var panelNo = $.cookie('panelNo');
    $.ajax({
        url: "createGame.html/getInfo",
        type: "post",
        async: false,
        data: {gameId: gameid,current:panelNo},
        success: function (data) {//signUpController to check if the username already exist
          info = jQuery.parseJSON(data);
        }
    });
    document.getElementById("kwhere").innerText = info.keyword;
    for(let i=0;i<info.word.length;i++){
        if(info.num[i]!="this"){
            if(document.getElementById("img1").src.indexOf("/1.jpg")!=-1){
                document.getElementById("no1").innerText = info.num[i];
                if(info.path[i]!=null){
                    document.getElementById("img1").src = info.path[i];
                }else{
                    document.getElementById("img1").src = "../img/emptyComic.png";
                }
                if(info.word[i]!=null){
                    document.getElementById("wd1").innerText = info.word[i];
                }else{
                    document.getElementById("wd1").innerText = "Upcoming...";
                }
            }else if(document.getElementById("img2").src.indexOf("/2.jpg")!=-1){
                document.getElementById("no2").innerText = info.num[i];
                if(info.path[i]!=null){
                    document.getElementById("img2").src = info.path[i];
                }else{
                    document.getElementById("img2").src = "../img/emptyComic.png";
                }
                if(info.word[i]!=null){
                    document.getElementById("wd2").innerText = info.word[i];
                }else{
                    document.getElementById("wd2").innerText = "Upcoming...";
                }
            }else if(document.getElementById("img3").src.indexOf("/3.jpg")!=-1){
                document.getElementById("no3").innerText = info.num[i];
                if(info.path[i]!=null){
                    document.getElementById("img3").src = info.path[i];
                }else{
                    document.getElementById("img3").src = "../img/emptyComic.png";
                }
                if(info.word[i]!=null){
                    document.getElementById("wd3").innerText = info.word[i];
                }else{
                    document.getElementById("wd3").innerText = "Upcoming...";
                }
            }
        }
    }
}

function unloadCancel(){
    var username = $.cookie("username");
    var comicId = $.cookie("comicId");
    var panelNo = $.cookie("panelNo");
    $.ajax({
        type: "post",
        url: "createGameDetail.html/cancel",
        async: false,
        dataType:"json",
        data: {username: username, comicId:comicId, panelNo:panelNo},
        success: function(data){
        }
    });
}