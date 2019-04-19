function followUser(){
    var validality = false;
    var following = $("#profileusername").text();
    $.ajax({
        url: "profile.html/follow",
        type: "post",
        async: false,
        data: {username: $.cookie("username"),following:following},
        success: function (data) {//signUpController to check if the username already exist
          validality = data;
        }
    });
    if(validality){
        document.getElementById("follow").style.display = "none";
        document.getElementById("unfollow").style.display = "initial";
    }
}

function unfollowUser(){
    var validality = false;
    var unfollowing = $("#profileusername").text();
    $.ajax({
        url: "profile.html/unfollow",
        type: "post",
        async: false,
        data: {username: $.cookie("username"),unfollowing:unfollowing},
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
    var following = $("#profileusername").text();
    $.ajax({
        url: "profile.html/check",
        type: "post",
        async: false,
        data: {username: $.cookie("username"),following:following},
        success: function (data) {//signUpController to check if the username already exist
          validality = data;
        }
    });
    return validality;
}

function getViews(username){
    var num = 0;
    $.ajax({
        type: "post",
        url: "profile.html/getViews",
        async: false,
        data: {username:username},
        success: function (data) {
            num = data;
        }
    });
    return num;
}

function getLikes(username){
    var num = 0;
    $.ajax({
        type: "post",
        url: "profile.html/getLikes",
        async: false,
        data: {username:username},
        success: function (data) {
            num = data;
        }
    });
    return num;
}


function getFollowings(username){
    var num = 0;
    $.ajax({
        type: "post",
        url: "profile.html/getFollowingCount",
        async: false,
        data: {username:username},
        success: function (data) {
            num = data;
        }
    });
    return num;
}


function getFollowers(username){
    var num = 0;
    $.ajax({
        type: "post",
        url: "profile.html/getFollowerCount",
        async: false,
        data: {username:username},
        success: function (data) {
            num = data;
        }
    });
    return num;
}

function getFollowerList(username){
    var obj;
    $.ajax({
        type: "post",
        url: "profile.html/getFollowerList",
        async: false,
        data: {username:username},
        success: function(data){
            obj = jQuery.parseJSON(data);
        }
    });
    return obj;
}

function getFollowingList(username){
    var obj;
    $.ajax({
        type: "post",
        url: "profile.html/getFollowingList",
        async: false,
        data: {username:username},
        success: function(data){
            obj = jQuery.parseJSON(data);
        }
    });
    return obj;
}

function getPublicFavorites(username){
    var obj;
    $.ajax({
        type: "post",
        url: "profile.html/getPublicFavorites",
        async: false,
        data: {username:username},
        success: function(data){
            obj = jQuery.parseJSON(data);
        }
    });
    return obj;
}

function favoriteRedirect(id){
    $.cookie("favoriteId",id);
    document.location.href = "./favorite.html";
}

function seriesRedirect(id){
    $.cookie("seriesId",id);
    document.location.href = "./viewSeries.html";
}

function comicRedirect(id, ifSeries){
    $.cookie("comicId", id);
    $.cookie("ifSeries", ifSeries);
    document.location.href = "./viewComics.html";
}

//TODO:EDIT PAGE
function editRedirect(id){
    $.cookie("comicId", id);
    document.location.href = "./editComics.html";
}

function deleteComic(id){
    $.ajax({
        type: "post",
        url: "profile.html/deleteComic",
        async: false,
        data: {comicId:id},
        success: function(data){

        }
    });
}

function getMyFavorites(username){
    var obj;
    $.ajax({
        type: "post",
        url: "profile.html/getMyFavorites",
        async: false,
        data: {username:username},
        success: function(data){
            obj = jQuery.parseJSON(data);
        }
    });
    return obj;
}


function getSeries(username){
    var obj;
    $.ajax({
        type: "post",
        url: "profile.html/getSeries",
        async: false,
        data: {username:username},
        success: function(data){
            obj = jQuery.parseJSON(data);
        }
    });
    return obj;
}

function getMyComics(username){
    var obj;
    $.ajax({
        type: "post",
        url: "profile.html/getMyComics",
        async: false,
        data: {username:username},
        success: function(data){
            obj = jQuery.parseJSON(data);
        }
    });
    return obj;
}

function getOthersComics(username){
    var obj;
    $.ajax({
        type: "post",
        url: "profile.html/getOthersComics",
        async: false,
        data: {username:username},
        success: function(data){
            obj = jQuery.parseJSON(data);
        }
    });
    return obj;
}