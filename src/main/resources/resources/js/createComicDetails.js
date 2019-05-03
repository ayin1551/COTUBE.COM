function getAllSeries(){
    var obj;
    console.log("HIT");
    $.ajax({
        type: "post",
        url: "createComicDetail.html/getSeries",
        async: false,
        dataType:"json",
        data: {username: $.cookie("username")},
        success: function(data){
            console.log(data);
            obj = data;
        }
    });
    return obj;
}

function uploadCmcThumb(){
    var file = document.getElementById("file-input").files[0];
    console.log(file);
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = function() {
        $.ajax({
            url: "createComicDetail.html/uploadCmcThumb",
            type: "post",
            async: false,
            data: {comicId: $.cookie("comicId"),img:reader.result.substr(reader.result.indexOf(',') + 1)},
            success: function (data) {
            }
        });
        document.getElementById("newcomicthumb").src =reader.result;
    }
}

function uploadSrsThumb(){
    var file = document.getElementById("file-inputs").files[0];
    console.log(file);
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = function() {
        $.ajax({
            url: "createComicDetail.html/uploadSrsThumb",
            type: "post",
            async: false,
            data: {comicId: $.cookie("comicId"),img:reader.result.substr(reader.result.indexOf(',') + 1)},
            success: function (data) {
            }
        });
        document.getElementById("newseriesthumb").src = reader.result;
    }
}

function saveComic(){
    $.ajax({
        type: "post",
        url: "createComicDetail.html/save",
        async: false,
        data: {title:document.getElementById("comicTitle").value,
            descr:document.getElementById("comicDescription").value,
            comicId: $.cookie('comicId'),
            newSeries:document.getElementById("newSeries").value,
            thumb: "https://s3.amazonaws.com/cotubetest/comic-" + $.cookie("comicId") + "_thumbnail.png",
            seriesThumb: "https://s3.amazonaws.com/cotubetest/seriescomic-" + $.cookie("comicId") + "_thumbnail.png",
            existSeries: document.getElementById("existSeries").value,
            tag1: document.getElementById("tag1word").innerText,
            tag2: document.getElementById("tag2word").innerText,
            tag3: document.getElementById("tag3word").innerText,
            tag4:document.getElementById("tag4word").innerText,
            tag5: document.getElementById("tag5word").innerText},
        success: function(data){
        }
    });
}

function publishComic(){
    $.ajax({
        type: "post",
        url: "createComicDetail.html/publish",
        async: false,
        data: {title:document.getElementById("comicTitle").value,
            descr:document.getElementById("comicDescription").value,
            comicId: $.cookie('comicId'),
            newSeries:document.getElementById("newSeries").value,
            thumb: "https://s3.amazonaws.com/cotubetest/comic-" + $.cookie("comicId") + "_thumbnail.png",
            seriesThumb: "https://s3.amazonaws.com/cotubetest/seriescomic-" + $.cookie("comicId") + "_thumbnail.png",
            existSeries: document.getElementById("existSeries").value,
        tag1: document.getElementById("tag1word").innerText,
        tag2: document.getElementById("tag2word").innerText,
            tag3: document.getElementById("tag3word").innerText,
            tag4:document.getElementById("tag4word").innerText,
            tag5: document.getElementById("tag5word").innerText},
        success: function(data){

        }
    });
}

function getInfo(){
    var info;
    // $.cookie('comicId', '43');
    // $.cookie('panelNo', '2');
    var gameid = $.cookie('comicId');
    var panelNo = $.cookie('panelNo');
    $.ajax({
        url: "createGameDetail.html/getInfo",
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
        }else{

        }
    }
}