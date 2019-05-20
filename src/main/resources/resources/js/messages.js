function goCreateComic(id,panel){
    $.cookie('comicId', id);
    $.cookie('panelNo',panel);
    document.location.href = "./createGame.html";
}


function loadTable(){
    var tbody = document.getElementById("messageBody");
    tbody.innerHTML = "";
    var username = $.cookie('username');
    console.log(username);
    $.ajax({
        url: "message.html/getMessages",
        type: "post",
        data: {username: username},
        async: false,
        success: function (data) {
            console.log(data);
            var obj = jQuery.parseJSON(data);
            for (let i = 0; i < obj.MESSAGES.length; i = i + 1) {
                console.log(obj.MESSAGES[i].notification_type);
                var TABLEROW = document.createElement('TR');
                var TD1 = document.createElement('TD');
                TD1.style="text-align: left";
                TD1.innerHTML = obj.MESSAGES[i].notification;
                var TD2 = document.createElement('TD');
                TD2.style="text-align: right; font-size: 14px; font-weight: lighter";
                TD2.innerHTML = obj.MESSAGES[i].notifcation_time;

                var deleteTD = document.createElement('TD');
                var wrong = document.createElement('input');
                wrong.type = "image";
                wrong.src = "./img/delete.png";
                wrong.height = "35";
                wrong.style = "margin-left:10px";
                wrong.addEventListener('click', function () {
                    deleteComment(obj.MESSAGES[i].notification_id);
                    //goViewSeries(obj["TPALV"][i].seriesID);
                });

                deleteTD.appendChild(wrong);
                var anchor = document.createElement('a');
                anchor.innerHTML = "Go to";
                anchor.addEventListener('click', function () {

                    
                    if (obj.MESSAGES[i].notification_type == 3){
                        var type = getType(obj.MESSAGES[i].link);
                        if (type == 1){
                            goViewGameComic(obj.MESSAGES[i].link);
                        }
                        else
                            goViewComic(obj.MESSAGES[i].link);
                    }
                    if (obj.MESSAGES[i].notification_type == 6){
                        var splitted = obj.MESSAGES[i].link.split(" ");
                        var one = splitted[0];
                        var lastChar = obj.MESSAGES[i].link[obj.MESSAGES[i].link.length -1];
                        // goCreateComic(one,lastChar);
                        goCreateComic(one, lastChar);
                    }
                    else if (obj.MESSAGES[i].notification_type == 7){
                        goViewGameComic(obj.MESSAGES[i].link)
                    }
                });

                TABLEROW.appendChild(TD1);
                TABLEROW.appendChild(TD2);

                TABLEROW.appendChild(deleteTD);
                if (obj.MESSAGES[i].notification_type > 5 || obj.MESSAGES[i].notification_type == 3) {
                    TABLEROW.appendChild(anchor);
                }
                tbody.appendChild(TABLEROW);
            }
        }
    });
}
function deleteComment(id){
    $.ajax({
        url: "message.html/deleteComment",
        type: "post",
        data: {id: id},
        async: false,
        success: function (data) {
            document.location.reload();
        }
    });
}

function getType(id){
    var type;
    $.ajax({
        url: "message.html/getType",
        type: "post",
        data: {id: id},
        async: false,
        success: function (data) {
            type = data;
        }
    });
    return type;
}
