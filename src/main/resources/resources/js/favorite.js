function togglePublic(){
    $.ajax({
        type: "post",
        url: "favorite.html/togglePublic",
        async: false,
        data: {favoriteId:$.cookie("favoriteId"),public:document.getElementById("publicCheck").checked},
        success: function(data){
            
        }
    });
}

function deleteFavoriteFolder(){
    $.ajax({
        type: "post",
        url: "favorite.html/deleteFolder",
        async: false,
        data: {favoriteId:$.cookie("favoriteId")},
        success: function(data){
        }
    });
    $.cookie('profileUsername', $.cookie('username'));
    document.location.href = "./profile.html";
}

function comicRedirect(id, ifSeries){
    $.cookie("comicId", id);
    $.cookie("ifSeries", ifSeries);
    document.location.href = "./viewComics.html";
}

function getComics(id){
    var obj;
    $.ajax({
        type: "post",
        url: "favorite.html/getComics",
        async: false,
        data: {favoriteId:id},
        success: function(data){
            obj = jQuery.parseJSON(data);
        }
    });
    return obj;
}

function unfavorite(favoriteId, comicId){
    $.ajax({
        type: "post",
        url: "favorite.html/unfavorite",
        async: false,
        data: {favoriteId:favoriteId, comicId:comicId},
        success: function(data){

        }
    });
    document.location.href = "./favorite.html";
}