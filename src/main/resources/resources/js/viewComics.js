function ifExist(){
  $.ajax({
    url: "viewComics.html/comicExist",
    type: "post",
    async: false,
    data: {comicId:$.cookie('comicId')},
    success: function (data) {
      if(!data){
        alert("This comic does not exist");
        gohome();
      }
    }
});
}

function ifExistAdmin(){
  $.ajax({
    url: "viewComics.html/comicExistAdmin",
    type: "post",
    async: false,
    data: {comicId:$.cookie('comicId')},
    success: function (data) {
      if(!data){
        alert("This comic does not exist");
        gohome();
      }
    }
});
}

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
  var idlist = "";
  for(let i=0; i<obj.name.length;i++){
    if(document.getElementById("thisIs"+obj.id[i]).checked){
      idlist +=obj.id[i] + ",";
    }
  }
  console.log(idlist);
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
  if(comment!=""&&comment!=null&&comment!=undefined){
    $.ajax({
      url: "viewComics.html/postComment",
      type: "post",
      async: false,
      data: {username:user,comic_id:comicid,comment:comment},
      success: function (data) {//signUpController to check if the username already exist
        
      }
    });
    document.location.href = "viewComics.html";
  }
}

function deleteComic(){
  $.ajax({
      type: "post",
      url: "viewComics.html/deleteComic",
      async: false,
      data: {comicId:$.cookie('comicId')},
      success: function(data){

      }
  });
  $.cookie("profileUsername",$.cookie("username"));
  document.location.href = "./profile.html";
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

function deleteComment(id, num){
  $.ajax({
    type: "post",
    url: "viewComics.html/deleteComment",
    async: false,
    data: {comicId:id, num:num},
    success: function(data){

    }
  });
  document.location.href = "./viewComics.html";
}

function commentPage(id, num,maxnum){
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
  for(let i = 1;i <= maxnum; i++){
    document.getElementById("span"+i).style.color = "white";
  }
  document.getElementById("span"+num).style.color = "dodgerblue";

  $("#commenttb tr").remove();
  var tb = document.getElementById("commenttb");
  document.getElementById("commentHeader").innerText = "Comment("+comments.commentCount+")";
  for(let i = 0; i < comments.commentNumber.length; i++){
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
          input.addEventListener('click', function() {
            deleteComment($.cookie("comicId"),comments.commentNumber[i]);
          });
      }
      var hr = document.createElement('hr');
      hr.className = "style-six";
      td2.appendChild(hr);

  }
}

function hasPrev(){
  var validality = false;
  var comicId = $.cookie("comicId");
  $.ajax({
    type: "post",
    url: "viewComics.html/hasPrev",
    async: false,
    data: {comicId:comicId},
    success: function(data){
      validality = data;
    }
  });
  return validality;
}

function hasNext(){
  var validality = false;
  var comicId = $.cookie("comicId");
  $.ajax({
    type: "post",
    url: "viewComics.html/hasNext",
    async: false,
    data: {comicId:comicId},
    success: function(data){
      validality = data;
    }
  });
  return validality;
}

function prev(){
  var prevId;
  var comicId = $.cookie("comicId");
  $.ajax({
    type: "post",
    url: "viewComics.html/prev",
    async: false,
    data: {comicId:comicId},
    success: function(data){
      prevId = data;
    }
  });
  $.cookie("comicId",prevId);
  document.location.href = "./viewComics.html";
}

function next(){
  var nextId;
  var comicId = $.cookie("comicId");
  $.ajax({
    type: "post",
    url: "viewComics.html/next",
    async: false,
    data: {comicId:comicId},
    success: function(data){
      nextId = data;
    }
  });
  $.cookie("comicId",nextId);
  document.location.href = "./viewComics.html";
}

function viewComic(username, id){
  $.ajax({
    type: "post",
    url: "viewComics.html/viewComic",
    async: true,
    data: {username: username, comicId: id},
    success: function(data){
    }
  });
}