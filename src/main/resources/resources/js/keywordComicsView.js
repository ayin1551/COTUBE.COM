var x = 0;
function loadMore(){
    loadGameComics(x);
    x = x + 1;
}
function loadGameComics(x){
    var appendDiv = document.getElementById("appendDiv");
    $.ajax({
        type: "post",
        url: "viewGameComicsByKeyword.html/getGameComics",
        async: false,
        success: function(data){

            var obj = jQuery.parseJSON(data);
            if (x == obj.COMICS.length){
                alert("No more comics to load");
                return;
            }
            alert(obj.COMICS[x].game_comic_id);
            var keyword = obj.COMICS[x].keyword;
            var src1 = obj.Details[x].panel1Src;
            var title1 = obj.Details[x].panel1Title;
            var src2 = obj.Details[x].panel2Src;
            var title2 = obj.Details[x].panel2Title;
            var src3 = obj.Details[x].panel3Src;
            var title3 = obj.Details[x].panel3Title;
            var src4 = obj.Details[x].panel4Src;
            var title4 = obj.Details[x].panel4Title;
            var allTitle = title1 + title2 + title3;
            if (src4 == undefined){
                src4 = "";
            }
            if (title4 == undefined){
                title4 = "";
            }
            else{
                allTitle = allTitle + title4;
            }

            var keywordDiv = document.createElement('div');
            console.log(keywordDiv);
            keywordDiv.className = "keyworddiv";

            var p = document.createElement('P');
            p.id = "kwd" + (x+1);
            p.style = "cursor:default";
            p.innerHTML = keyword;

            var table = document.createElement('table');
            table.className = "keywordTab";
            var tbody = document.createElement('tbody');
            tbody.id = "keywordBody";

            var td = document.createElement('td');
            var th = document.createElement('th');
            var imgsrc1 = document.createElement('img');
            imgsrc1.src = src1;
            imgsrc1.style.width = "260px";
            imgsrc1.style.height = "190px";
            imgsrc1.style.marginRight = "30px";
            table.setAttribute('class','style-1');
            var h31 = document.createElement('h3');
            h31.innerHTML = allTitle;
            th.appendChild(imgsrc1);
            th.appendChild(h31);
            td.appendChild(th);
            td.addEventListener('click', function () {
                $.cookie("comicId",obj.COMICS[x].game_comic_id);
                goToComics();
            });
            tbody.appendChild(td);


            keywordDiv.appendChild(p);
            keywordDiv.appendChild(table);
            keywordDiv.appendChild(tbody);

            appendDiv.appendChild(keywordDiv);
        }

    });
}
function goToComics(){
    document.location.href = "viewGameComics.html";
}