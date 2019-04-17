function searchResult() {
    console.log("innnnnnnnnnnn");
    var word = document.getElementById("search_word").value;
    $.cookie('search_word',word);

    var by = document.getElementById("search_by").value;
    $.cookie('search_by',by);
    console.log(word);
    console.log(by);
    if(word!=""){
        console.log("innnnnnnnnnnn");
        document.location.href="./searchResult.html";  
        
    }
    
        
    
        
    
    
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