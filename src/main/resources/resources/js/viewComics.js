function checkAuthor(){
    var validality = false;
    var comic = $("#comictitle").text();
    $.ajax({
        url: "viewComics.html/checkAuthor",
        type: "post",
        async: false,
        data: {username: $.cookie("username"),comic_name:comic},
        success: function (data) {//signUpController to check if the username already exist
          validality = data;
        }
    });
    return validality;
}