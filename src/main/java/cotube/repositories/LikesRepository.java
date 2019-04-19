package cotube.repositories;

import cotube.domain.Comments;
import cotube.domain.Likes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikesRepository extends CrudRepository<Likes, Integer> {
    @Query(value = "SELECT * from Likes l where l.comic_id = :comic_id", nativeQuery = true)
    List<Likes> getAllLikesInComic(@Param("comic_id") Integer comic_id);
}