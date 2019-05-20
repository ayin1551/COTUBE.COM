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

function editRedirect(id){
    $.cookie("comicId", id);
    if(ifExistProfile()){
        document.location.href = "./editComic.html";
    }else{
        $.cookie('profileUsername', $.cookie('username'));
        document.location.href = "./profile.html";
    }
}

function ifExistProfile(){
    var obj;
    $.ajax({
      url: "viewComics.html/comicExistProfile",
      type: "post",
      async: false,
      data: {comicId:$.cookie('comicId')},
      success: function (data) {
        obj = data;
      }
    });
    return obj;
}

function ifExist(){
    var obj;
    $.ajax({
      url: "viewComics.html/comicExist",
      type: "post",
      async: false,
      data: {comicId:$.cookie('comicId')},
      success: function (data) {
        obj = data;
      }
    });
    return obj;
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
    $.cookie('profileUsername', $.cookie('username'));
    document.location.href = "./profile.html";
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

function getProfilePic(username){
    var obj;
    $.ajax({
        type: "post",
        url: "profile.html/getProfilePic",
        async: false,
        data: {username:username},
        success: function(data){
            obj = data;
        }
    });
    return obj;
}

function comicGameRedirect(id){
    $.cookie("comicId",id);
    document.location.href = "./viewGameComics.html";
}

function publicGameComic(id){
    $.ajax({
        type: "post",
        url: "profile.html/makePublic",
        async: false,
        data: {comicId:id},
        success: function(data){
        }
    });
    $.cookie('profileUsername', $.cookie('username'));
    document.location.href = "./profile.html";
}

function editGameRedirect(id,panelNo){
    $.cookie("comicId",id);
    $.cookie("panelNo",panelNo);
    document.location.href = "./createGame.html";
    
}

function submitadd(gameId){
    console.log("in js, gameId:", gameId);
    document.getElementById("add_error1").style.display = "none";
    document.getElementById("add_error2").style.display = "none";
    document.getElementById("add_error3").style.display = "none";
    document.getElementById("add_error4").style.display = "none";
    document.getElementById("add_error5").style.display = "none";
    var per1 = document.getElementById("add1name");
    var per2 = document.getElementById("add2name");
    var per3 = document.getElementById("add3name");
    var pos1 = document.getElementById("user1option");
    var pos2 = document.getElementById("user2option");
    var pos3 = document.getElementById("user3option");
    if(per1.innerText==""||per1.innerText==null){
        document.getElementById("add_error2").style.display = "block";
    }else if(per2.innerText==""||per2.innerText==null){
        if(pos1.value==" "){
            document.getElementById("add_error4").style.display = "block";
        }else{
            if(pos1.value=="2"){
                pos2.value = "3";
                pos3.value = "4";
            }
            else if(pos1.value=="3"){
                pos2.value = "2";
                pos3.value = "4";
            }
            else if(pos1.value=="4"){
                pos2.value = "2";
                pos3.value = "3";
            }
            $.ajax({
                type: "post",
                url: "profile.html/setUser",
                async: false,
                data: {gameId:gameId, user:per1.innerText, pos: pos1.value},
                success: function(data){
                    console.log("8");
                    // obj = jQuery.parseJSON(data);
                }
            });
            $.ajax({
                type: "post",
                url: "profile.html/setUser",
                async: false,
                data: {gameId:gameId, user:null, pos: pos2.value},
                success: function(data){
                    console.log("9");
                    // obj = jQuery.parseJSON(data);
                }
            });
            $.ajax({
                type: "post",
                url: "profile.html/setUser",
                async: false,
                data: {gameId:gameId, user:null, pos: pos3.value},
                success: function(data){
                    console.log("10");
                    // obj = jQuery.parseJSON(data);
                }
            });
            document.getElementById('addPopup').style.display = "none";
            cleanpopup();
        }
    }else if(per3.innerHTML==""||per3.innerText==null){
        if(pos1.value==pos2.value||pos1.value==" "||pos2.value==" "){
            document.getElementById("add_error4").style.display = "block";
        }else{
            if((pos1.value=="2"&&pos2.value=="3")||(pos1.value=="3"&&pos2.value=="2")){
                pos3.value = "4";
            }
            else if((pos1.value=="2"&&pos2.value=="4")||(pos1.value=="4"&&pos2.value=="2")){
                pos3.value = "3";
            }
            else if((pos1.value=="3"&&pos2.value=="4")||(pos1.value=="4"&&pos2.value=="3")){
                pos3.value = "2";
            }
            $.ajax({
                type: "post",
                url: "profile.html/setUser",
                async: false,
                data: {gameId:gameId, user:per1.innerText, pos: pos1.value},
                success: function(data){
                    console.log("16");
                }
            });
            $.ajax({
                type: "post",
                url: "profile.html/setUser",
                async: false,
                data: {gameId:gameId, user:per2.innerText, pos: pos2.value},
                success: function(data){
                    console.log("17");
                }
            });
            $.ajax({
                type: "post",
                url: "profile.html/setUser",
                async: false,
                data: {gameId:gameId, user:null, pos: pos3.value},
                success: function(data){
                    console.log("18");
                }
            });
            document.getElementById('addPopup').style.display = "none";
            cleanpopup();
        }
    }else if(pos1.value==" "||pos2.value==" "||pos3.value==" "||pos1.value==pos2.value||pos1.value==pos3.value||pos2.value==pos3.value){
        document.getElementById("add_error4").style.display = "block";
    }else{
        $.ajax({
            type: "post",
            url: "profile.html/setUser",
            async: false,
            data: {gameId:gameId, user:per1.innerText, pos: pos1.value},
            success: function(data){
                console.log("21");
            }
        });
        $.ajax({
            type: "post",
            url: "profile.html/setUser",
            async: false,
            data: {gameId:gameId, user:per2.innerText, pos: pos2.value},
            success: function(data){
                console.log("22");
            }
        });
        $.ajax({
            type: "post",
            url: "profile.html/setUser",
            async: false,
            data: {gameId:gameId, user:per3.innerText, pos: pos3.value},
            success: function(data){
                console.log("23");
            }
        });
        document.getElementById('addPopup').style.display = "none";
        cleanpopup();
    }
}

function cleanpopup(){
    document.getElementById("add1close").style.display = "contents";
    document.getElementById("add2close").style.display = "contents";
    document.getElementById("add3close").style.display = "contents";
    document.getElementById("user1option").disabled = false;
    document.getElementById("user2option").disabled = false;
    document.getElementById("user3option").disabled = false;
    document.getElementById('add_error1').style.display = "none";
    document.getElementById('add_error2').style.display = "none";
    document.getElementById('add_error3').style.display = "none";
    document.getElementById('add_error4').style.display = "none";
    document.getElementById("addbtn").disabled = false;
    document.getElementById('invite_people').value = "";
    delete3();
    delete2();
    delete1();
}

function delete1() {
    user1 = document.getElementById("add1name");
    user2 = document.getElementById("add2name");
    user3 = document.getElementById("add3name");
    if(user2.innerText==""||user2.innerText==null){
        user1.innerText = "";
        var tag1 = document.getElementById("addhere1");
        tag1.style.display = "none";
        var u1o = document.getElementById("u1o");
        u1o.style.display = "none";
        document.getElementById("user1option").value = " ";
    }else{
        user1.innerText = user2.innerText;
        document.getElementById("user1option").value = document.getElementById("user2option").value;
        delete2();
    }
}

function delete2() {
    user2 = document.getElementById("add2name");
    user3 = document.getElementById("add3name");
    if(user3.innerText==""||user3.innerText==null){
        user2.innerText = "";
        var tag2 = document.getElementById("addhere2");
        tag2.style.display = "none";
        var u2o = document.getElementById("u2o");
        u2o.style.display = "none";
        document.getElementById("user2option").value = " ";
    }else{
        user2.innerText = user3.innerText;
        document.getElementById("user2option").value = document.getElementById("user3option").value;
        delete3();
    }
}

function delete3() {
    user3 = document.getElementById("add3name");
    user3.innerText = "";
    var btndisable = document.getElementById("addbtn");
    btndisable.disabled = false;
    var tag3 = document.getElementById("addhere3");
    tag3.style.display = "none";
    var u3o = document.getElementById("u3o");
    u3o.style.display = "none";
    document.getElementById("user3option").value = " ";
}

function list_invite_user(game_comic_id){
    $.ajax({
        type: "post",
        url: "profile.html/list_invite_user",
        async: false,
        data: {game_comic_id:game_comic_id},
        success: function(data){
            obj = jQuery.parseJSON(data);
            for(let i=0; i<obj.finished.length; i++){
                if(obj.finished[i]==true){
                    addundeletableuser(obj.user[i], i+2);
                }
            }
            for(let j=0; j<obj.finished.length; j++){
                if(obj.finished[j]==false){
                    if(obj.user[j]!=null){
                        adddeleteableuser(obj.user[j], j+2);
                    }
                }
            }
            checkadd();
        }
    });
}

function addundeletableuser(name, position){
    var user1 = document.getElementById("add1name");
    var user2 = document.getElementById("add2name");
    var user3 = document.getElementById("add3name");
    if(user1.innerText==""||user1.innerText==null){
        user1.innerText = name;
        var tag1 = document.getElementById("addhere1");
        tag1.style.display = "block";
        var u1o = document.getElementById("u1o");
        u1o.style.display = "block";
        document.getElementById("add1close").style.display = "none";
        if(position=="2"){
            document.getElementById('user1_2').selected = "true";
        }
        if(position=="3"){
            document.getElementById('user1_3').selected = "true";
        }
        if(position=="4"){
            document.getElementById('user1_4').selected = "true";
        }
        document.getElementById("user1option").disabled = true;
    }
    else if(user2.innerText==""||user2.innerText==null){
        user2.innerText = name;
        var tag2 = document.getElementById("addhere2");
        tag2.style.display = "block";
        var u2o = document.getElementById("u2o");
        u2o.style.display = "block";
        document.getElementById("add2close").style.display = "none";
        if(position=="2"){
            document.getElementById('user2_2').selected = "true";
        }
        if(position=="3"){
            document.getElementById('user2_3').selected = "true";
        }
        if(position=="4"){
            document.getElementById('user2_4').selected = "true";
        }
        document.getElementById("user2option").disabled = true;
    }else if(user3.innerText==""||user3.innerText==null){
        user3.innerText = name;
        var tag3 = document.getElementById("addhere3");
        tag3.style.display = "block";
        var btndisable = document.getElementById("addbtn");
        btndisable.disabled = true;
        var u3o = document.getElementById("u3o");
        u3o.style.display = "block";
        document.getElementById("add3close").style.display = "none";
        if(position=="2"){
            document.getElementById('user3_2').selected = "true";
        }
        if(position=="3"){
            document.getElementById('user3_3').selected = "true";
        }
        if(position=="4"){
            document.getElementById('user3_4').selected = "true";
        }
        document.getElementById("user3option").disabled = true;
    }
}

function adddeleteableuser(name, position){
    var user1 = document.getElementById("add1name");
    var user2 = document.getElementById("add2name");
    var user3 = document.getElementById("add3name");
    if(user1.innerText==""||user1.innerText==null){
        user1.innerText = name;
        var tag1 = document.getElementById("addhere1");
        tag1.style.display = "block";
        var u1o = document.getElementById("u1o");
        u1o.style.display = "block";
        document.getElementById("add1close").style.display = "contents";
        if(position=="2"){
            document.getElementById('user1_2').selected = "true";
        }
        if(position=="3"){
            document.getElementById('user1_3').selected = "true";
        }
        if(position=="4"){
            document.getElementById('user1_4').selected = "true";
        }
        document.getElementById("user1option").disabled = false;
    }
    else if(user2.innerText==""||user2.innerText==null){
        user2.innerText = name;
        var tag2 = document.getElementById("addhere2");
        tag2.style.display = "block";
        var u2o = document.getElementById("u2o");
        u2o.style.display = "block";
        document.getElementById("add2close").style.display = "contents";
        if(position=="2"){
            document.getElementById('user2_2').selected = "true";
        }
        if(position=="3"){
            document.getElementById('user2_3').selected = "true";
        }
        if(position=="4"){
            document.getElementById('user2_4').selected = "true";
        }
        document.getElementById("user2option").disabled = false;
    }else if(user3.innerText==""||user3.innerText==null){
        user3.innerText = name;
        var tag3 = document.getElementById("addhere3");
        tag3.style.display = "block";
        var btndisable = document.getElementById("addbtn");
        btndisable.disabled = true;
        var u3o = document.getElementById("u3o");
        u3o.style.display = "block";
        document.getElementById("add3close").style.display = "contents";
        if(position=="2"){
            document.getElementById('user3_2').selected = "true";
        }
        if(position=="3"){
            document.getElementById('user3_3').selected = "true";
        }
        if(position=="4"){
            document.getElementById('user3_4').selected = "true";
        }
        document.getElementById("user3option").disabled = false;
    }
}

function checkadd(){
    var btndisable = document.getElementById("addbtn");
    var user3 = document.getElementById("add3name");
    if(user3.innerText!=""&&user3.innerText!=null){
        btndisable.disabled = true;
    }else{
        btndisable.disabled = false;
    }
}
 
function addUserBelow() {
    document.getElementById("add_error1").style.display = "none";
    document.getElementById("add_error2").style.display = "none";
    document.getElementById("add_error4").style.display = "none";
    document.getElementById("add_error3").style.display = "none";
    document.getElementById("add_error5").style.display = "none";
    var user1 = document.getElementById("add1name");
    var user2 = document.getElementById("add2name");
    var user3 = document.getElementById("add3name");
    console.log($.cookie('username'));
    console.log(document.getElementById("invite_people").value);
    if(document.getElementById("invite_people").value==""){
        // <!-- unfinished, user name empty -->
    }else if(document.getElementById("invite_people").value==$.cookie('username')){
    document.getElementById("add_error5").style.display = "block";
    }else{
        var validality;
        $.ajax({
            url: "profile.html/checkUser",
            type: "post",
            async: false,
            data: {username: document.getElementById("invite_people").value},
            success: function (data) {//signUpController to check if the username already exist
              validality = data;
            }
        });
        if(!validality){
            document.getElementById("add_error1").style.display = "block";
        }else{
            if(user1.innerText==""||user1.innerText==null){
                user1.innerText = document.getElementById("invite_people").value;
                var tag1 = document.getElementById("addhere1");
                tag1.style.display = "block";
                var u1o = document.getElementById("u1o");
                u1o.style.display = "block";
            }
            else if(user2.innerText==""||user2.innerText==null){
                user2.innerText = document.getElementById("invite_people").value;
                if(user2.innerText==user1.innerText){
                    document.getElementById("add_error3").style.display = "block";
                    user2.innerText = "";
                }else {
                    var tag2 = document.getElementById("addhere2");
                    tag2.style.display = "block";
                    var u2o = document.getElementById("u2o");
                    u2o.style.display = "block";
                }
            }else if(user3.innerText==""||user3.innerText==null){
                user3.innerText = document.getElementById("invite_people").value;
                if(user3.innerText==user1.innerText||user3.innerText==user2.innerText){
                    document.getElementById("add_error3").style.display = "block";
                    user3.innerText = "";
                }else {
                    var tag3 = document.getElementById("addhere3");
                    tag3.style.display = "block";
                    var btndisable = document.getElementById("addbtn");
                    btndisable.disabled = true;
                    var u3o = document.getElementById("u3o");
                    u3o.style.display = "block";
                }
            }
        }
    }
    document.getElementById("invite_people").value = "";
}
