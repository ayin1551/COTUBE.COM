package cotube.controller;

import java.util.Date;

public class comicCensorPackage {
    int id;
    String title;
    //String author;
    Date date_published;
    public comicCensorPackage(int id,String title,Date date_published){
        this.id = id;
        this.title = title;
        //this.author = author;
        this.date_published = date_published;
    }

    public String getTitle() {
        return title;
    }

    //public String getAuthor() {
        //return author;
    //}

    public int getId() {
        return id;
    }

    public Date getDate_published() {
        return date_published;
    }
}
