package cotube.controller;

import cotube.domain.FollowUser;
import cotube.services.FollowUserService;
import cotube.domain.Account;
import cotube.domain.Folder;
import cotube.services.AccountService;
import cotube.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/favorite.html")
public class ajaxFavoriteController{


    private FolderService folderService;
    @Autowired
    public void setFolderService(FolderService folderService) {
        this.folderService = folderService;
    }


    @RequestMapping(value="/getInfo",method = RequestMethod.POST)
    @ResponseBody
    public String getInfo(HttpServletRequest request){
        Integer folderId = Integer.parseInt(request.getParameter("favoriteId"));
        List<Folder> folders = folderService.getAllFolders();
        String folderName = "";
        Boolean pub = false;
        String folderOwner = "";

        

        for(Folder folder: folders){
            if (folder.getFolder_id() == folderId){
                folderName = folder.getFolder_name();
                pub = folder.getVisibility()==1?true:false;
                folderOwner = folder.getUsername();
            }
        }

        JSONObject result = new JSONObject();
        result.put("folderName", folderName);
        result.put("folderPublic", pub);
        result.put("folderOwner", folderOwner);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value="/togglePublic",method = RequestMethod.POST)
    @ResponseBody
    public Boolean togglePublic(HttpServletRequest request){
        Integer folderId = Integer.parseInt(request.getParameter("favoriteId"));
        Boolean pub = request.getParameter("public").equals("true");
        List<Folder> folders = folderService.getAllFolders();


        for(Folder folder: folders){
            if (folder.getFolder_id() == folderId){
                folder.setVisibility(pub?1:0);
                folderService.addFolder(folder);
                return true;
            }
        }

        return false;
    }
    
}
