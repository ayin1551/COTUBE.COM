package cotube.domain;


public class searchPackage{
    String title ;
    String picPath;
    String author;
    int likes;
    int views;
    int comicID;
    public searchPackage(String title,String path,String author,int likes,int views,int comicID){
        this.title = title;
        this.author = author;
        this.picPath = path;
        this.likes = likes;
        this.views = views;
        this.comicID = comicID;
    }
    
    public int getComicID(){
        return comicID;
    }
    public String getTitle() {
        return title;
    }

    public int getLikes() {
        return likes;
    }

    public int getViews() {
        return views;
    }

    public String getAuthor() {
        return author;
    }
    public String getPicPath(){
        return picPath;
    }
}
