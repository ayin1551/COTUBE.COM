function followUser(){
    var validality = false;
    var following = "majik";
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
    var unfollowing = "majik";
    $.ajax({
        url: "profile.html/unfollow",
        type: "post",
        async: false,
        data: {username: $.cookie("username"),following:unfollowing},
        success: function (data) {//signUpController to check if the username already exist
          validality = data;
        }
    });
    if(validality){
        document.getElementById("unfollow").style.display = "none";
        document.getElementById("follow").style.display = "initial";
    }
}