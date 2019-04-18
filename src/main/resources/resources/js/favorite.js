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