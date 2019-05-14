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
            var obj = jQuery.parseJSON(data);
            for (let i = 0; i < obj.MESSAGES.length; i = i + 1) {
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
                TABLEROW.appendChild(TD1);
                TABLEROW.appendChild(TD2);
                TABLEROW.appendChild(deleteTD);
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