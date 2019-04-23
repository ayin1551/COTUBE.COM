function getComic(comicId){
    var obj;
    $.ajax({
        type: "post",
        url: "editComic.html/getComic",
        async: false,
        data: {comicId:comicId},
        success: function(data){
            obj = data;
        }
    });
    return obj;
}