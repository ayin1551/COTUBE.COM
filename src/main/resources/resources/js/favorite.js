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
    document.location.href = "./profile.html";
}