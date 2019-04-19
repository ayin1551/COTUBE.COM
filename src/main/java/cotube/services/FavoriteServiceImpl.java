package cotube.services;

import cotube.domain.Favorite;
import cotube.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    public void setProductRepository(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }


    @Override
    public Favorite addFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public List<Favorite> getAllFavorites() {

        List<Favorite> favorites = (List<Favorite>) favoriteRepository.findAll();
        return favorites;
    }

    @Override
    public void deleteFavorite(Favorite favorite) {
        favoriteRepository.delete(favorite);
    }

    @Override
    public List<Favorite> getAllFavoritesInComic(Integer comic_id) {
        return favoriteRepository.getAllFavoritesInComic(comic_id);
    }

    @Override
    public List<Favorite> getAllFavoritesInFolderId(Integer folder_id) {
        return favoriteRepository.getAllFavoritesFromFolderId(folder_id);
    }

}