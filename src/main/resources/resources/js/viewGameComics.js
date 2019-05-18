function checkExist(){
    var obj;
    var comicId = $.cookie("comicId");
    $.ajax({
        url: "viewGameComics.html/gameExist",
        type: "post",
        async: false,
        data: {comicId:comicId},
        success: function (data) {
          obj = data;
        }
    });
    if(!obj){
      document.location.href = "./index.html";
    }
}

function loadGameComics() {
    
}
function comicInfo(){
    var obj;
    var comicId = $.cookie("comicId");
    var username = $.cookie("username");
    $.ajax({
        url: "viewGameComics.html/comicInfo",
        type: "post",
        async: false,
        data: {comic_id:comicId, username:username},
        success: function (data) {
          obj = jQuery.parseJSON(data);
        }
    });
    return obj;
  }

  function toggleLike(){
    var comicid = $.cookie("comicId");
    var user = $.cookie("username");
    var like = !(document.getElementById("likebtn").src.indexOf("like-red.png")==-1);
    $.ajax({
        url: "viewGameComics.html/toggleLike",
        type: "post",
        async: false,
        data: {username:user,comic_id:comicid,like:like},
        success: function (data) {
          if(like){
            document.getElementById("likebtn").src ="./img/like-gray.png";
          }else{
            document.getElementById("likebtn").src ="./img/like-red.png";
          }
          document.getElementById("likenumber").innerHTML = data;
        }
    });
  }

  function getComment(id, num){
    var obj;
      $.ajax({
          type: "post",
          url: "viewGameComics.html/getComment",
          async: false,
          data: {comicId:id, num:num},
          success: function(data){
              obj = jQuery.parseJSON(data);
          }
      });
      return obj;
  }

  function postComment(){
    var comment = $("#commentText").val();
    $("#commentText").val('');
    var user = $.cookie("username");
    var comicid = $.cookie("comicId");
    if(comment!=""&&comment!=null&&comment!=undefined){
      $.ajax({
        url: "viewGameComics.html/postComment",
        type: "post",
        async: false,
        data: {username:user,comic_id:comicid,comment:comment},
        success: function (data) {
          
        }
      });
      document.location.href = "viewGameComics.html";
    }
  }
  
  function deleteComment(id, num){
    $.ajax({
      type: "post",
      url: "viewGameComics.html/deleteComment",
      async: false,
      data: {comicId:id, num:num},
      success: function(data){
  
      }
    });
    document.location.href = "./viewGameComics.html";
  }
  

  function viewComic(username, id){
    $.ajax({
      type: "post",
      url: "viewGameComics.html/viewComic",
      async: true,
      data: {username: username, comicId: id},
      success: function(data){
      }
    });
  }
