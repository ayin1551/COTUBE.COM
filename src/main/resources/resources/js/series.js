function ifSeriesExist(){
    $.ajax({
        url: "viewSeries.html/seriesExist",
        type: "post",
        async: false,
        data: {seriesId:$.cookie('seriesId')},
        success: function (data) {//signUpController to check if the username already exist
          if(!data){
              alert("This series has been deleted.");
              gohome();
          }
        }
    });
}

function followSeries(){
    var validality = false;
    var id = $.cookie("seriesId");
    $.ajax({
        url: "viewSeries.html/follow",
        type: "post",
        async: false,
        data: {username: $.cookie("username"),seriesId:id},
        success: function (data) {//signUpController to check if the username already exist
          validality = data;
        }
    });
    if(validality){
        document.getElementById("follow").style.display = "none";
        document.getElementById("unfollow").style.display = "initial";
    }
}

function unfollowSeries(){
    var validality = false;
    var id = $.cookie("seriesId");
    $.ajax({
        url: "viewSeries.html/unfollow",
        type: "post",
        async: false,
        data: {username: $.cookie("username"),seriesId:id},
        success: function (data) {//signUpController to check if the username already exist
          validality = data;
        }
    });
    if(validality){
        document.getElementById("unfollow").style.display = "none";
        document.getElementById("follow").style.display = "initial";
    }
}

function checkFollow(){
    var validality = false;
    var id = $.cookie("seriesId");
    $.ajax({
        url: "viewSeries.html/check",
        type: "post",
        async: false,
        data: {username: $.cookie("username"),seriesId:id},
        success: function (data) {
          validality = data;
        }
    });
    return validality;
}


function getInfo(id){
    var obj;
    $.ajax({
        type: "post",
        url: "viewSeries.html/getInfo",
        async: false,
        data: {seriesId:id},
        success: function(data){
            obj = jQuery.parseJSON(data);
        }
    });
    return obj;
}

function getComics(id){
    var obj;
    $.ajax({
        type: "post",
        url: "viewSeries.html/getComics",
        async: false,
        data: {seriesId:id},
        success: function(data){
            obj = jQuery.parseJSON(data);
        }
    });
    return obj;
}

function editRedirect(id){
    $.cookie("comicId", id);
    document.location.href = "./editComic.html";
}


function comicRedirect(id, ifSeries){
    $.cookie("comicId", id);
    $.cookie("ifSeries", ifSeries);
    document.location.href = "./viewComics.html";
}

function deleteComic(id){
    $.ajax({
        type: "post",
        url: "viewSeries.html/deleteComic",
        async: false,
        data: {comicId:id},
        success: function(data){

        }
    });
    document.location.href = "./viewSeries.html";
}

function deleteSeries(){
    $.ajax({
        type: "post",
        url: "viewSeries.html/deleteSeries",
        async: false,
        data: {seriesId:$.cookie("seriesId")},
        success: function(data){

        }
    });
    $.cookie("profileUsername",$.cookie("username"));
    document.location.href = "./profile.html";
}
