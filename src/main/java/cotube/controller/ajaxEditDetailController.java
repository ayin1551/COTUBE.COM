package cotube.controller;

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
import java.util.Base64.Decoder;

@Controller
@RequestMapping(value = "/editComicDetail.html")
public class ajaxEditDetailController {


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
        
        File outputfile = new File("cotubeImage_new.png");  //file path and file name need to change
                                                        //Do not replace the old comic at this time
        ImageIO.write(image, "png", outputfile);
        System.out.println(request.getParameter("username"));

        /*

            Save the new comic file elsewhere with a different file name
            Once commit the edit, replace the old comic with the new one (This will be handle by another controller)

        */

        return new RedirectView("?editComicId="+Integer.toString(comicId));
    }

 


}
