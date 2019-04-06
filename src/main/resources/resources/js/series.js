function followSeries(){
    var validality = false;
    var following = "majik";
    $.ajax({
        url: "series.html/follow",
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

function unfollowSeries(){
    var validality = false;
    var unfollowing = "majik";
    $.ajax({
        url: "series.html/unfollow",
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
    var following = "majik";
    $.ajax({
        url: "series.html/check",
        type: "post",
        async: false,
        data: {username: $.cookie("username"),following:following},
        success: function (data) {//signUpController to check if the username already exist
          validality = data;
        }
    });
    return validality;
}