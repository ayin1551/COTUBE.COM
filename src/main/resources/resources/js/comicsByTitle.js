function getAllComicsByTitle(){
    $.ajax({
        url: "viewComicsByTitle.html/comicTitles",
        type: "post",
        async: false,
        success: function (data) {
            var obj = jQuery.parseJSON(data);
            for (let i = 0; i < obj.TNA.length; i = i + 1) {
                if(obj["TNA"][i].title.length > 35) {
                    obj["TNA"][i].title = obj["TNA"][i].title.substring(0,34)+"...";
                }
                var title = obj["TNA"][i].title;
                var author = obj["TNA"][i].author;
                var id = obj["TNA"][i].ID;
                var firstChar = title.charAt(0).toLowerCase();
                var tbody;
                var letters = /^[A-Za-z]/;
                if(!firstChar.match(letters)){
                    console.log("in if " + firstChar);
                    tbody = document.getElementById('#');
                }else{
                    console.log("in else " + firstChar);
                    tbody = document.getElementById(firstChar);
                }
                var tablerow = document.createElement('TR');
                var tableheadEmpty = document.createElement('TH');
                tableheadEmpty.style = "width:5%";
                var tableheadBullet = document.createElement('TH');
                tableheadBullet.style = "width:5%";
                tableheadBullet.innerHTML = "&#9679";
                var tableheadTitle = document.createElement("TH");
                tableheadTitle.addEventListener('click', function () {
                    $.cookie('ifSeries',obj.isSeries[i]);
                    goViewComic(obj["TNA"][i].ID);
                });
                tableheadTitle.style = "width:25%";
                tableheadTitle.style.cursor = "pointer";
                tableheadTitle.innerHTML = title;
                var tableheadAuthor = document.createElement("TH");
                tableheadAuthor.addEventListener('click', function () {
                    authorprofile(obj["TNA"][i].author);
                });

                tableheadAuthor.style = "width:15%";
                tableheadAuthor.style.cursor = "pointer";
                tableheadAuthor.innerHTML = author;
                tablerow.appendChild(tableheadEmpty);
                tablerow.appendChild(tableheadBullet);
                tablerow.appendChild(tableheadTitle);
                tablerow.appendChild(tableheadAuthor);
                tbody.appendChild(tablerow);
            }
        }
});
}