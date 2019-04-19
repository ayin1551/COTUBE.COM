package cotube.repositories;

import cotube.domain.Comments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import org.springframework.data.repository.query.Param;


public interface CommentsRepository extends CrudRepository<Comments, Integer> {

    @Query(value = "SELECT * from Comments c where c.comic_id = :comic_id", nativeQuery = true)
    List<Comments> getAllCommentsInComic(@Param("comic_id") Integer comic_id);
}