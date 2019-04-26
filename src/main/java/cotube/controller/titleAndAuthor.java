package cotube.controller;

public class titleAndAuthor {
    public String title;
    public String author;
    public int ID;
    public titleAndAuthor(String title, String author, int id){
        this.title = title;
        this.author = author;
        this.ID = id;
    }

    public int getID() {
        return ID;
    }

    public String getAuthor() {
        return author;
    }
    public String getTitle(){
        return title;
    }
}
