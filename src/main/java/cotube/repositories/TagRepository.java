package cotube.repositories;

import cotube.domain.Tag;
import cotube.domain.Views;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Integer> {
    @Query(value = "SELECT * from Tag t where t.regular_comic_id = :regular_comic_id", nativeQuery = true)
    List<Tag> getAllTagsInRegularComic(@Param("regular_comic_id") Integer regular_comic_id);
}