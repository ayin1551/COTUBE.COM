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

function commentPage(id, num){
  var comments;
  $.ajax({
      type: "post",
      url: "viewComics.html/getComment",
      async: false,
      data: {comicId:id, num:num},
      success: function(data){
          comments = jQuery.parseJSON(data);
      }
  });
  document.getElementById("span"+num).style.color = "dodgerblue";
  $("#commenttb tr").remove();
  var tb = document.getElementById("commenttb");
  document.getElementById("commentHeader").innerText = "Comment("+comments.commentCount+")";
  for(var i = 0; i < comments.commentNumber.length; i++){
      var tr1 = document.createElement('tr');
      tb.appendChild(tr1);
      var td = document.createElement('td');
      tr1.appendChild(td);
      var span = document.createElement('span');
      var t = document.createTextNode(comments.commentContent[i]);
      span.appendChild(t);
      td.appendChild(span);
      span.style.marginLeft = "0px";
      

      var tr2 = document.createElement('tr');
      tb.appendChild(tr2);
      var td2 = document.createElement('td');
      tr2.appendChild(td2);
      var span2 = document.createElement('span');
      var t2 = document.createTextNode("#"+comments.commentNumber[i]);
      span2.appendChild(t2);
      td2.appendChild(span2);
      span2.style.marginLeft = "0px";

      var a = document.createElement('a');
      a.href = "./profile.html";
      a.style.cssFloat = "right";
      var t3 = document.createTextNode(comments.commenter[i]);
      a.appendChild(t3);
      td2.appendChild(a);

      var span3 = document.createElement('span');
      var t4 = document.createTextNode("by");
      span3.appendChild(t4);
      span3.style.cssFloat = "right";
      span3.style.marginRight = "0.5em";
      td2.appendChild(span3);

      var span4 = document.createElement('span');
      var t5 = document.createTextNode(comments.commentTime[i]);
      span4.appendChild(t5);
      span4.style.cssFloat = "right";
      span4.style.color = "gray";
      span4.style.marginRight = "0.5em";
      td2.appendChild(span4);

      if($.cookie("username")==comments.commenter[i]){
          var input = document.createElement('input');
          td2.appendChild(input);
          input.type = "image";
          input.src = "./img/delete.png";
          input.style.marginRight = "5px";
          input.style.cssFloat = "right";
          input.style.width = "20px";
          input.style.height = "20px";
      }
      

  }
}