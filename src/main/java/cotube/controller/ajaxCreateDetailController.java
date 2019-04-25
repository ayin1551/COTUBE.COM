package cotube.controller;


import cotube.domain.*;
import cotube.services.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64.Decoder;
import java.util.List;

@Controller
@RequestMapping(value = "/createComicDetail.html")
public class ajaxCreateDetailController {

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

    @RequestMapping(value = "/saveComic", method = RequestMethod.POST)
    public RedirectView saveComic(HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        String img = request.getParameter("data");
        byte[] imageByte;
        BufferedImage image = null;
        Decoder decoder = java.util.Base64.getMimeDecoder();
        //Integer comicId = 23333; //comidId which need to return

        imageByte = decoder.decode(img);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();
/*
        File outputfile = new File("cotubeImage.png"); //file path and file name need to change
        ImageIO.write(image, "png", outputfile);
        System.out.println(request.getParameter("username"));
*/
        /*

            Create a new comic, regularComic and panel in the db with the username given
            Fill the other fields of the tables with placeholder
            Save the newly generated comicId in the variable comidId

        */

        Panel panel = new Panel();
        Comic comic = new Comic();
        RegularComic rc = new RegularComic();
        panel.setAuthor(username);
        comic.setComic_type(0);
        comic.setStatus(0);
        comicService.addComic(comic);
        Integer comicId = comic.getComic_id();
        panel.setCanvas_path("comicID_" + comicId + ".png");
        System.out.println(panel.getCanvas_path());
        rc.setRegular_comic_id(comicId);
        rc.setPanel_id(panel.getPanel_id());

        panelService.addPanel(panel);
        //comicService.addComic(comic);
        regularComicService.addRegularComic(rc);
        File outputfile = new File("src/main/resources/resources/img/t/comicID_" + comicId + ".png"); //file path and file name need to change
        System.out.println("Path of outputfile:" + outputfile);
        ImageIO.write(image, "png", outputfile);

        return new RedirectView("?createComicId="+Integer.toString(comicId));
    }

    @RequestMapping(value = "/uploadCmcThumb", method = RequestMethod.POST)
    public String uploadCmcThumb(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        String img = request.getParameter("img");
        String filePath = "comic-" + comicId + "_thumbnail.png";
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

        return img;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        String descr = request.getParameter("descr");
        String title = request.getParameter("title");
        String thumb = request.getParameter("oldthumb");
        String seriesID = request.getParameter("seriesId");
/*        String tag1 = request.getParameter("tag1");
        String tag2 = request.getParameter("tag2");
        String tag3 = request.getParameter("tag3");
        String tag4 = request.getParameter("tag4");
        String tag5 = request.getParameter("tag5");*/

        System.out.println(request.getParameter("comicId"));
        System.out.println(request.getParameter("descr"));
        System.out.println(request.getParameter("title"));
        System.out.println(request.getParameter("oldthumb"));
        System.out.println(request.getParameter("seriesId"));
  /*      System.out.println(request.getParameter("tag1"));
        System.out.println(request.getParameter("tag2"));
        System.out.println(request.getParameter("tag3"));
        System.out.println(request.getParameter("tag4"));
        System.out.println(request.getParameter("tag5"));*/
        Comic comic = comicService.getComicByComic_Id(Integer.parseInt(comicId));
        comic.setTitle(title);
        RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(Integer.parseInt(comicId));
        rc.setDescription(descr);
        rc.setThumbnail_path(thumb);
        rc.setSeries_id(Integer.parseInt(seriesID));
/*
        if (tag5.equals(null))
            System.out.println(":S");
        if (tag5.equals("null"))
            System.out.println(":Ss");
        if (tag5 == null)
            System.out.println(":Sas");
        if (tag5 == "null")
            System.out.println(":Sasadf");
            */
        return "createComicDetail";
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public String publish(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        String descr = request.getParameter("descr");
        String title = request.getParameter("title");
        String thumb = request.getParameter("oldthumb");
        String seriesID = request.getParameter("seriesId");

       /* String tag1 = request.getParameter("tag1");
        String tag2 = request.getParameter("tag2");
        String tag3 = request.getParameter("tag3");
        String tag4 = request.getParameter("tag4");
        String tag5 = request.getParameter("tag5");
*/
        System.out.println(request.getParameter("comicId"));
        System.out.println(request.getParameter("descr"));
        System.out.println(request.getParameter("title"));
        System.out.println(request.getParameter("oldthumb"));
        System.out.println(request.getParameter("seriesId"));
        /*System.out.println(request.getParameter("tag1"));
        System.out.println(request.getParameter("tag2"));
        System.out.println(request.getParameter("tag3"));
        System.out.println(request.getParameter("tag4"));
        System.out.println(request.getParameter("tag5"));*/

        Comic comic = comicService.getComicByComic_Id(Integer.parseInt(comicId));
        comic.setTitle(title);
        comic.setStatus(1);
        RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(Integer.parseInt(comicId));
        rc.setDescription(descr);
        rc.setThumbnail_path(thumb);


        regularComicService.addRegularComic(rc);
        comicService.addComic(comic);
        return "createComicDetail";
    }

    @RequestMapping(value = "/getSeries", method = RequestMethod.POST)
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
        return "createComicDetail";
    }

}
