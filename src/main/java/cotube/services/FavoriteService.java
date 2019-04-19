package cotube.services;

import cotube.domain.Favorite;

import java.util.List;

public interface FavoriteService {
    Favorite addFavorite(Favorite favorite); //adds a comic to db *C
    List<Favorite> getAllFavorites(); //get all followUser pairs in db *R
    void deleteFavorite(Favorite favorite);
    List<Favorite> getAllFavoritesInComic(Integer comic_id);
    List<Favorite> getAllFavoritesInFolderId(Integer folder_id);
}