package cotube.domain;



public class seriesSearchPackage{
    String title ;
    String picPath;
    String author;
    //int likes;
    //int views;
    int seriesID;
    int total;
    public seriesSearchPackage(String title,String path,String author,int seriesID,int total){
        this.title = title;
        this.author = author;
        this.picPath = path;
        this.seriesID = seriesID;
        this.total = total;
    }
    public int getSeriesID(){
        return seriesID;
    }
    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }
    public String getPicPath(){
        return picPath;
    }
    public int getTotal(){
        return this.total;
    }
}
