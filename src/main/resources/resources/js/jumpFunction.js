function searchResult() {
    var word = document.getElementById("search_word").value;
    $.cookie('search_word',word);
    $.cookie('searchComicType', 'regular');
    var by = document.getElementById("search_by").value;
    $.cookie('search_by',by);
    if(word!=""){
        document.location.href="./searchResult.html";  
        
    } 
}


function authorprofile(name){
    $.cookie('profileUsername', name);
    document.location.href="./profile.html";
}


function trendingcomic(comicId, ifSeries){
    if (ifSeries == null)
        ifSeries = false;
    else
        ifSeries = true;
    $.cookie("comicId", comicId);
    $.cookie("ifSeries", ifSeries);
    document.location.href="./viewComics.html"
}

function popularseries(series_id){
    $.cookie('seriesId', series_id);
    document.location.href="./viewSeries.html";
}

function admingohome(){
    document.location.href="./admin.html";
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
    $.cookie('profileUsername', $.cookie('username'));
    document.location.href="./profile.html";
}

function goViewComic(id){
    $.cookie('profileUsername', $.cookie('username'));
    $.cookie('comicId',id);
    // $.cookie('ifSeries', false);
    document.location.href="./viewComics.html";
}

function goViewGameComic(id){
    $.cookie('profileUsername', $.cookie('username'));
    $.cookie('comicId',id);
    document.location.href="./viewGameComics.html";
}

function goCreateComic(id,panel){
    $.cookie('comicId', id);
    $.cookie('panelNo',panel);
    document.location.href = "./createGame.html";
}

function goViewSeries(id){
    $.cookie('seriesId',id);
    document.location.href="./viewSeries.html";
}

function login(){
    document.location.href="./login.html";
}

function logout(){
    $.cookie('username', null);
    $.cookie('role',"guest");
    document.location.href="./index.html";
}
