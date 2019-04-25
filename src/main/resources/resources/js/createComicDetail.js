function getAllSeries(){
    var obj;
    console.log("HIT");
    $.ajax({
        type: "post",
        url: "createComicDetail.html/getSeries",
        async: false,
        dataType:"json",
        data: {username: $.cookie("username")},
        success: function(data){
            obj = data;
        }
    });
    return obj;
}


function uploadCmcThumb(){
    var file = document.querySelector('input[type=file]').files[0];
    var reader = new FileReader();
    reader.readAsDataURL(file);
    console.log("HELP");
    reader.onloadend = function() {
        $.ajax({
            url: "createComicDetail.html/uploadCmcThumb",
            type: "post",
            async: false,
            data: {comicId: $.cookie("comicId"),img:reader.result.substr(reader.result.indexOf(',') + 1)},
            success: function (data) {
            }
        });
        console.log(reader.result);
        //document.getElementById("oldthumb").src = reader.result;
    }
}

function saveComic(){
    $.ajax({
        type: "post",
        url: "createComicDetail.html/save",
        async: false,
        data: {title:document.getElementById("comicTitle"), descr:document.getElementById("comicDescription"),
            thumb:document.getElementById("file-input").value, comicId: $.cookie('comicId'), seriesId: document.getElementById("existSeries")},
        success: function(data){

        }
    });
}

function publishComic(){
    $.ajax({
        type: "post",
        url: "createComicDetail.html/publish",
        async: false,
        data: {title:document.getElementById("comicTitle").value, descr:document.getElementById("comicDescription").value,
            thumb: document.getElementById("file-input").files[0].name, comicId: $.cookie('comicId'), seriesId: document.getElementById("existSeries").value},
        success: function(data){

        }
    });
}

