function getTrending(){
    var obj;
    $.ajax({
        type: "post",
        url: "index.html/trending",
        async: false,
        dataType:"json",
        data: {},
        success: function(data){
            console.log(data);
            obj = data;
        }
    });
    return obj;
}

function getPopularSeries(){
    var obj;
    $.ajax({
        type: "post",
        url: "index.html/popseries",
        async: false,
        dataType:"json",
        data: {},
        success: function(data){
            console.log(data);
            obj = data;
        }
    });
    return obj;
}

function getTopRated(){
    var obj;
    $.ajax({
        type: "post",
        url: "index.html/toprated",
        async: false,
        dataType:"json",
        data: {},
        success: function(data){
            console.log(data);
            obj = data;
        }
    });
    return obj;
}

function getTimeline(username){
    var obj;
    $.ajax({
        type: "post",
        url: "index.html/getTimeline",
        async: false,
        data: {username: username},
        success: function(data){
            obj = jQuery.parseJSON(data);
        }
    });
    return obj;
}