package cotube.controller;

import cotube.domain.Folder;
import cotube.domain.Favorite;
import cotube.domain.Comic;
import cotube.domain.RegularComic;
import cotube.services.RegularComicService;
import cotube.services.ComicService;
import cotube.services.FavoriteService;
import cotube.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64.Decoder;

@Controller
@RequestMapping(value = "/createComic.html")
public class ajaxCreateController {

    private FolderService folderService;

    @Autowired
    public void setFolderService(FolderService folderService) {
        this.folderService = folderService;
    }

    private FavoriteService favoriteService;

    @Autowired
    public void setFavoriteService(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

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

    // @RequestMapping(value = "/saveComic", method = RequestMethod.POST)
    // public String saveComic(HttpServletRequest request) throws IOException {
    //     String img = request.getParameter("data");
    //     byte[] imageByte;
    //     BufferedImage image = null;
    //     Decoder decoder = java.util.Base64.getMimeDecoder();

    //     imageByte = decoder.decode(img);
    //     ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
    //     image = ImageIO.read(bis);
    //     bis.close();
        
    //     File outputfile = new File("cotubeImage.png");
    //     ImageIO.write(image, "png", outputfile);

    //     return "";
    // }

 


}
