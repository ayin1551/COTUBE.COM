package cotube.repositories;

import cotube.domain.Comic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ComicRepository extends CrudRepository<Comic, Integer> {

    @Query(value = "SELECT * from Comic c where c.comic_id = :comic_id", nativeQuery = true)
    Comic getComicByComic_id(@Param("comic_id") Integer comic_id);

}