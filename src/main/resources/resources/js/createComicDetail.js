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
        data: {title:document.getElementById("comicTitle").value,
            descr:document.getElementById("comicDescription").value,
            thumb: null, comicId: $.cookie('comicId'),
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
        url: "createComicDetail.html/publish",
        async: false,
        data: {title:document.getElementById("comicTitle").value,
            descr:document.getElementById("comicDescription").value,
            thumb: null, comicId: $.cookie('comicId'),
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

