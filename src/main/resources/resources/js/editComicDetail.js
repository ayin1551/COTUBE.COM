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


function uploadCmcThumb(){
    var file = document.querySelector('input[type=file]').files[0];
    var reader = new FileReader();
    reader.readAsDataURL(file);
    console.log("HELP");
    reader.onloadend = function() {
        $.ajax({
            url: "editComicDetail.html/uploadCmcThumb",
            type: "post",
            async: false,
            data: {comicId: $.cookie("comicId"),img:reader.result.substr(reader.result.indexOf(',') + 1)},
            success: function (data) {
            }
        });
        console.log(reader.result);
        //document.getElementById("thumb").src = reader.result;
    }
}
function uploadSrsThumb(){
    var file = document.querySelector('input[type=file]').files[0];
    var reader = new FileReader();
    reader.readAsDataURL(file);
    console.log("HELP");
    reader.onloadend = function() {
        $.ajax({
            url: "createComicDetail.html/uploadSrsThumb",
            type: "post",
            async: false,
            data: {comicId: $.cookie("comicId"),img:reader.result.substr(reader.result.indexOf(',') + 1)},
            success: function (data) {
            }
        });
        console.log(reader.result);
        //document.getElementById("srsthumb").src = reader.result;
    }
}

function saveComic(){
    $.ajax({
        type: "post",
        url: "editComicDetail.html/save",
        async: false,
        data: {title:document.getElementById("comicTitle").value,
            descr:document.getElementById("comicDescription").value,
            thumb: null, seriesThumb: null,
            //thumb:document.getElementById("file-input").value,
            //seriesThumb: document.getElementById("file-inputs").value,
            comicId: $.cookie('comicId'),
            newSeries:document.getElementById("newSeries").value,
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
            thumb: null, seriesThumb: null,
            //thumb:document.getElementById("file-input").value,
            //seriesThumb: document.getElementById("file-inputs").value,
            comicId: $.cookie('comicId'),
            newSeries:document.getElementById("newSeries").value,
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
        data: {title:document.getElementById("comicTitle").value,
            descr:document.getElementById("comicDescription").value,
            thumb: null,
            //thumb:document.getElementById("pub_file-input").value,
            comicId: $.cookie('comicId'),
            tag1: document.getElementById("tag1word").innerText,
            tag2: document.getElementById("tag2word").innerText,
            tag3: document.getElementById("tag3word").innerText,
            tag4:document.getElementById("tag4word").innerText,
            tag5: document.getElementById("tag5word").innerText},
        success: function(data){
        }
    });
}


