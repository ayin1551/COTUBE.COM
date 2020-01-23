function loadMore(){
    document.location.reload();

}
function loadGameComics(){
    var appendDiv = document.getElementById("appendDiv");
    $.ajax({
        type: "post",
        url: "viewGameComicsByKeyword.html/getGameComics",
        async: false,
        success: function(data) {
            console.log(data);
            var obj = jQuery.parseJSON(data);

            var Keyword1 = obj.KW[0].keyword;
            var Keyword2 = obj.KW[1].keyword;
            var Keyword3 = obj.KW[2].keyword;
            var Keyword4 = obj.KW[3].keyword;
            var Keyword5 = obj.KW[4].keyword;

            var keywordDiv1 = document.createElement('div');
            keywordDiv1.className = "keyworddiv";
            var p = document.createElement('P');
            p.id = "kwd";
            p.style = "cursor:default";
            p.innerHTML = Keyword1;
            keywordDiv1.appendChild(p);
            appendDiv.appendChild(keywordDiv1);
            for (let i = 0; i < obj.D1.length; i = i + 1) {
                var src1 = obj.D1[i].panel1Src;
                var title1 = obj.D1[i].panel1Title;
                var title2 = obj.D1[i].panel2Title;
                var title3 = obj.D1[i].panel3Title;
                var title4 = obj.D1[i].panel4Title;
                var comic_id = obj.D1[i].comic_id;
                var allTitle = title1 + " " + title2 + " " + title3;
                if (title4 == undefined) {
                    title4 = "";
                } else {
                    allTitle = allTitle + " " + title4;
                }


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
                table.setAttribute('class', 'style-1');
                var h31 = document.createElement('h3');
                if(allTitle.length > 22) {
                    allTitle = allTitle.substring(0,21)+"...";
                }
                h31.innerHTML = allTitle;
                th.appendChild(imgsrc1);
                th.appendChild(h31);
                td.appendChild(th);
                td.addEventListener('click', function () {
                    $.cookie("comicId", comic_id);
                    goToComics();
                });
                tbody.appendChild(td);
                keywordDiv1.appendChild(table);
                keywordDiv1.appendChild(tbody);
            }

            var keywordDiv2 = document.createElement('div');
            keywordDiv2.className = "keyworddiv";
            var p = document.createElement('P');
            p.id = "kwd";
            p.style = "cursor:default";x
            p.innerHTML = Keyword2;
            keywordDiv2.appendChild(p);
            appendDiv.appendChild(keywordDiv2);
            for (let i = 0; i < obj.D2.length; i = i + 1) {
                var src1 = obj.D2[i].panel1Src;
                var title1 = obj.D2[i].panel1Title;
                var title2 = obj.D2[i].panel2Title;
                var title3 = obj.D2[i].panel3Title;
                var title4 = obj.D2[i].panel4Title;
                var comic_id = obj.D2[i].comic_id;
                var allTitle = title1 + " " + title2 + " " + title3;
                if (title4 == undefined) {
                    title4 = "";
                } else {
                    allTitle = allTitle + " " + title4;
                }


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
                table.setAttribute('class', 'style-1');
                var h31 = document.createElement('h3');
                if(allTitle.length > 22) {
                    allTitle = allTitle.substring(0,21)+"...";
                }
                h31.innerHTML = allTitle;
                th.appendChild(imgsrc1);
                th.appendChild(h31);
                td.appendChild(th);
                td.addEventListener('click', function () {
                    $.cookie("comicId", comic_id);
                    goToComics();
                });
                tbody.appendChild(td);

                keywordDiv2.appendChild(table);
                keywordDiv2.appendChild(tbody);
            }
            var keywordDiv3 = document.createElement('div');
            keywordDiv3.className = "keyworddiv";
            var p = document.createElement('P');
            p.id = "kwd";
            p.style = "cursor:default";
            p.innerHTML = Keyword3;
            keywordDiv3.appendChild(p);
            appendDiv.appendChild(keywordDiv3);
            for (let i = 0; i < obj.D3.length; i = i + 1) {
                var src1 = obj.D3[i].panel1Src;
                var title1 = obj.D3[i].panel1Title;
                var title2 = obj.D3[i].panel2Title;
                var title3 = obj.D3[i].panel3Title;
                var title4 = obj.D3[i].panel4Title;
                var comic_id = obj.D3[i].comic_id;
                var allTitle = title1 + " " + title2 + " " + title3;
                if (title4 == undefined) {
                    title4 = "";
                } else {
                    allTitle = allTitle + " " + title4;
                }


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
                table.setAttribute('class', 'style-1');
                var h31 = document.createElement('h3');
                if(allTitle.length > 22) {
                    allTitle = allTitle.substring(0,21)+"...";
                }
                h31.innerHTML = allTitle;
                th.appendChild(imgsrc1);
                th.appendChild(h31);
                td.appendChild(th);
                td.addEventListener('click', function () {
                    $.cookie("comicId", comic_id);
                    goToComics();
                });
                tbody.appendChild(td);

                keywordDiv3.appendChild(table);
                keywordDiv3.appendChild(tbody);
            }

            var keywordDiv4 = document.createElement('div');
            keywordDiv4.className = "keyworddiv";
            var p = document.createElement('P');
            p.id = "kwd";
            p.style = "cursor:default";
            p.innerHTML = Keyword4;
            keywordDiv4.appendChild(p);
            appendDiv.appendChild(keywordDiv4);
            for (let i = 0; i < obj.D4.length; i = i + 1) {
                var src1 = obj.D4[i].panel1Src;
                var title1 = obj.D4[i].panel1Title;
                var title2 = obj.D4[i].panel2Title;
                var title3 = obj.D4[i].panel3Title;
                var title4 = obj.D4[i].panel4Title;
                var comic_id = obj.D4[i].comic_id;
                var allTitle = title1 + " " + title2 + " " + title3;
                if (title4 == undefined) {
                    title4 = "";
                } else {
                    allTitle = allTitle + " " + title4;
                }


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
                table.setAttribute('class', 'style-1');
                var h31 = document.createElement('h3');
                if(allTitle.length > 22) {
                    allTitle = allTitle.substring(0,21)+"...";
                }
                h31.innerHTML = allTitle;
                th.appendChild(imgsrc1);
                th.appendChild(h31);
                td.appendChild(th);
                td.addEventListener('click', function () {
                    $.cookie("comicId", comic_id);
                    goToComics();
                });
                tbody.appendChild(td);

                keywordDiv4.appendChild(table);
                keywordDiv4.appendChild(tbody);
            }

            var keywordDiv5 = document.createElement('div');
            keywordDiv5.className = "keyworddiv";
            var p = document.createElement('P');
            p.id = "kwd";
            p.style = "cursor:default";
            p.innerHTML = Keyword5;
            keywordDiv5.appendChild(p);
            appendDiv.appendChild(keywordDiv5);
            for (let i = 0; i < obj.D5.length; i = i + 1) {
                var src1 = obj.D5[i].panel1Src;
                var title1 = obj.D5[i].panel1Title;
                var title2 = obj.D5[i].panel2Title;
                var title3 = obj.D5[i].panel3Title;
                var title4 = obj.D5[i].panel4Title;
                var comic_id = obj.D5[i].comic_id;
                var allTitle = title1 + " " + title2 + " " + title3;
                if (title4 == undefined) {
                    title4 = "";
                } else {
                    allTitle = allTitle + " " + title4;
                }


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
                table.setAttribute('class', 'style-1');
                var h31 = document.createElement('h3');
                if(allTitle.length > 22) {
                    allTitle = allTitle.substring(0,21)+"...";
                }
                h31.innerHTML = allTitle;
                th.appendChild(imgsrc1);
                th.appendChild(h31);
                td.appendChild(th);
                td.addEventListener('click', function () {
                    $.cookie("comicId", comic_id);
                    goToComics();
                });
                tbody.appendChild(td);

                keywordDiv5.appendChild(table);
                keywordDiv5.appendChild(tbody);
            }
        }
    });
}
function goToComics(){
    document.location.href = "viewGameComics.html";
}