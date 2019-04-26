package cotube.controller;

import cotube.domain.*;
import cotube.services.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64.Decoder;
import java.util.List;

@Controller
@RequestMapping(value = "/editComicDetail.html")
public class ajaxEditDetailController {

    private ComicService comicService;
    @Autowired
    public void setComicService(ComicService comicService) {
        this.comicService = comicService;
    }

    private RegularComicService regularComicService;
    @Autowired
    public void setRegularComicService(RegularComicService regularComicService) {
        this.regularComicService = regularComicService;
    }

    private FolderService folderService;
    @Autowired
    public void setFolderService(FolderService folderService){
        this.folderService = folderService;
    }
    private SeriesService seriesService;
    @Autowired
    public void setSeriesService(SeriesService seriesService){
        this.seriesService = seriesService;
    }

    private PanelService panelService;
    @Autowired
    public void setPanelService(PanelService panelService) {
        this.panelService = panelService;
    }

    private TagService tagService;
    @Autowired
    public void setTagService(TagService tagService) {this.tagService = tagService;}
    @RequestMapping(value = "/saveComic", method = RequestMethod.POST)
    public RedirectView saveComic(HttpServletRequest request) throws IOException {
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        String img = request.getParameter("data");
        byte[] imageByte;
        BufferedImage image = null;
        Decoder decoder = java.util.Base64.getMimeDecoder();


        imageByte = decoder.decode(img);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();
        
       // File outputfile = new File("cotubeImage_new.png");  //file path and file name need to change
                                                        //Do not replace the old comic at this time
        //ImageIO.write(image, "png", outputfile);
        //System.out.println(request.getParameter("username"));

        /*

            Save the new comic file elsewhere with a different file name
            Once commit the edit, replace the old comic with the new one (This will be handle by another controller)

        */

       /* Panel panel = panelService.getPanelFromPanelId(comicId);
        Comic comic = comicService.getComicByComic_Id(comicId);
        RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(comicId);
        comicService.addComic(comic);
        panel.setCanvas_path("newcomicID_" + comicId + ".png");
        System.out.println(panel.getCanvas_path());*/
        //rc.setRegular_comic_id(comicId);
        //rc.setPanel_id(panel.getPanel_id());

        //panelService.addPanel(panel);
        //comicService.addComic(comic);
        //regularComicService.addRegularComic(rc);
        File outputfile = new File("src/main/resources/resources/img/regularcomics/newcomicID_" + comicId + ".png"); //file path and file name need to change
        System.out.println("Path of outputfile:" + outputfile);
        ImageIO.write(image, "png", outputfile);


        return new RedirectView("?editComicId="+Integer.toString(comicId));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        String descr = request.getParameter("descr");
        String title = request.getParameter("title");
        String thumb = request.getParameter("thumb");
        String newSeries = request.getParameter("newSeries");
        String existSeries = request.getParameter("existSeries");
        String seriesThumb = request.getParameter("seriesThumb");
        String tag1 = request.getParameter("tag1");
        String tag2 = request.getParameter("tag2");
        String tag3 = request.getParameter("tag3");
        String tag4 = request.getParameter("tag4");
        String tag5 = request.getParameter("tag5");

        System.out.println("comicId:" + request.getParameter("comicId"));
        System.out.println("descr:" +request.getParameter("descr"));
        System.out.println("title:" +request.getParameter("title"));
        System.out.println("thumb:" +request.getParameter("thumb"));
        System.out.println("newSeries:" +request.getParameter("newSeries"));
        System.out.println("existSeries:" +request.getParameter("existSeries"));
        System.out.println("seriesThumb:" +request.getParameter("seriesThumb"));
        System.out.println("tag1:" +request.getParameter("tag1"));
        System.out.println("tag2:" +request.getParameter("tag2"));
        System.out.println("tag3:" +request.getParameter("tag3"));
        System.out.println("tag4:" +request.getParameter("tag4"));
        System.out.println("tag5:" +request.getParameter("tag5"));


        Comic comic = comicService.getComicByComic_Id(Integer.parseInt(comicId));
        comic.setTitle(title);
        comic.setStatus(0);
        RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(Integer.parseInt(comicId));
        rc.setDescription(descr);
        rc.setThumbnail_path(thumb);

        //An existing series was selected
        if (!existSeries.equals("")){
            List<Series> seriesList = seriesService.getAllSeries();
            for (int i = 0; i < seriesList.size(); i++){
                if (seriesList.get(i).getSeries_name() == existSeries)
                    rc.setSeries_id(seriesList.get(i).getSeries_id());
            }
        }
        //A new series was selected
        if (!newSeries.equals("")){
            String user = panelService.getPanelFromPanelId(rc.getPanel_id()).getAuthor();
            Folder folder = new Folder();
            folder.setFolder_type(1);
            folder.setVisibility(1);
            folder.setUsername(user);
            folder.setFolder_name(user+newSeries+"seriesfolder");
            folderService.addFolder(folder);
            Series series = new Series();
            series.setFolder_id(folder.getFolder_id());
            series.setSeries_name(newSeries);
            series.setThumbnail_path(seriesThumb);
            seriesService.addSeries(series);
            rc.setSeries_id(series.getSeries_id());
        }

        regularComicService.addRegularComic(rc);

        List<Tag> previousTags = tagService.getAllTagsInRegularComic(Integer.parseInt(comicId));
        for (int i = 0; i < previousTags.size();i++){
            tagService.deleteTag(previousTags.get(i));
        }

        if (!tag1.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag1);
            tagService.addTag(tag);
        }
        if (!tag2.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag1);
            tagService.addTag(tag);
        }
        if (!tag3.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag1);
            tagService.addTag(tag);
        }
        if (!tag4.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag1);
            tagService.addTag(tag);
        }
        if (!tag5.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag1);
            tagService.addTag(tag);
        }


        comicService.addComic(comic);
        System.out.println("savb");

        return "editComicDetail";
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public String publish(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        String descr = request.getParameter("descr");
        String title = request.getParameter("title");
        String thumb = request.getParameter("thumb");
        String newSeries = request.getParameter("newSeries");
        String existSeries = request.getParameter("existSeries");
        String seriesThumb = request.getParameter("seriesThumb");
        String tag1 = request.getParameter("tag1");
        String tag2 = request.getParameter("tag2");
        String tag3 = request.getParameter("tag3");
        String tag4 = request.getParameter("tag4");
        String tag5 = request.getParameter("tag5");

        System.out.println("comicId:" + request.getParameter("comicId"));
        System.out.println("descr:" +request.getParameter("descr"));
        System.out.println("title:" +request.getParameter("title"));
        System.out.println("thumb:" +request.getParameter("thumb"));
        System.out.println("newSeries:" +request.getParameter("newSeries"));
        System.out.println("existSeries:" +request.getParameter("existSeries"));
        System.out.println("seriesThumb:" +request.getParameter("seriesThumb"));
        System.out.println("tag1:" +request.getParameter("tag1"));
        System.out.println("tag2:" +request.getParameter("tag2"));
        System.out.println("tag3:" +request.getParameter("tag3"));
        System.out.println("tag4:" +request.getParameter("tag4"));
        System.out.println("tag5:" +request.getParameter("tag5"));

        Comic comic = comicService.getComicByComic_Id(Integer.parseInt(comicId));
        comic.setTitle(title);
        comic.setStatus(1);
        RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(Integer.parseInt(comicId));
        rc.setDescription(descr);
        rc.setThumbnail_path(thumb);
        Panel panel = panelService.getPanelFromPanelId(rc.getPanel_id());
        File newComicFile = new File("src/main/resources/resources/img/regularcomics/newcomicID_" + comicId + ".png");
        File oldComicFile = new File("src/main/resources/resources/img/regularcomics/comicID_" + comicId + ".png");
        panel.setCanvas_path(newComicFile.getCanonicalPath());
        oldComicFile.delete();


        //An existing series was selected
        if (!existSeries.equals("")){
            List<Series> seriesList = seriesService.getAllSeries();
            for (int i = 0; i < seriesList.size(); i++){
                if (seriesList.get(i).getSeries_name() == existSeries)
                    rc.setSeries_id(seriesList.get(i).getSeries_id());
            }
        }
        //A new series was selected
        if (!newSeries.equals("")){
            String user = panelService.getPanelFromPanelId(rc.getPanel_id()).getAuthor();
            Folder folder = new Folder();
            folder.setFolder_type(1);
            folder.setVisibility(1);
            folder.setUsername(user);
            folder.setFolder_name(user+newSeries+"seriesfolder");
            folderService.addFolder(folder);
            Series series = new Series();
            series.setFolder_id(folder.getFolder_id());
            series.setSeries_name(newSeries);
            series.setThumbnail_path(seriesThumb);
            seriesService.addSeries(series);
            rc.setSeries_id(series.getSeries_id());
        }

        System.out.println("pub=");
        regularComicService.addRegularComic(rc);

        List<Tag> previousTags = tagService.getAllTagsInRegularComic(Integer.parseInt(comicId));
        for (int i = 0; i < previousTags.size();i++){
            tagService.deleteTag(previousTags.get(i));
        }

        if (!tag1.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag1);
            tagService.addTag(tag);
        }
        if (!tag2.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag2);
            tagService.addTag(tag);
        }
        if (!tag3.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag3);
            tagService.addTag(tag);
        }
        if (!tag4.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag4);
            tagService.addTag(tag);
        }
        if (!tag5.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag5);
            tagService.addTag(tag);
        }

        comicService.addComic(comic);
        return "editComicDetail";
    }

    @RequestMapping(value = "/getComic", method = RequestMethod.POST)
    @ResponseBody
    public String getComic(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        List<Comic> comic = new ArrayList<>();
        comic.add(comicService.getComicByComic_Id(Integer.parseInt(comicId)));
        List<RegularComic> rc = new ArrayList<>();
        rc.add(regularComicService.getRegularComicByRegular_Comic_Id(Integer.parseInt(comicId)));
        List<Tag> tags = tagService.getAllTagsInRegularComic(Integer.parseInt(comicId));
        JSONObject result = new JSONObject();
        result.put("comic", comic);
        result.put("rc", rc);
        result.put("tags", tags);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value = "/getSeries", method = RequestMethod.POST)
    @ResponseBody
    public String getSeries(HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        List<Folder> folderList = folderService.getAllFolders();
        List<Series> seriesList = seriesService.getAllSeries();
        List<Series> resultList = new ArrayList<>();
        for (int i = 0; i < seriesList.size(); i++){
            for(int j = 0; j < folderList.size(); j++){
                if (seriesList.get(i).getFolder_id() == folderList.get(j).getFolder_id())
                    if (folderList.get(j).getUsername().equals(username))
                        resultList.add(seriesList.get(i));
            }
        }
        JSONObject result = new JSONObject();
        result.put("series", resultList);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value = "/uploadCmcThumb", method = RequestMethod.POST)
    @ResponseBody
    public String uploadCmcThumb(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        String img = request.getParameter("img");
        String filePath = "newcomic-" + comicId + "_thumbnail.png";
        //File path and need to change
        byte[] imageByte;
        BufferedImage image = null;
        Decoder decoder = java.util.Base64.getMimeDecoder();

        imageByte = decoder.decode(img);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();

        System.out.println(filePath);
        File outputfile = new File(filePath);
        ImageIO.write(image, "png", outputfile);
        System.out.println("Comic Thumbnail edit");
        return filePath;
    }

    @RequestMapping(value = "/uploadSrsThumb", method = RequestMethod.POST)
    @ResponseBody
    public String uploadSrsThumb(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        String img = request.getParameter("img");
        String filePath = "seriesnewcomic-" + comicId + "_thumbnail.png";
        //File path and need to change
        byte[] imageByte;
        BufferedImage image = null;
        Decoder decoder = java.util.Base64.getMimeDecoder();

        imageByte = decoder.decode(img);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();

        System.out.println(filePath);
        File outputfile = new File(filePath);
        ImageIO.write(image, "png", outputfile);

        System.out.println("Series Thumbnail edit");
        return filePath;
    }

    @RequestMapping(value = "/pubpublish", method = RequestMethod.POST)
    public String pubPublish(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        String descr = request.getParameter("descr");
        String title = request.getParameter("title");
        String thumb = request.getParameter("thumb");
        String tag1 = request.getParameter("tag1");
        String tag2 = request.getParameter("tag2");
        String tag3 = request.getParameter("tag3");
        String tag4 = request.getParameter("tag4");
        String tag5 = request.getParameter("tag5");

        System.out.println("comicId:" + request.getParameter("comicId"));
        System.out.println("descr:" +request.getParameter("descr"));
        System.out.println("title:" +request.getParameter("title"));
        System.out.println("thumb:" +request.getParameter("thumb"));
        System.out.println("tag1:" +request.getParameter("tag1"));
        System.out.println("tag2:" +request.getParameter("tag2"));
        System.out.println("tag3:" +request.getParameter("tag3"));
        System.out.println("tag4:" +request.getParameter("tag4"));
        System.out.println("tag5:" +request.getParameter("tag5"));

        Comic comic = comicService.getComicByComic_Id(Integer.parseInt(comicId));
        comic.setTitle(title);
        comic.setStatus(1);
        RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(Integer.parseInt(comicId));
        rc.setDescription(descr);
        rc.setThumbnail_path(thumb);

        Panel panel = panelService.getPanelFromPanelId(rc.getPanel_id());
        File newComicFile = new File("src/main/resources/resources/img/regularcomics/newcomicID_" + comicId + ".png");
        File oldComicFile = new File("src/main/resources/resources/img/regularcomics/comicID_" + comicId + ".png");
        panel.setCanvas_path(newComicFile.getCanonicalPath());
        oldComicFile.delete();

        regularComicService.addRegularComic(rc);

        List<Tag> previousTags = tagService.getAllTagsInRegularComic(Integer.parseInt(comicId));
        for (int i = 0; i < previousTags.size();i++){
            tagService.deleteTag(previousTags.get(i));
        }

        if (!tag1.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag1);
            tagService.addTag(tag);
        }
        if (!tag2.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag2);
            tagService.addTag(tag);
        }
        if (!tag3.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag3);
            tagService.addTag(tag);
        }
        if (!tag4.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag4);
            tagService.addTag(tag);
        }
        if (!tag5.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag5);
            tagService.addTag(tag);
        }

        comicService.addComic(comic);
        System.out.println("pubpub");
        return "editComicDetail";
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public String cancel(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        System.out.println("comicId:" + request.getParameter("comicId"));

        File newComicFile = new File("src/main/resources/resources/img/regularcomics/newcomicID_" + comicId + ".png");
        File newSrsThumbFile = new File("seriesnewcomic-" + comicId + "_thumbnail.png");
        File newComicThumbFile = new File("newcomic-" + comicId + "_thumbnail.png");
        newComicFile.delete();
        newSrsThumbFile.delete();
        newComicThumbFile.delete();

        //Delete all files
        System.out.println("canc");

        return "editComicDetail";
    }

    @RequestMapping(value = "/pubCancel", method = RequestMethod.POST)
    public String pubCancel(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        System.out.println("comicId:" + request.getParameter("comicId"));

        File newComicFile = new File("src/main/resources/resources/img/regularcomics/newcomicID_" + comicId + ".png");
        File newComicThumbFile = new File("newcomic-" + comicId + "_thumbnail.png");
        newComicFile.delete();
        newComicThumbFile.delete();

        System.out.println("pubcanc");

        return "editComicDetail";
    }
    }
