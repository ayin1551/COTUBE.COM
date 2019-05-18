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
            console.log(data);
            obj = data;
        }
    });
    return obj;
}

function uploadCmcThumb(){
    var file = document.getElementById("file-input").files[0];
    console.log(file);
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = function() {
        $.ajax({
            url: "createComicDetail.html/uploadCmcThumb",
            type: "post",
            async: false,
            data: {comicId: $.cookie("comicId"),img:reader.result.substr(reader.result.indexOf(',') + 1)},
            success: function (data) {
            }
        });
        document.getElementById("newcomicthumb").src =reader.result;
    }
}

function uploadSrsThumb(){
    var file = document.getElementById("file-inputs").files[0];
    console.log(file);
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = function() {
        $.ajax({
            url: "createComicDetail.html/uploadSrsThumb",
            type: "post",
            async: false,
            data: {comicId: $.cookie("comicId"),img:reader.result.substr(reader.result.indexOf(',') + 1)},
            success: function (data) {
            }
        });
        document.getElementById("newseriesthumb").src = reader.result;
    }
}

function saveComic(){
    window.onbeforeunload = null;
    $.ajax({
        type: "post",
        url: "createComicDetail.html/save",
        async: false,
        data: {title:document.getElementById("comicTitle").value,
            descr:document.getElementById("comicDescription").value,
            comicId: $.cookie('comicId'),
            newSeries:document.getElementById("newSeries").value,
            thumb: "https://s3.amazonaws.com/cotubetest/comic-" + $.cookie("comicId") + "_thumbnail.png",
            seriesThumb: "https://s3.amazonaws.com/cotubetest/seriescomic-" + $.cookie("comicId") + "_thumbnail.png",
            existSeries: document.getElementById("existSeries").value,
            tag1: document.getElementById("tag1word").innerText,
            tag2: document.getElementById("tag2word").innerText,
            tag3: document.getElementById("tag3word").innerText,
            tag4:document.getElementById("tag4word").innerText,
            tag5: document.getElementById("tag5word").innerText},
        success: function(data){
        }
    });
}

function publishComic(){
    window.onbeforeunload = null;
    $.ajax({
        type: "post",
        url: "createComicDetail.html/publish",
        async: false,
        data: {title:document.getElementById("comicTitle").value,
            descr:document.getElementById("comicDescription").value,
            comicId: $.cookie('comicId'),
            newSeries:document.getElementById("newSeries").value,
            thumb: "https://s3.amazonaws.com/cotubetest/comic-" + $.cookie("comicId") + "_thumbnail.png",
            seriesThumb: "https://s3.amazonaws.com/cotubetest/seriescomic-" + $.cookie("comicId") + "_thumbnail.png",
            existSeries: document.getElementById("existSeries").value,
        tag1: document.getElementById("tag1word").innerText,
        tag2: document.getElementById("tag2word").innerText,
            tag3: document.getElementById("tag3word").innerText,
            tag4:document.getElementById("tag4word").innerText,
            tag5: document.getElementById("tag5word").innerText},
        success: function(data){

        }
    });
}

function unloadCancel(){
    var comicId = $.cookie("comicId");
    $.ajax({
        type: "post",
        url: "createComicDetail.html/cancel",
        async: false,
        dataType:"json",
        data: {comicId:comicId},
        success: function(data){
        }
    });
}