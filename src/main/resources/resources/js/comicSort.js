function checksortby(){
    if(document.getElementById("series_regular").style.display == "block"){
        if(document.getElementById("selectregular").selected==true){
            document.getElementById("keywordsortby").style.display = "block";
        }else{
            document.getElementById("keywordsortby").style.display = "none";
        }
    }
}

function showtable(){
    var kw = $.cookie('search_word');
    if(kw!=""){
        document.getElementById("rst").innerText = "\"" + kw + "\"";
        // document.location.href="./searchResult.html";
    }
    var by = $.cookie('search_by');
    console.log(by);

    if(by=="author"){
        document.getElementById("authorresult").style.display = "block";
        document.getElementById("series_regular").style.display = "none";
        document.getElementById("keywordsortby").style.display = "none";
        document.getElementById("keywordresult").style.display = "none";
        showauthor();
    }
    if(by=="keyword" && document.getElementById('selectregular').selected == true) {
        document.getElementById("keywordsortby").style.display = "block";
        document.getElementById("series_regular").style.display = "block";
        // checksortby();
        document.getElementById("keywordresult").style.display = "block";
        document.getElementById("authorresult").style.display = "none";
        showcomic("regular");
    }
    if(by=="keyword" && document.getElementById('selectseries').selected == true){
        document.getElementById("keywordsortby").style.display = "block";
        document.getElementById("series_regular").style.display = "block";
        // checksortby();
        document.getElementById("keywordresult").style.display = "block";
        document.getElementById("authorresult").style.display = "none";
        checksortby();
        showcomic("series");
    }
    if(by=="keyword" && document.getElementById('selectgames').selected == true){
        document.getElementById("keywordsortby").style.display = "block";
        document.getElementById("series_regular").style.display = "block";
        // checksortby();
        document.getElementById("keywordresult").style.display = "block";
        document.getElementById("authorresult").style.display = "none";
        checksortby();
        showcomic("game");
    }
}


function showauthor(){
    $(window).ready(function() {
        authorPage(1);
    });
}

function authorPage(page){
    $.ajax({
        url: "searchResult.html/author",
        type: "post",
        async: false,
        data: {author: $.cookie('search_word'), page: page},
        success: function (data) {
            var tbody = document.getElementById("author_result_table");
            var obj = jQuery.parseJSON(data);
            $("#author_result_table tr").remove();
            if(obj.account.length==0){
                document.getElementById("authorEmpty").style.display = "block";
                document.getElementById("divauthorpage").style.display = "none";
            }else{
                document.getElementById("authorEmpty").style.display = "none";
                document.getElementById("divauthorpage").style.display = "block";
                for (let i = 0; i < obj.account.length; i+=3) {
                    // (function () {
                    var tr = document.createElement('TR');
                    tr.style.width = "100%";
                    tbody.appendChild(tr);

                    // i = 0, first one
                    var td1 = document.createElement('TD');
                    td1.style.width = "33.3%";
                    td1.style.paddingBottom = "20px";
                    tr.appendChild(td1);
                    var divBig1 = document.createElement('DIV');
                    divBig1.addEventListener('click', function() {
                        authorprofile(obj.account[i]);
                    });
                    divBig1.classList.add("profileCard");
                    divBig1.style.width = "93.93%";
                    divBig1.style.display = "flex";
                    td1.appendChild(divBig1);
                    // img photo here
                    var divImg1 = document.createElement('DIV');
                    divImg1.style.width = "38.2%";
                    divImg1.style.paddingTop = "38.2%";
                    divImg1.style.position = "relative";
                    var img1 = document.createElement('img');
                    img1.src = obj.picpath[i];
                    img1.style.width = "100%";
                    img1.style.height = "100%";
                    img1.style.position = "absolute";
                    img1.style.top = "0";
                    divImg1.appendChild(img1);
                    // other info here
                    var divinfo1 = document.createElement('div');
                    divinfo1.style.width = "61.8%";
                    divinfo1.style.marginLeft = "6.8%"
                    var span_author1 = document.createElement('span');
                    var t = document.createTextNode(obj.account[i]);
                    span_author1.appendChild(t);
                    var span_follower1 = document.createElement('span');
                    var t = document.createTextNode(obj.followers[i]);
                    span_follower1.appendChild(t);
                    divinfo1.appendChild(span_author1);
                    divinfo1.appendChild(document.createElement('br'));
                    divinfo1.appendChild(span_follower1);
                    divinfo1.appendChild(document.createElement('span').appendChild(document.createTextNode(" follower")));
                    // add to divBig1
                    divBig1.appendChild(divImg1);
                    divBig1.appendChild(divinfo1);

                    // i = 1, second one
                    var td2 = document.createElement('TD');
                    td2.style.width = "33.3%";
                    td2.style.paddingBottom = "20px";
                    tr.appendChild(td2);
                    if(i+1<obj.account.length){
                        var divBig2 = document.createElement('DIV');
                        divBig2.addEventListener('click', function(){
                            authorprofile(obj.account[i+1]);
                        });
                        divBig2.classList.add("profileCard");
                        divBig2.style.width = "93.93%";
                        divBig2.style.display = "flex";
                        divBig2.style.marginLeft = "3.03%";
                        td2.appendChild(divBig2);
                        // img photo here
                        var divImg2 = document.createElement('DIV');
                        divImg2.style.width = "38.2%";
                        divImg2.style.paddingTop = "38.2%";
                        divImg2.style.position = "relative";
                        var img2 = document.createElement('img');
                        img2.src = obj.picpath[i+1];
                        console.log("img src = " + img2.src);
                        img2.style.width = "100%";
                        img2.style.height = "100%";
                        img2.style.position = "absolute";
                        img2.style.top = "0";
                        divImg2.appendChild(img2);
                        // other info here
                        var divinfo2 = document.createElement('div');
                        divinfo2.style.width = "61.8%";
                        divinfo2.style.marginLeft = "6.8%"
                        var span_author2 = document.createElement('span');
                        var t = document.createTextNode(obj.account[i+1]);
                        span_author2.appendChild(t);
                        var span_follower2 = document.createElement('span');
                        var t = document.createTextNode(obj.followers[i+1]);
                        span_follower2.appendChild(t);
                        divinfo2.appendChild(span_author2);
                        divinfo2.appendChild(document.createElement('br'));
                        divinfo2.appendChild(span_follower2);
                        divinfo2.appendChild(document.createElement('span').appendChild(document.createTextNode(" follower")));
                        // add to divBig2
                        divBig2.appendChild(divImg2);
                        divBig2.appendChild(divinfo2);
                    }

                    // i = 2, first one
                    var td3 = document.createElement('TD');
                    td3.style.width = "33.3%";
                    td3.style.paddingBottom = "20px";
                    tr.appendChild(td3);

                    if(i+2<obj.account.length){
                        var divBig3 = document.createElement('DIV');
                        divBig3.addEventListener('click', function(){
                            authorprofile(obj.account[i+2]);
                        });
                        divBig3.classList.add("profileCard");
                        divBig3.style.width = "93.93%";
                        divBig3.style.display = "flex";
                        divBig3.style.cssFloat = "right";
                        td3.appendChild(divBig3);
                        // img photo here
                        var divImg3 = document.createElement('DIV');
                        divImg3.style.width = "38.2%";
                        divImg3.style.paddingTop = "38.2%";
                        divImg3.style.position = "relative";
                        var img3 = document.createElement('img');
                        img3.src = obj.picpath[i+2];
                        console.log("img src = " + img3.src);
                        img3.style.width = "100%";
                        img3.style.height = "100%";
                        img3.style.position = "absolute";
                        img3.style.top = "0";
                        divImg3.appendChild(img3);
                        // other info here
                        var divinfo3 = document.createElement('div');
                        divinfo3.style.width = "61.8%";
                        divinfo3.style.marginLeft = "6.8%"
                        var span_author3 = document.createElement('span');
                        var t = document.createTextNode(obj.account[i+2]);
                        span_author3.appendChild(t);
                        var span_follower3 = document.createElement('span');
                        var t = document.createTextNode(obj.followers[i+2]);
                        span_follower3.appendChild(t);
                        divinfo3.appendChild(span_author3);
                        divinfo3.appendChild(document.createElement('br'));
                        divinfo3.appendChild(span_follower3);
                        divinfo3.appendChild(document.createElement('span').appendChild(document.createTextNode(" follower")));
                        // add to divBig3
                        divBig3.appendChild(divImg3);
                        divBig3.appendChild(divinfo3);
                    }
                }
                $("#authorpageturn li").remove();
                var pageturntable = document.getElementById("authorpageturn");
                for(let k=1; k<=obj.totalpage; k++){
                    if(k==1){
                        var prev = document.createElement('li');
                        prev.appendChild(document.createTextNode("<"));
                        
                        pageturntable.appendChild(prev);
                        if(k==obj.pagenumber){
                            prev.disable = true;
                            prev.style.cursor = "not-allowed";
                        }else{
                            prev.disable = false;
                            prev.style.cursor = "pointer";
                            prev.addEventListener('click', function() {
                                authorPage(obj.pagenumber-1);
                            });
                        }
                    }
                    if(k==obj.pagenumber){
                        // var prev = document.createElement('li');
                        // prev.disable = true;
                        // prev.appendChild(document.createTextNode("&laquo;"));
                        // pageturntable.appendChild(prev);
                        var select = document.createElement('li');
                        select.classList = "active";
                        select.appendChild(document.createTextNode(k));
                        select.addEventListener('click', function() {
                            authorPage(k);
                        });
                        select.style.cursor = "pointer";
                        pageturntable.appendChild(select);
                    }else{
                        var unselect = document.createElement('li');
                        unselect.appendChild(document.createTextNode(k));
                        unselect.addEventListener('click', function() {
                            authorPage(k);
                        });
                        unselect.style.cursor = "pointer";
                        pageturntable.appendChild(unselect);
                    }
                    if(k==obj.totalpage){
                        var next = document.createElement('li');
                        next.appendChild(document.createTextNode(">"));
                        pageturntable.appendChild(next);
                        if(k==obj.pagenumber){
                            next.disable = true;
                            next.style.cursor = "not-allowed";
                        }else{
                            next.style.cursor = "pointer";
                            next.disable = false;
                            next.addEventListener('click', function() {
                                authorPage(obj.pagenumber+1);
                            });
                        }
                    }
                }
            }
        }
    });
}

function showcomic(type){
    if(type=="regular"){
        $(window).ready(function() {
            // regualr, sort by, page
            comicPage(1);
        });
    }else if(type=="series"){
        console.log("corret position");
        $(window).ready(function() {
            // series, page
            seriesPage(1);
        });
    }else if(type=="game"){
        console.log("game position");
        $(window).ready(function() {
            // game, page
            gamePage(1);
        });
    }
}

function comicPage(page){
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
    console.log("option:"+option);
    console.log("choice:"+choice);
    $.ajax({
        url: "searchResult.html/keyword",
        type: "post",
        async: false,
        data: {keyword: $.cookie('search_word'),sorted: choice,page:page},
        success: function (data) {
            console.log(data);
            var obj = jQuery.parseJSON(data);
            var tbody = document.getElementById("search_result_table");
            $("#search_result_table tr").remove();
            tbody.innerHTML = "";
            if (obj.TPALV.length == 0) {
                document.getElementById("authorEmpty").style.display = "block";
                document.getElementById("divsearchpage").style.display = "none";
            } else {
                document.getElementById("authorEmpty").style.display = "none";
                document.getElementById("divsearchpage").style.display = "block";
                for (let i = 0; i < obj.TPALV.length; i += 3) {
                    var tr = document.createElement('TR');
                    tbody.appendChild(tr);
                    // i = 0, first one
                    var td1 = document.createElement('TD');
                    var img1 = document.createElement('img');
                    img1.style.width = "17.6vw";
                    img1.style.height = "9.9vw";                 
                    img1.style.cursor = "pointer";
                    img1.src = obj["TPALV"][i].picPath;
                    td1.appendChild(img1);
                    img1.addEventListener('click', function () {
                        $.cookie("ifSeries",obj.isSeries[i]);
                        console.log($.cookie('ifSeries'));
                        goViewComic(obj["TPALV"][i].comicID);
                    });
                    var td2 = document.createElement('TD');
                    td2.style = "padding-right: 20px; padding-bottom: 40px; width:12vw; word-wrap: break-word; height: 9.9vw;";
                    var span_title = document.createElement('p');
                    if(obj["TPALV"][i].title.length > 16) {
                        obj["TPALV"][i].title = obj["TPALV"][i].title.substring(0,15)+"...";
                    }
                    var t = document.createTextNode(obj["TPALV"][i].title);
                    span_title.appendChild(t);
                    span_title.addEventListener('click', function () {
                        $.cookie("ifSeries",obj.isSeries[i]);
                        console.log($.cookie('ifSeries'));
                        goViewComic(obj["TPALV"][i].comicID);
                    });
                    span_title.style.width = "11vw";
                    span_title.style.cursor = "pointer";
                    var span_author1 = document.createElement('span');
                    span_author1.style.cursor = "pointer";
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
                    // i = 1, second one
                    var td2 = document.createElement('TD');
                    tr.appendChild(td2);
                    if (i + 1 < obj.TPALV.length) {
                        var td1 = document.createElement('TD');
                        var img1 = document.createElement('img');
                        img1.style.width = "17.6vw";
                        img1.style.height = "9.9vw";                 
                        img1.style.cursor = "pointer";
                        img1.src = obj["TPALV"][i+1].picPath;
                        td1.appendChild(img1);
                        img1.addEventListener('click', function () {
                            $.cookie("ifSeries",obj.isSeries[i+1]);
                            console.log($.cookie('ifSeries'));
                            goViewComic(obj["TPALV"][i+1].comicID);
                        });
                        var td2 = document.createElement('TD');
                        td2.style = "padding-right: 20px; padding-bottom: 40px; width:12vw; word-wrap: break-word; height: 9.9vw;";
                        var span_title = document.createElement('p');
                        if(obj["TPALV"][i+1].title.length > 16) {
                            obj["TPALV"][i+1].title = obj["TPALV"][i+1].title.substring(0,15)+"...";
                        }
                        var t = document.createTextNode(obj["TPALV"][i+1].title);
                        span_title.appendChild(t);
                        span_title.addEventListener('click', function () {
                            $.cookie("ifSeries",obj.isSeries[i+1]);
                            console.log($.cookie('ifSeries'));
                            goViewComic(obj["TPALV"][i+1].comicID);
                        });
                        span_title.style.width = "11vw";
                        span_title.style.cursor = "pointer";
                        var span_author1 = document.createElement('span');
                        span_author1.style.cursor = "pointer";
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
                        img1.style.cursor = "pointer";
                        img1.src = obj["TPALV"][i+2].picPath;
                        td1.appendChild(img1);
                        img1.addEventListener('click', function () {
                            $.cookie("ifSeries",obj.isSeries[i+2]);
                            console.log($.cookie('ifSeries'));
                            goViewComic(obj["TPALV"][i+2].comicID);
                        });
                        var td2 = document.createElement('TD');
                        td2.style = "padding-right: 20px; padding-bottom: 40px; width:11vw; word-wrap: break-word; height: 9.9vw;";
                        var span_title = document.createElement('p');
                        if(obj["TPALV"][i+2].title.length > 16) {
                            obj["TPALV"][i+2].title = obj["TPALV"][i+2].title.substring(0,15)+"...";
                        }
                        var t = document.createTextNode(obj["TPALV"][i+2].title);
                        span_title.appendChild(t);
                        span_title.addEventListener('click', function () {
                            $.cookie("ifSeries",obj.isSeries[i+2]);
                            console.log($.cookie('ifSeries'));
                            goViewComic(obj["TPALV"][i+2].comicID);
                        });
                        span_title.style.width = "11vw";
                        span_title.style.cursor = "pointer";
                        var span_author1 = document.createElement('span');
                        span_author1.style.cursor = "pointer";
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
                $("#searchpageturn li").remove();
                var pageturntable = document.getElementById("searchpageturn");
                for(let k=1; k<=obj.totalpage; k++){
                    if(k==1){
                        var prev = document.createElement('li');
                        prev.appendChild(document.createTextNode("<"));
                        
                        pageturntable.appendChild(prev);
                        if(k==obj.pagenumber){
                            prev.disable = true;
                            prev.style.cursor = "not-allowed";
                        }else{
                            prev.disable = false;
                            prev.style.cursor = "pointer";
                            prev.addEventListener('click', function() {
                                comicPage(obj.pagenumber-1);
                            });
                        }
                    }
                    if(k==obj.pagenumber){
                        var select = document.createElement('li');
                        select.classList = "active";
                        select.appendChild(document.createTextNode(k));
                        select.addEventListener('click', function() {
                            comicPage(k);
                        });
                        select.style.cursor = "pointer";
                        pageturntable.appendChild(select);
                    }else{
                        var unselect = document.createElement('li');
                        unselect.appendChild(document.createTextNode(k));
                        unselect.addEventListener('click', function() {
                            comicPage(k);
                        });
                        unselect.style.cursor = "pointer";
                        pageturntable.appendChild(unselect);
                    }
                    if(k==obj.totalpage){
                        var next = document.createElement('li');
                        next.appendChild(document.createTextNode(">"));
                        pageturntable.appendChild(next);
                        if(k==obj.pagenumber){
                            next.disable = true;
                            next.style.cursor = "not-allowed";
                        }else{
                            next.style.cursor = "pointer";
                            next.disable = false;
                            next.addEventListener('click', function() {
                                comicPage(obj.pagenumber+1);
                            });
                        }
                    }
                }
            }
            
        }
    });
}

function seriesPage(page){
    $.ajax({
        url: "searchResult.html/seriesSearch",
        type: "post",
        async: false,
        data: {keyword: $.cookie('search_word'), page: page},
        success: function (data) {
            console.log(data);
            var obj = jQuery.parseJSON(data);
            console.log("whaaaaat1");
            var tbody = document.getElementById("search_result_table");
            console.log("whaaaaatqqq");
            $("#search_result_table tr").remove();
            console.log("whaaaaat");
            tbody.innerHTML = "";
            if (obj.TPALV.length == 0) {
                console.log("?????");
                document.getElementById("authorEmpty").style.display = "block";
                document.getElementById("divsearchpage").style.display = "none";
            } else {
                console.log("!!!!!!!!");
                document.getElementById("authorEmpty").style.display = "none";
                document.getElementById("divsearchpage").style.display = "block";
                for (let i = 0; i < obj.TPALV.length; i += 3) {
                    var tr = document.createElement('TR');
                    tbody.appendChild(tr);
    
                    // i = 0, first one
                    var td1 = document.createElement('TD');
                    var img1 = document.createElement('img');
                    img1.style.width = "17.6vw";
                    img1.style.height = "9.9vw";
                    img1.style.cursor = "pointer";
                    img1.src = obj["TPALV"][i].picPath;
                    td1.appendChild(img1);
                    img1.addEventListener('click', function () {
                        goViewSeries(obj["TPALV"][i].seriesID);
                    });
                    var td2 = document.createElement('TD');
                    td2.style = "padding-right: 20px; padding-bottom: 40px; width:12vw; word-wrap: break-word; height: 9.9vw;";
                    var span_title = document.createElement('p');
                    if(obj["TPALV"][i].title.length > 16) {
                        obj["TPALV"][i].title = obj["TPALV"][i].title.substring(0,15)+"...";
                    }
                    var t = document.createTextNode(obj["TPALV"][i].title);
                    span_title.appendChild(t);
                    span_title.addEventListener('click', function () {
                        goViewSeries(obj["TPALV"][i].seriesID);
                    });
                    span_title.style.width = "11vw";
                    span_title.style.cursor = "pointer";
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
    
                    // i = 1, second one
                    var td2 = document.createElement('TD');
                    tr.appendChild(td2);
                    if (i + 1 < obj.TPALV.length) {
                        var td1 = document.createElement('TD');
                        var img1 = document.createElement('img');
                        img1.style.width = "17.6vw";
                        img1.style.height = "9.9vw";                 
                        img1.style.cursor = "pointer";
                        img1.src = obj["TPALV"][i + 1].picPath;
                        td1.appendChild(img1);
                        img1.addEventListener('click', function () {
                            goViewSeries(obj["TPALV"][i + 1].seriesID);
                        });
                        var td2 = document.createElement('TD');
                        td2.style = "padding-right: 20px; padding-bottom: 40px; width:12vw; word-wrap: break-word; height: 9.9vw;";
                        var span_title = document.createElement('p');
                        if(obj["TPALV"][i+1].title.length > 16) {
                            obj["TPALV"][i+1].title = obj["TPALV"][i+1].title.substring(0,15)+"...";
                        }
                        var t = document.createTextNode(obj["TPALV"][i + 1].title);
                        span_title.appendChild(t);
                        span_title.addEventListener('click', function () {
                            goViewSeries(obj["TPALV"][i + 1].seriesID);
                        });
                        span_title.style.width = "11vw";
                        span_title.style.cursor = "pointer";

                        var span_author = document.createElement('p');
                        var t = document.createTextNode(obj["TPALV"][i+1].author);
                        span_author.appendChild(t);
                        span_author.addEventListener('click', function () {
                            authorprofile(obj["TPALV"][i+1].author);
                        });
                        td2.appendChild(span_title);
                        td2.appendChild(span_author);
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
                        img1.style.cursor = "pointer";
                        img1.src = obj["TPALV"][i + 2].picPath;
                        td1.appendChild(img1);
                        img1.addEventListener('click', function () {
                            goViewSeries(obj["TPALV"][i + 2].seriesID);
                        });
                        var td2 = document.createElement('TD');
                        td2.style = "padding-right: 20px; padding-bottom: 40px; width:11vw; word-wrap: break-word; height: 9.9vw;";
                        var span_title = document.createElement('p');
                        if(obj["TPALV"][i+2].title.length > 16) {
                            obj["TPALV"][i+2].title = obj["TPALV"][i+2].title.substring(0,15)+"...";
                        }
                        var t = document.createTextNode(obj["TPALV"][i + 2].title);
                        span_title.appendChild(t);
                        span_title.addEventListener('click', function () {
                            goViewSeries(obj["TPALV"][i + 2].seriesID);
                        });

                        span_title.style.width = "11vw";
                        span_title.style.cursor = "pointer";
                        var span_author = document.createElement('p');
                        var t = document.createTextNode(obj["TPALV"][i+2].author);
                        span_author.appendChild(t);
                        span_author.addEventListener('click', function () {
                            authorprofile(obj["TPALV"][i+2].author);
                        });
                        td2.appendChild(span_title);
                        td2.appendChild(span_author);
                        tr.appendChild(td1);
                        tr.appendChild(td2);
                    }
                }
                $("#searchpageturn li").remove();
                var pageturntable = document.getElementById("searchpageturn");
                for(let k=1; k<=obj.totalpage; k++){
                    if(k==1){
                        var prev = document.createElement('li');
                        prev.appendChild(document.createTextNode("<"));
                        
                        pageturntable.appendChild(prev);
                        if(k==obj.pagenumber){
                            prev.disable = true;
                            prev.style.cursor = "not-allowed";
                        }else{
                            prev.disable = false;
                            prev.style.cursor = "pointer";
                            prev.addEventListener('click', function() {
                                seriesPage(obj.pagenumber-1);
                            });
                        }
                    }
                    if(k==obj.pagenumber){
                        var select = document.createElement('li');
                        select.classList = "active";
                        select.appendChild(document.createTextNode(k));
                        select.addEventListener('click', function() {
                            seriesPage(k);
                        });
                        select.style.cursor = "pointer";
                        pageturntable.appendChild(select);
                    }else{
                        var unselect = document.createElement('li');
                        unselect.appendChild(document.createTextNode(k));
                        unselect.addEventListener('click', function() {
                            seriesPage(k);
                        });
                        unselect.style.cursor = "pointer";
                        pageturntable.appendChild(unselect);
                    }
                    if(k==obj.totalpage){
                        var next = document.createElement('li');
                        next.appendChild(document.createTextNode(">"));
                        pageturntable.appendChild(next);
                        if(k==obj.pagenumber){
                            next.disable = true;
                            next.style.cursor = "not-allowed";
                        }else{
                            next.style.cursor = "pointer";
                            next.disable = false;
                            next.addEventListener('click', function() {
                                seriesPage(obj.pagenumber+1);
                            });
                        }
                    }
                }
            }
            
        }
    });
}

function gamePage(page){
    $.ajax({
        url: "searchResult.html/game",
        type: "post",
        async: false,
        data: {keyword: $.cookie('search_word'),page:page},
        success: function (data) {
            console.log(data);
            var obj = jQuery.parseJSON(data);
            var tbody = document.getElementById("search_result_table");
            $("#search_result_table tr").remove();
            tbody.innerHTML = "";
            if (obj.titles.length == 0) {
                document.getElementById("authorEmpty").style.display = "block";
                document.getElementById("divsearchpage").style.display = "none";
            } else {
                document.getElementById("authorEmpty").style.display = "none";
                document.getElementById("divsearchpage").style.display = "block";
                for (let i = 0; i < obj.titles.length; i += 3) {
                    var tr = document.createElement('TR');
                    tbody.appendChild(tr);
    
                    // i = 0, first one
                    var td1 = document.createElement('TD');
                    var img1 = document.createElement('img');
                    img1.style.width = "17.6vw";
                    img1.style.height = "9.9vw";                 
                    img1.style.cursor = "pointer";
                    img1.src = obj.path[i];
                    td1.appendChild(img1);
                    img1.addEventListener('click', function () {
                        comicGameRedirect(obj.IDs[i]);
                    });
                    var td2 = document.createElement('TD');
                    td2.style = "padding-right: 20px; padding-bottom: 40px; width:12vw; word-wrap: break-word; height: 9.9vw;";
                    var span_title = document.createElement('p');
                    if(obj.titles[i].length > 16) {
                        obj.titles[i] = obj.titles[i].substring(0,15)+"...";
                    }
                    var t = document.createTextNode(obj.titles[i]);
                    span_title.appendChild(t);
                    td2.addEventListener('click', function () {
                        comicGameRedirect(obj.IDs[i]);
                    });
                    span_title.style.width = "11vw";
                    span_title.style.cursor = "pointer";
                    var span_author1 = document.createElement('span');
                    span_author1.style.cursor = "pointer";
                    var t = document.createTextNode("keyword: " + obj.keywords[i]);
                    span_author1.appendChild(t);
                    td2.appendChild(span_title);
                    td2.appendChild(span_author1);
                    tr.appendChild(td1);
                    tr.appendChild(td2);
                    // i = 1, second one
                    var td2 = document.createElement('TD');
                    tr.appendChild(td2);
                    if (i + 1 < obj.titles.length) {
                        var td1 = document.createElement('TD');
                        var img1 = document.createElement('img');
                        img1.style.width = "17.6vw";
                        img1.style.height = "9.9vw";                 
                        img1.style.cursor = "pointer";
                        img1.src = obj.path[i+1];
                        td1.appendChild(img1);
                        img1.addEventListener('click', function () {
                            comicGameRedirect(obj.IDs[i+1]);
                        });
                        var td2 = document.createElement('TD');
                        td2.style = "padding-right: 20px; padding-bottom: 40px; width:12vw; word-wrap: break-word; height: 9.9vw;";
                        var span_title = document.createElement('p');
                        if(obj.titles[i+1].length > 16) {
                            obj.titles[i+1] = obj.titles[i+1].substring(0,15)+"...";
                        }
                        var t = document.createTextNode(obj.titles[i+1]);
                        span_title.appendChild(t);
                        td2.addEventListener('click', function () {
                            comicGameRedirect(obj.IDs[i+1]);
                        });
                        span_title.style.width = "11vw";
                        span_title.style.cursor = "pointer";
                        var span_author1 = document.createElement('span');
                        span_author1.style.cursor = "pointer";
                        var t = document.createTextNode("keyword: " + obj.keywords[i+1]);
                        span_author1.appendChild(t);
                        td2.appendChild(span_title);
                        td2.appendChild(span_author1);
                        tr.appendChild(td1);
                        tr.appendChild(td2);
                    }
    
                    // i = 2, first one
                    var td3 = document.createElement('TD');
                    tr.appendChild(td3);
    
                    if (i + 2 < obj.titles.length) {
                        var td1 = document.createElement('TD');
                        var img1 = document.createElement('img');
                        img1.style.width = "17.6vw";
                        img1.style.height = "9.9vw";                 
                        img1.style.cursor = "pointer";
                        img1.src = obj.path[i+2];
                        td1.appendChild(img1);
                        img1.addEventListener('click', function () {
                            comicGameRedirect(obj.IDs[i+2]);
                        });
                        var td2 = document.createElement('TD');
                        td2.style = "padding-right: 20px; padding-bottom: 40px; width:11vw; word-wrap: break-word; height: 9.9vw;";
                        var span_title = document.createElement('p');
                        if(obj.titles[i+2].length > 16) {
                            obj.titles[i+2] = obj.titles[i+2].substring(0,15)+"...";
                        }
                        var t = document.createTextNode(obj.titles[i+2]);
                        span_title.appendChild(t);
                        td2.addEventListener('click', function () {
                            comicGameRedirect(obj.IDs[i+2]);
                        });
                        span_title.style.width = "11vw";
                        span_title.style.cursor = "pointer";
                        var span_author1 = document.createElement('span');
                        span_author1.style.cursor = "pointer";
                        var t = document.createTextNode("keyword: " + obj.keywords[i+2]);
                        span_author1.appendChild(t);
                        td2.appendChild(span_title);
                        td2.appendChild(span_author1);
                        tr.appendChild(td1);
                        tr.appendChild(td2);
                    }
                }
                $("#searchpageturn li").remove();
                var pageturntable = document.getElementById("searchpageturn");
                for(let k=1; k<=obj.totalpage; k++){
                    if(k==1){
                        var prev = document.createElement('li');
                        prev.appendChild(document.createTextNode("<"));
                        
                        pageturntable.appendChild(prev);
                        if(k==obj.pagenumber){
                            prev.disable = true;
                            prev.style.cursor = "not-allowed";
                        }else{
                            prev.disable = false;
                            prev.style.cursor = "pointer";
                            prev.addEventListener('click', function() {
                                comicPage(obj.pagenumber-1);
                            });
                        }
                    }
                    if(k==obj.pagenumber){
                        var select = document.createElement('li');
                        select.classList = "active";
                        select.appendChild(document.createTextNode(k));
                        select.addEventListener('click', function() {
                            comicPage(k);
                        });
                        select.style.cursor = "pointer";
                        pageturntable.appendChild(select);
                    }else{
                        var unselect = document.createElement('li');
                        unselect.appendChild(document.createTextNode(k));
                        unselect.addEventListener('click', function() {
                            comicPage(k);
                        });
                        unselect.style.cursor = "pointer";
                        pageturntable.appendChild(unselect);
                    }
                    if(k==obj.totalpage){
                        var next = document.createElement('li');
                        next.appendChild(document.createTextNode(">"));
                        pageturntable.appendChild(next);
                        if(k==obj.pagenumber){
                            next.disable = true;
                            next.style.cursor = "not-allowed";
                        }else{
                            next.style.cursor = "pointer";
                            next.disable = false;
                            next.addEventListener('click', function() {
                                comicPage(obj.pagenumber+1);
                            });
                        }
                    }
                }
            }
            
        }
    });
}

function comicGameRedirect(id){
    $.cookie("comicId",id);
    document.location.href = "./viewGameComics.html";
}