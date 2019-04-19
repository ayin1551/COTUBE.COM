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

function removeFromFav(){
  var comicid = $("input#comicid").val();
  var user = $.cookie("username");
  $.ajax({
      url: "viewComics.html/removeFromFav",
      type: "post",
      async: false,
      data: {username:user,comic_id:comicid},
      success: function (data) {//signUpController to check if the username already exist
        document.getElementById("favbtn").src = "./img/star-empty.png";
      }
  });
}

function addToFav(obj){
  document.getElementById("fav_error1").style.display = "none";
  document.getElementById("fav_error2").style.display = "none";
  var comicid = $("input#comicid").val();
  var user = $.cookie("username");
  if(document.getElementById('thisIsCheckAdd').checked&&document.getElementById('thisIsInput').value==""){
    document.getElementById("fav_error1").style.display = "block";
    return false;
  }
  var ifCheck = false;
  for(let i=0; i<obj.name.length; i++){
    if(document.getElementById("thisIs"+obj.id[i]).checked){
      ifCheck = true;
      break;
    }
  }
  if(!ifCheck && !document.getElementById('thisIsCheckAdd').checked){
    document.getElementById("fav_error2").style.display = "block";
    return false;
  }
  var newlist = "";
  if(document.getElementById('thisIsCheckAdd').checked){
    newlist = document.getElementById('thisIsInput').value;
  }
  var comicid = $("input#comicid").val();
  var user = $.cookie("username");
  var idlist = [];
  var addto = 0;
  for(let i=0; i<obj.name.length;i++){
    if(document.getElementById("thisIs"+obj.id[i]).checked){
      idlist[addto] = obj.id[i];
      addto++;
    }
  }
  $.ajax({
      url: "viewComics.html/addToFav",
      type: "post",
      async: false,
      data: {username:user,comic_id:comicid,new_list:newlist,id_list:idlist},
      success: function (data) {//signUpController to check if the username already exist
        document.getElementById("favbtn").src = "./img/star.png";
      }
  });
  return true;
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


function getComment(id, num){
  var obj;
    $.ajax({
        type: "post",
        url: "viewComics.html/getComment",
        async: false,
        data: {comicId:id, num:num},
        success: function(data){
            obj = jQuery.parseJSON(data);
        }
    });
    return obj;
}