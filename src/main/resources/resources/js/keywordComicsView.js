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
            var keyword = obj.COMICS[x].keyword;
            var src1 = obj.Details[x].panel1Src;
            var title1 = obj.Details[x].panel1Title;
            var src2 = obj.Details[x].panel2Src;
            var title2 = obj.Details[x].panel2Title;
            var src3 = obj.Details[x].panel3Src;
            var title3 = obj.Details[x].panel3Title;
            var src4 = obj.Details[x].panel4Src;
            var title4 = obj.Details[x].panel4Title;
                if (src4 == undefined){
                    src4 = "";
                }
                if (title4 == undefined){
                    title4 = "";
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
                var h31 = document.createElement('h3');
                h31.innerHTML = title1;
                th.appendChild(imgsrc1);
                th.appendChild(h31);
                td.appendChild(th);
                tbody.appendChild(td);

                var td2 = document.createElement('td');
                var th2 = document.createElement('th');
                var imgsrc2 = document.createElement('img');
                imgsrc2.src = src2;
                var h32 = document.createElement('h3');
                h32.innerHTML = title2;
                th2.appendChild(imgsrc2);
                th2.appendChild(h32);
                td2.appendChild(th2);
                tbody.appendChild(td2);

                var td3 = document.createElement('td');
                var th3 = document.createElement('th');
                var imgsrc3 = document.createElement('img');
                imgsrc3.src = src3;
                var h33 = document.createElement('h3');
                h33.innerHTML = title3;
                th3.appendChild(imgsrc3);
                th3.appendChild(h33);
                td3.appendChild(th3);
                tbody.appendChild(td3);

                var td4 = document.createElement('td');
                var th4 = document.createElement('th');
                var imgsrc4 = document.createElement('img');
                imgsrc4.src = src4;
                var h34 = document.createElement('h3');
                h34.innerHTML = title4;
                th4.appendChild(imgsrc4);
                th4.appendChild(h34);
                td4.appendChild(th4);
                tbody.appendChild(td4);

                keywordDiv.appendChild(p);
                keywordDiv.appendChild(table);
                keywordDiv.appendChild(tbody);

                appendDiv.appendChild(keywordDiv);
            }

    });
}
