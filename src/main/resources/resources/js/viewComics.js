function comicTitle(){
  var title;
  var comicid = $("input#comicid").val();
  $.ajax({
      url: "viewComics.html/comicTitle",
      type: "post",
      async: false,
      data: {comic_id:comicid},
      success: function (data) {//signUpController to check if the username already exist
        title = data;
      }
  });
  return title;
}

function comicInfo(){
  var obj;
  var comicid = $("input#comicid").val();
  $.ajax({
      url: "viewComics.html/comicInfo",
      type: "post",
      async: false,
      data: {comic_id:comicid},
      success: function (data) {//signUpController to check if the username already exist
        obj = jQuery.parseJSON(data);
      }
  });
  return obj;
}

function checkLike(){
  var validality = false;
  var comicid = $("input#comicid").val();
  var user = $.cookie("username");
  $.ajax({
      url: "viewComics.html/checkLike",
      type: "post",
      async: false,
      data: {username:user,comic_id:comicid},
      success: function (data) {//signUpController to check if the username already exist
        validality = data;
      }
  });
  return validality;
}

function likeNumber(){
  var num = 0;
  var comicid = $("input#comicid").val();
  $.ajax({
      url: "viewComics.html/likeNumber",
      type: "post",
      async: false,
      data: {comic_id:comicid},
      success: function (data) {//signUpController to check if the username already exist
        num = data;
      }
  });
  return num;
}

function checkFavorite(){
  var validality = false;
  var comicid = $("input#comicid").val();
  var user = $.cookie("username");
  $.ajax({
      url: "viewComics.html/checkFavorite",
      type: "post",
      async: false,
      data: {username:user,comic_id:comicid},
      success: function (data) {//signUpController to check if the username already exist
        validality = data;
      }
  });
  return validality;
}


function toggleLike(){
  var comicid = $("input#comicid").val();
  var user = $.cookie("username");
  var like = checkLike();
  $.ajax({
      url: "viewComics.html/toggleLike",
      type: "post",
      async: false,
      data: {username:user,comic_id:comicid,like:like},
      success: function (data) {//signUpController to check if the username already exist
        if(like){
          document.getElementById("likebtn").src ="./img/like-gray.png";
        }else{
          document.getElementById("likebtn").src ="./img/like-red.png";
        }
        document.getElementById("likenumber").innerHTML = likeNumber();
      }
  });
}

function postComment(){
  var comment = $("#commentText").val();
  $("#commentText").val('');
  var user = $.cookie("username");
  var comicid = $("input#comicid").val();
  $.ajax({
    url: "viewComics.html/postComment",
    type: "post",
    async: false,
    data: {username:user,comic_id:comicid,comment:comment},
    success: function (data) {//signUpController to check if the username already exist
      
    }
});

}