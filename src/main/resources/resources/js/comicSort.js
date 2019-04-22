function checksortby(){
    if(document.getElementById("series_regular").style.display == "block"){
        if(document.getElementById("selectseries").selected==true){
            document.getElementById("keywordsortby").style.display = "none";
        }else{
            document.getElementById("keywordsortby").style.display = "block";
        }
    }
}


function getTable(){
    if($.cookie('searchComicType')=='series'){
        option = 'series';
        document.getElementById("selectseries").selected = true;
    }
    var selector = document.getElementById("series_regular_view");
    var option = selector.options[selector.selectedIndex].value;
    console.log("option:" + option);
    checksortby();
    if (option == "series"){
        getSeriesTable();
    }
    else{
        refreshTable();
    }
}
function getSeriesTable(){
    var kw = $.cookie('search_word');
    var selector = document.getElementById("sort_by");
    var option = selector.options[selector.selectedIndex].value;
    var choice;
    if (option == "view"){
        choice = "1";
    }
    else if(option == "likes"){
        choice = "2";
    }
    else{
        choice = "3";
    }
    $.ajax({
        url: "searchResult.html/seriesSearch",
        type: "post",
        async: false,
        data: {keyword: kw,sorted: choice},
        success: function (data) {
            console.log(data);
            var obj = jQuery.parseJSON(data);
            var tbody = document.getElementById("search_result_table");
            tbody.innerHTML = "";
            if (obj.TPALV.length == 0) {
                document.getElementById("authorEmpty").style.display = "block";
            } else {
                document.getElementById("authorEmpty").style.display = "none";
            }
            for (let i = 0; i < obj.TPALV.length; i += 3) {
                var tr = document.createElement('TR');
                tbody.appendChild(tr);

                // i = 0, first one
                var td1 = document.createElement('TD');
                var img1 = document.createElement('img');
                img1.style.width = "17.6vw";
                img1.style.height = "9.9vw";
                img1.src = obj["TPALV"][i].picPath;
                td1.appendChild(img1);
                img1.addEventListener('click', function () {
                    goViewSeries(obj["TPALV"][i].seriesID);
                });
                var td2 = document.createElement('TD');
                td2.style = "padding-right: 20px; padding-bottom: 40px; width:12vw; word-wrap: break-word; height: 9.9vw;";
                var span_title = document.createElement('p');
                var t = document.createTextNode(obj["TPALV"][i].title);
                span_title.appendChild(t);
                span_title.style.width = "11vw";
                span_title.addEventListener('click', function () {
                    goViewSeries(obj["TPALV"][i].seriesID);
                });
                var span_author = document.createElement('p');
                var t = document.createTextNode(obj["TPALV"][i].author);
                span_author.appendChild(t);
                span_author.addEventListener('click', function () {
                    authorprofile(obj["TPALV"][i].author);
                });
                var span_total = document.createElement('p');
                var t = document.createTextNode("Num comics: " + obj["TPALV"][i].total);
                td2.appendChild(span_title);
                td2.appendChild(span_author);
                td2.appendChild(span_total);
                tr.appendChild(td1);
                tr.appendChild(td2);

                //divinfo1.appendChild(span_author1);
                //divinfo1.appendChild(document.createElement('br'));
                //divinfo1.appendChild(span_follower1);
                //divinfo1.appendChild(document.createElement('span').appendChild(document.createTextNode(" follower")));
                // add to divBig1
                //divBig1.appendChild(divImg1);
                //divBig1.appendChild(divinfo1);

                // i = 1, second one
                var td2 = document.createElement('TD');
                tr.appendChild(td2);
                if (i + 1 < obj.TPALV.length) {
                    var td1 = document.createElement('TD');
                    var img1 = document.createElement('img');
                    img1.style.width = "17.6vw";
                    img1.style.height = "9.9vw";
                    img1.src = obj["TPALV"][i + 1].picPath;
                    td1.appendChild(img1);
                    img1.addEventListener('click', function () {
                        goViewSeries(obj["TPALV"][i + 1].seriesID);
                    });
                    var td2 = document.createElement('TD');
                    td2.style = "padding-right: 20px; padding-bottom: 40px; width:12vw; word-wrap: break-word; height: 9.9vw;";
                    var span_title = document.createElement('p');
                    var t = document.createTextNode(obj["TPALV"][i + 1].title);
                    span_title.appendChild(t);
                    span_title.style.width = "11vw";
                    span_title.addEventListener('click', function () {
                        goViewSeries(obj["TPALV"][i + 1].seriesID);
                    });
                    td2.appendChild(span_title);
                    tr.appendChild(td1);
                    tr.appendChild(td2);

                }

                // i = 2, first one
                var td3 = document.createElement('TD');
                tr.appendChild(td3);

                if (i + 2 < obj.TPALV.length) {
                    var td1 = document.createElement('TD');
                    var img1 = document.createElement('img');
                    img1.style.width = "17.6vw";
                    img1.style.height = "9.9vw";
                    img1.src = obj["TPALV"][i + 2].picPath;
                    td1.appendChild(img1);
                    img1.addEventListener('click', function () {
                        goViewSeries(obj["TPALV"][i + 2].seriesID);
                    });
                    var td2 = document.createElement('TD');
                    td2.style = "padding-right: 20px; padding-bottom: 40px; width:11vw; word-wrap: break-word; height: 9.9vw;";
                    var span_title = document.createElement('p');
                    var t = document.createTextNode(obj["TPALV"][i + 2].title);
                    span_title.appendChild(t);
                    span_title.style.width = "11vw";
                    span_title.addEventListener('click', function () {
                        goViewSeries(obj["TPALV"][i + 2].seriesID);
                    });
                    td2.appendChild(span_title);
                    tr.appendChild(td1);
                    tr.appendChild(td2);
                }
            }
        }
    });
}
function refreshTable(){
    var selector = document.getElementById("sort_by");
    var option = selector.options[selector.selectedIndex].value;
    var choice;

    if (option == "view"){
        choice = "1";
    }
    else if(option == "like"){
        choice = "2";
    }
    else if(option == "nameA"){
        choice = "3";
    }
    //console.log(option);
    //console.log(choice);
    $.ajax({
        url: "searchResult.html/keyword",
        type: "post",
        async: false,
        data: {keyword: kw,sorted: choice},
        success: function (data) {
            console.log(data);
            var obj = jQuery.parseJSON(data);
            var tbody = document.getElementById("search_result_table");
            tbody.innerHTML = "";
            if (obj.TPALV.length == 0) {
                document.getElementById("authorEmpty").style.display = "block";
            } else {
                document.getElementById("authorEmpty").style.display = "none";
            }
            for (let i = 0; i < obj.TPALV.length; i += 3) {
                var tr = document.createElement('TR');
                tbody.appendChild(tr);

                // i = 0, first one
                var td1 = document.createElement('TD');
                var img1 = document.createElement('img');
                img1.style.width = "17.6vw";
                img1.style.height = "9.9vw";
                img1.src = obj["TPALV"][i].picPath;
                td1.appendChild(img1);
                img1.addEventListener('click', function () {
                    goViewComic(obj["TPALV"][i].comicID);
                });
                var td2 = document.createElement('TD');
                td2.style = "padding-right: 20px; padding-bottom: 40px; width:12vw; word-wrap: break-word; height: 9.9vw;";
                var span_title = document.createElement('p');
                var t = document.createTextNode(obj["TPALV"][i].title);
                span_title.appendChild(t);
                span_title.style.width = "11vw";
                var span_author1 = document.createElement('span');
                span_author1.addEventListener('click', function () {
                    authorprofile(obj["TPALV"][i].author);
                });
                var t = document.createTextNode(obj["TPALV"][i].author);
                span_author1.appendChild(t);
                var span_likes = document.createElement('p');
                var t = document.createTextNode("Likes: " + obj["TPALV"][i].likes);
                span_likes.append(t);
                var span_views = document.createElement('p');
                var t = document.createTextNode("Views: " + obj["TPALV"][i].views);
                span_views.append(t);
                td2.appendChild(span_title);
                td2.appendChild(span_author1);
                td2.appendChild(span_likes);
                td2.appendChild(span_views);
                tr.appendChild(td1);
                tr.appendChild(td2);

                //divinfo1.appendChild(span_author1);
                //divinfo1.appendChild(document.createElement('br'));
                //divinfo1.appendChild(span_follower1);
                //divinfo1.appendChild(document.createElement('span').appendChild(document.createTextNode(" follower")));
                // add to divBig1
                //divBig1.appendChild(divImg1);
                //divBig1.appendChild(divinfo1);

                // i = 1, second one
                var td2 = document.createElement('TD');
                tr.appendChild(td2);
                if (i + 1 < obj.TPALV.length) {
                    var td1 = document.createElement('TD');
                    var img1 = document.createElement('img');
                    img1.style.width = "17.6vw";
                    img1.style.height = "9.9vw";
                    img1.src = obj["TPALV"][i+1].picPath;
                    td1.appendChild(img1);
                    img1.addEventListener('click', function () {
                        goViewComic(obj["TPALV"][i+1].comicID);
                    });
                    var td2 = document.createElement('TD');
                    td2.style = "padding-right: 20px; padding-bottom: 40px; width:12vw; word-wrap: break-word; height: 9.9vw;";
                    var span_title = document.createElement('p');
                    var t = document.createTextNode(obj["TPALV"][i+1].title);
                    span_title.appendChild(t);
                    span_title.style.width = "11vw";
                    var span_author1 = document.createElement('span');
                    //span_author1.addEventListener("click",authorprofile(obj["TPALV"][i].author));
                    span_author1.addEventListener('click', function () {
                        authorprofile(obj["TPALV"][i+1].author);
                    });
                    var t = document.createTextNode(obj["TPALV"][i+1].author);
                    span_author1.appendChild(t);
                    var span_likes = document.createElement('p');
                    var t = document.createTextNode("Likes: " + obj["TPALV"][i+1].likes);
                    span_likes.append(t);
                    var span_views = document.createElement('p');
                    var t = document.createTextNode("Views: " + obj["TPALV"][i+1].views);
                    span_views.append(t);
                    td2.appendChild(span_title);
                    td2.appendChild(span_author1);
                    td2.appendChild(span_likes);
                    td2.appendChild(span_views);
                    tr.appendChild(td1);
                    tr.appendChild(td2);
                }

                // i = 2, first one
                var td3 = document.createElement('TD');
                tr.appendChild(td3);

                if (i + 2 < obj.TPALV.length) {
                    var td1 = document.createElement('TD');
                    var img1 = document.createElement('img');
                    img1.style.width = "17.6vw";
                    img1.style.height = "9.9vw";
                    img1.src = obj["TPALV"][i+2].picPath;
                    td1.appendChild(img1);
                    img1.addEventListener('click', function () {
                        goViewComic(obj["TPALV"][i+2].comicID);
                    });
                    var td2 = document.createElement('TD');
                    td2.style = "padding-right: 20px; padding-bottom: 40px; width:11vw; word-wrap: break-word; height: 9.9vw;";
                    var span_title = document.createElement('p');
                    var t = document.createTextNode(obj["TPALV"][i+2].title);
                    span_title.appendChild(t);
                    span_title.style.width = "11vw";
                    var span_author1 = document.createElement('span');
                    //span_author1.addEventListener("click",authorprofile(obj["TPALV"][i].author));
                    span_author1.addEventListener('click', function () {
                        authorprofile(obj["TPALV"][i].author);
                    });
                    var t = document.createTextNode(obj["TPALV"][i+2].author);
                    span_author1.appendChild(t);
                    var span_likes = document.createElement('p');
                    var t = document.createTextNode("Likes: " + obj["TPALV"][i+2].likes);
                    span_likes.append(t);
                    var span_views = document.createElement('p');
                    var t = document.createTextNode("Views: " + obj["TPALV"][i+2].views);
                    span_views.append(t);
                    td2.appendChild(span_title);
                    td2.appendChild(span_author1);
                    td2.appendChild(span_likes);
                    td2.appendChild(span_views);
                    tr.appendChild(td1);
                    tr.appendChild(td2);
                }
            }
        }
    });

}