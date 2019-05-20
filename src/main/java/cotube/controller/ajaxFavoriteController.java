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

import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value="/favorite.html")
public class ajaxFavoriteController{


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

    @RequestMapping(value="/getInfo",method = RequestMethod.POST)
    @ResponseBody
    public String getInfo(HttpServletRequest request){
        Integer folderId = Integer.parseInt(request.getParameter("favoriteId"));
        List<Folder> folders = folderService.getAllFolders();
        String folderName = "";
        Boolean pub = false;
        String folderOwner = "";

        

        for(Folder folder: folders){
            if (folder.getFolder_id().equals(folderId)){
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
            if (folder.getFolder_id().equals(folderId)){
                folder.setVisibility(pub?1:0);
                folderService.addFolder(folder);
                return true;
            }
        }

        return false;
    }

    @RequestMapping(value="/deleteFolder",method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteFolder(HttpServletRequest request){


        Integer folderId = Integer.parseInt(request.getParameter("favoriteId"));
        List<Favorite> favorites = favoriteService.getAllFavoritesInFolderId(folderId);


        for(Favorite favorite: favorites){
            favoriteService.deleteFavorite(favorite);
        }
        List<Folder> folders = folderService.getAllFolders();


        for(Folder folder: folders){
            if (folder.getFolder_id().equals(folderId)){
                folderService.deleteFolder(folder);
                return true;
            }
        }

        return false;
    }
    
    @RequestMapping(value="/getComics",method = RequestMethod.POST)
    @ResponseBody
    public String getComics(HttpServletRequest request){
        Integer favoriteId = Integer.parseInt(request.getParameter("favoriteId"));
        List<Favorite> favorites = favoriteService.getAllFavorites();
        List<Comic> comics = comicService.getAllComics();
        List<RegularComic> regularComics = regularComicService.getAllRegularComics();
        List<Integer> comicId = new ArrayList<Integer>();
        List<String> comicName = new ArrayList<String>();
        List<String> comicThumbnail = new ArrayList<String>();
        List<Boolean> comicSeries = new ArrayList<Boolean>();

        for(Favorite f: favorites){
            if(f.getFavorite_folder_id().equals(favoriteId)){
                comicId.add(f.getComic_id());
            }
        }

        Collections.sort(comicId);
        Collections.reverse(comicId);

        for(Integer i: comicId){
            for(Comic c: comics){
                if(c.getComic_id().equals(i)){
                    comicName.add(c.getTitle());
                    break;
                }
            }

            for(RegularComic rc: regularComics){
                if(rc.getRegular_comic_id().equals(i)){
                    comicThumbnail.add(rc.getThumbnail_path());
                    comicSeries.add(rc.getSeries_id()==null?false:true);
                    break;
                }
            }
        }
        
        JSONObject result = new JSONObject();
        result.put("comicName", comicName);
        result.put("comicId", comicId);
        result.put("comicThumbnail", comicThumbnail);
        result.put("comicSeries", comicSeries);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value="/unfavorite",method = RequestMethod.POST)
    @ResponseBody
    public Boolean unfavorite(HttpServletRequest request){
        Integer favoriteId = Integer.parseInt(request.getParameter("favoriteId"));
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        List<Favorite> favorites = favoriteService.getAllFavorites();


        for(Favorite f: favorites){
            if(f.getFavorite_folder_id().equals(favoriteId) && f.getComic_id().equals(comicId)){
                this.favoriteService.deleteFavorite(f);
                return true;
            }
        }

        return false;

    }


}
