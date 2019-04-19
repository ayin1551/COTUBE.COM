package cotube.repositories;

import cotube.domain.Views;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ViewsRepository extends CrudRepository<Views, Integer> {

    @Query(value = "select v.comic_id, count(comic_id) from Views v\n" +
            "Group by v.comic_id\n" +
            "ORDER BY count(comic_id) DESC", nativeQuery = true)
    List<Integer> getComicIdsOfMostViewedComics();
}