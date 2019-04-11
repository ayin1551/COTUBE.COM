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
      }
  });
}