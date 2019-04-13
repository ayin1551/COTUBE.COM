function searchResult() {
    var word = document.getElementById("search_word").value;
    $.cookie('search_word',word);

    var by = document.getElementById("search_by").value;
    $.cookie('search_by',by);
    if(word!="")
        document.location.href="./searchResult.html";

    $(window).load(function() {
        if (by == "author") {
            $.ajax({
                url: "searchResult.html/author",
                type: "post",
                async: false,
                data: {author: word},
                success: function (data) {
                    var tbody = document.getElementById("author_result_table");
                    alert(tbody);
                    var obj = jQuery.parseJSON(data);
                    for (var i = 0; i < obj.account.length; i++) {
                        var tr = document.createElement('TR');
                        tbody.appendChild(tr);
                        var td = document.createElement('TD');
                        td.appendChild(document.createTextNode(obj.account[i]));
                        tr.appendChild(td);


                    }
                }
            });
        }
    });
}

function gohome(){
    document.location.href="./index.html";
}

function goCreate(){
    document.location.href="./createHome.html";
}

function goMessage() {
    document.location.href="./message.html";
}

function gotosetting(){
    document.location.href="./setting.html";
}

function goProfile(){
    document.location.href="./profile.html";
}

function login(){
    document.location.href="./login.html";
}

function logout(){
    $.cookie('username', null);
    $.cookie('role',"guest");
    document.location.href="./index.html";
}