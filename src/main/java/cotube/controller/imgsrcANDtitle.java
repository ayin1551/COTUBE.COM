package cotube.controller;

public class imgsrcANDtitle {
    String panel1Src;
    String panel1Title;
    String panel2Title;
    String panel3Title;
    String panel4Title;
    int comic_id;
    public imgsrcANDtitle(){

    }
    public int getComic_id(){
        return this.comic_id;
    }
    public void setComic_id(int x){
        this.comic_id = x;
    }
    public String getPanel1Src(){
        return panel1Src;
    }
    public String getPanel1Title(){
        return panel1Title;
    }
    public String getPanel2Title(){
        return panel2Title;
    }
    public String getPanel3Title(){
        return panel3Title;
    }
    public String getPanel4Title(){
        return panel4Title;
    }
    public void setPanel1Src(String x){
        x = this.panel1Src;
    }
    public void setPanel1Title(String x){
        x = panel1Title;
    }
    public void setPanel2Title(String x) {
        x = panel2Title;
    }
    public void setPanel3Title(String x){
        x = panel3Title;
    }
    public void setPanel4Title(String x){
        x = panel4Title;
    }
}
