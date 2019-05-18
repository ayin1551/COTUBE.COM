function resetPassword(){
    var validality = false;

    var original_password = document.getElementById("original_password").value;
    var new_password = document.getElementById("new_password").value;
    var cfm_new_password = document.getElementById("cfm_new_password").value;

    if(new_password!=cfm_new_password){
        var box_opacity = $('#error1').css('opacity');
        if(box_opacity==1){
            $("#error1").fadeOut(50);
            $("#error1").fadeIn(500);
            var box_opacity2 = $('#successResetPassword').css('opacity');
            if(box_opacity2==1){
                $("#successResetPassword").fadeOut(50);
            }
            return;
        }
    }

    $.ajax({
        url: "setting.html/resetPassword",
        type: "post",
        async: false,
        data: {username: $.cookie("username"),password:original_password,new_password:new_password},
        success: function (data) {//signUpController to check if the username already exist
          validality = data;
        }
    });
    if(!validality){
        var box_opacity = $('#error').css('opacity');
        if(box_opacity==1){
            $("#error").fadeOut(50);
            $("#error").fadeIn(500);
        }
        var box_opacity2 = $('#successResetPassword').css('opacity');
        if(box_opacity2==1){
            $("#successResetPassword").fadeOut(50);
        }
    }else{
        var box_opacity = $('#successResetPassword').css('opacity');
        if(box_opacity==1){
            $("#successResetPassword").fadeOut(50);
            $("#successResetPassword").fadeIn(500);
        }
    }
}

function resetSecurityQuestion(){
    var validality = false;
    var original_question = document.getElementById("security_question").value;
    var original_answer = document.getElementById("original_answer").value;
    var new_question = document.getElementById("new_security_question").value;
    var new_answer = document.getElementById("new_answer").value;

    $.ajax({
        url: "setting.html/resetQuestion",
        type: "post",
        async: false,
        data: {username: $.cookie("username"),original_question:original_question,original_answer:original_answer,new_question:new_question,new_answer:new_answer},
        success: function (data) {//signUpController to check if the username already exist
          validality = data;
        }
    });

    if(!validality){
        var box_opacity = $('#error_answer').css('opacity');
        if(box_opacity==1){
            $("#error_answer").fadeOut(50);
            $("#error_answer").fadeIn(500);
        }
        var box_opacity2 = $('#successResetQuestion').css('opacity');
        if(box_opacity2==1){
            $("#successResetQuestion").fadeOut(50);
        }
    }else{
        var box_opacity = $('#successResetQuestion').css('opacity');
        if(box_opacity==1){
            $("#successResetQuestion").fadeOut(50);
            $("#successResetQuestion").fadeIn(500);
        }
    }
}
function loadProfilePicture(){
    $.ajax({
        url: "setting.html/loadProfile",
        type: "post",
        async: false,
        data: {username: $.cookie("username")},
        success: function (data) {
            if(data){
                document.getElementById("oldpp").src = data;
            }
        }
    });
}
function changeProfile(){
    var imgsrc = document.getElementById("newpp").src;
    //alert(imgsrc);
    $.ajax({
        url: "setting.html/changeProfile",
        type: "post",
        async: false,
        data: {username: $.cookie("username"),img: imgsrc},
        success: function (data) {

            document.getElementById("oldpp").src = document.getElementById("newpp").src;

        }
    });
    //loadProfilePicture();
}

function changeNewPP(){
    var file = document.querySelector('input[type=file]').files[0];
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = function() {
        $.ajax({
            url: "setting.html/uploadPicture",
            type: "post",
            async: false,
            data: {username: $.cookie("username"),img:reader.result.substr(reader.result.indexOf(',') + 1)},
            success: function (data) {
            }
        });
        document.getElementById("newpp").src = reader.result;
    }
  
}