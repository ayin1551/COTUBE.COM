function getAllSeries(){
    var obj;
    console.log("HIT");
    $.ajax({
        type: "post",
        url: "editComicDetail.html/getSeries",
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

function getComicInfo(){
    var obj;
    console.log("HIT");
    $.ajax({
        type: "post",
        url: "editComicDetail.html/getComic",
        async: false,
        dataType:"json",
        data: {comicId: $.cookie("comicId")},
        success: function(data){
            console.log(data);
            obj = data;
        }
    });
    return obj;
}

function cancelEdit(){
    var obj;
    console.log("HIT");
    $.ajax({
        type: "post",
        url: "editComicDetail.html/cancel",
        async: false,
        dataType:"json",
        data: {comicId: $.cookie("comicId")},
        success: function(data){
            console.log(data);
            obj = data;
        }
    });
    return obj;
}

function pub_cancelEdit(){
    var obj;
    console.log("HIT");
    $.ajax({
        type: "post",
        url: "editComicDetail.html/pubcancel",
        async: false,
        dataType:"json",
        data: {comicId: $.cookie("comicId")},
        success: function(data){
            console.log(data);
            obj = data;
        }
    });
    return obj;
}

function pub_uploadCmcThumb(){
    var file = document.getElementById("pub_file-input").files[0];
    console.log(file);
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = function() {
        $.ajax({
            url: "editComicDetail.html/uploadCmcThumbPub",
            type: "post",
            async: false,
            data: {comicId: $.cookie("comicId"),img:reader.result.substr(reader.result.indexOf(',') + 1)},
            success: function (data) {
            }
        });
        document.getElementById("pub_newcomicthumb").src =reader.result;
    }
}

function uploadCmcThumb(){
    var file = document.getElementById("file-input").files[0];
    console.log(file);
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = function() {
        $.ajax({
            url: "editComicDetail.html/uploadCmcThumb",
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
            url: "editComicDetail.html/uploadSrsThumb",
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
    $.ajax({
        type: "post",
        url: "editComicDetail.html/save",
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
    $.ajax({
        type: "post",
        url: "editComicDetail.html/publish",
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

function publishPubComic(){
    $.ajax({
        type: "post",
        url: "editComicDetail.html/pubpublish",
        async: false,
        data: {title:document.getElementById("pub_comicTitle").value,
            descr:document.getElementById("pub_comicDescription").value,
            comicId: $.cookie('comicId'),
            thumb: "https://s3.amazonaws.com/cotubetest/comic-" + $.cookie("comicId") + "_thumbnail.png",
            tag1: document.getElementById("pub_tag1word").innerText,
            tag2: document.getElementById("pub_tag2word").innerText,
            tag3: document.getElementById("pub_tag3word").innerText,
            tag4:document.getElementById("pub_tag4word").innerText,
            tag5: document.getElementById("pub_tag5word").innerText},
        success: function(data){
        }
    });
}


