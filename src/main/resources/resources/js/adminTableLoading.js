function loadComics(){
    $.ajax({
        url: "admin.html/getComics",
        type: "post",
        async: false,
        success: function (data) {
            var tbody = document.getElementById("comicstb");
            var obj = jQuery.parseJSON(data);
            tbody.innerHTML = "";
            var tableRow = document.createElement("TR");
            tableRow.style.cursor = "default";
            tableRow.className = "d0";
            var titleTD = document.createElement('TD');
            titleTD.style = "width:22.5vw";
            titleTD.innerText = "Title";
            var AuthorTD = document.createElement('TD');
            AuthorTD.style = "width:22.5vw";
            AuthorTD.innerText = "Author";
            var TimeTD = document.createElement('TD');
            TimeTD.style = "width:22.5vw";
            TimeTD.innerText = "Time";
            var ActionTD = document.createElement('TD');
            ActionTD.style = "width:22.5vw";
            ActionTD.innerText = "Action";
            tableRow.appendChild(titleTD);
            tableRow.appendChild(AuthorTD);
            tableRow.appendChild(TimeTD);
            tableRow.appendChild(ActionTD);
            tbody.appendChild(tableRow);
            console.log(obj.COMICS);
            for (let i = 0; i < obj.COMICS.length; i = i + 1) {
                console.log("COMIC TYPE IS: " +obj.COMICS[i].ctype);
                var tableRow = document.createElement("TR");
                tableRow.className = "d0";
                var titleTD = document.createElement('TD');
                titleTD.innerText = obj.COMICS[i].title;
                var AuthorTD = document.createElement('TD');
                AuthorTD.innerText = obj.author[i];
                var TimeTD = document.createElement('TD');
                TimeTD.innerText = obj.COMICS[i].date_published;
                var ActionTD = document.createElement('TD');
                //ActionTD.style = "width:22.5vw";
                var anchor = document.createElement('a');
                anchor.style.cursor = "pointer";
                anchor.innerHTML = "View";
                anchor.addEventListener('click', function () {
                    if (obj.COMICS[i].ctype == 0){
                        $.cookie('ifSeries', false);
                        goViewComic(obj.COMICS[i].id);
                    }
                    else{
                        goViewGameComic(obj.COMICS[i].id);
                    }

                    //$.cookie('ifSeries', false);
                    //goViewComic(obj.COMICS[i].id);
                });
                var correct = document.createElement('input');
                correct.type = "image";
                correct.src = "./img/correct.png";
                correct.height = "35";
                correct.style = "margin-left:10px;vertical-align:inherit;cursor:pointer";
                correct.addEventListener('click', function () {
                    //goViewSeries(obj["TPALV"][i].seriesID);
                    passComicID(obj.COMICS[i].id);
                });
                var wrong = document.createElement('input');
                wrong.type = "image";
                wrong.src = "./img/delete.png";
                wrong.height = "35";
                wrong.style = "margin-left:10px;vertical-align:inherit;cursor:pointer";
                wrong.addEventListener('click', function () {
                    //goViewSeries(obj["TPALV"][i].seriesID);
                    denyComicID(obj.COMICS[i].id);
                });
                ActionTD.appendChild(anchor);
                ActionTD.appendChild(correct);
                ActionTD.appendChild(wrong);
                tableRow.appendChild(titleTD);
                tableRow.appendChild(AuthorTD);
                tableRow.appendChild(TimeTD);
                tableRow.appendChild(ActionTD);
                tbody.appendChild(tableRow);
            }
        }
    });
}
function passComicID(ID){
    $.ajax({
        url: "admin.html/passComic",
        type: "post",
        async: false,
        data: {comicTitle: ID},
        success: function (data) {

        }
    });
    document.location.reload();
}
function denyComicID(ID){
    $.ajax({
        url: "admin.html/denyComic",
        type: "post",
        async: false,
        data: {comicTitle: ID},
        success: function (data) {

        }
    });
    document.location.reload();
}

function loadComments(){
    $.ajax({
        url: "admin.html/getComments",
        type: "post",
        async: false,
        success: function (data) {
            var tbody = document.getElementById("commentstb");
            var obj = jQuery.parseJSON(data);
            tbody.innerHTML = "";
            var tableRow = document.createElement("TR");
            tableRow.style.cursor = "default";
            tableRow.className = "d0";
            var titleTD = document.createElement('TD');
            titleTD.style = "width:17.5vw";
            titleTD.innerText = "Comment";
            var AuthorTD = document.createElement('TD');
            AuthorTD.style = "width:17.5vw";
            AuthorTD.innerText = "User";
            var ComNumTD = document.createElement('TD');
            ComNumTD.style = "width:17.5vw";
            ComNumTD.innerText = "Comment Number";
            var TimeTD = document.createElement('TD');
            TimeTD.style = "width:17.5vw";
            TimeTD.innerText = "Time";
            var ActionTD = document.createElement('TD');
            ActionTD.style = "width:17.5vw";
            ActionTD.innerText = "Action";
            tableRow.appendChild(titleTD);
            tableRow.appendChild(AuthorTD);
            tableRow.appendChild(ComNumTD);
            tableRow.appendChild(TimeTD);
            tableRow.appendChild(ActionTD);
            tbody.appendChild(tableRow);
            for (let i = 0; i < obj.COMMENTS.length; i = i + 1) {
                var tableRow = document.createElement("TR");
                tableRow.className = "d0";

                var titleTD = document.createElement('TD');
                titleTD.innerText = obj.COMMENTS[i].comment;

                var AuthorTD = document.createElement('TD');
                AuthorTD.innerText = obj.COMMENTS[i].commenter_username;

                var CNTD = document.createElement('TD');
                CNTD.innerText = obj.COMMENTS[i].comment_number;

                var TimeTD = document.createElement('TD');
                TimeTD.innerText = obj.COMMENTS[i].comment_time;

                var ActionTD = document.createElement('TD');
                //ActionTD.style = "width:22.5vw";
                var anchor = document.createElement('a');
                anchor.innerHTML = "View";
                anchor.style.cursor = "pointer";
                anchor.addEventListener('click', function () {
                    $.cookie('ifSeries', false);
                    goViewComic(obj.COMMENTS[i].comic_id);
                });
                var correct = document.createElement('input');
                correct.type = "image";
                correct.src = "./img/correct.png";
                correct.height = "35";
                correct.style = "margin-left:10px;vertical-align:inherit;cursor:pointer";
                correct.addEventListener('click', function () {
                    //goViewSeries(obj["TPALV"][i].seriesID);
                    passComment(obj.COMMENTS[i].comic_id,obj.COMMENTS[i].comment_number);
                });
                var wrong = document.createElement('input');
                wrong.type = "image";
                wrong.src = "./img/delete.png";
                wrong.height = "35";
                wrong.style = "margin-left:10px;vertical-align:inherit;cursor:pointer";
                wrong.addEventListener('click', function () {
                    //goViewSeries(obj["TPALV"][i].seriesID);
                    denyComment(obj.COMMENTS[i].comic_id,obj.COMMENTS[i].comment_number);
                });
                ActionTD.appendChild(anchor);
                ActionTD.appendChild(correct);
                ActionTD.appendChild(wrong);
                tableRow.appendChild(titleTD);
                tableRow.appendChild(AuthorTD);
                tableRow.appendChild(CNTD);
                tableRow.appendChild(TimeTD);
                tableRow.appendChild(ActionTD);
                tbody.appendChild(tableRow);
            }
        }
    });
}

function passComment(comic_id,comic_number){
    $.ajax({
        url: "admin.html/passComment",
        type: "post",
        async: false,
        data: {comicID: comic_id,comicNum: comic_number},
        success: function (data) {

        }
    });
    document.location.reload();
    showComments();
}
function denyComment(comic_id,comic_number){
    $.ajax({
        url: "admin.html/denyComment",
        type: "post",
        async: false,
        data: {comicID:comic_id,comicNum: comic_number},
        success: function (data) {
            showComics();
            //showComments();
        }
    });
    document.location.reload();
    showComments();
}