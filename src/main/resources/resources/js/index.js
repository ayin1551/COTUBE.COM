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