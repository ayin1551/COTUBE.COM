package cotube.repositories;

import cotube.domain.Favorite;
import cotube.domain.Views;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {
    @Query(value = "SELECT * from Favorite f where f.comic_id = :comic_id", nativeQuery = true)
    List<Favorite> getAllFavoritesInComic(@Param("comic_id") Integer comic_id);

    @Query(value = "SELECT * from Favorite f where f.favorite_folder_id = :folder_id", nativeQuery = true)
    List<Favorite> getAllFavoritesFromFolderId(@Param("folder_id") Integer folder_id);
}