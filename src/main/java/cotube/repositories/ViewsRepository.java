package cotube.repositories;

import cotube.domain.Views;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ViewsRepository extends CrudRepository<Views, Integer> {

    @Query(value = "select v.comic_id from Views v where v.comic_id in (select c.comic_id from comic c where c.comic_type = 0 AND (c.status = 1 OR  c.status = 3)) Group by v.comic_id oRDER BY count(v.comic_id) DESC LIMIT 20", nativeQuery = true)
    List<Integer> getComicIdsOfMostViewedRegularComics();

    @Query(value = "select series_id from (Views v join RegularComic rc on v.comic_id = rc.regular_comic_id) where series_id IS NOT NULL Group by series_id ORDER BY count(series_id) DESC LIMIT 20", nativeQuery = true)
    List<Integer> getSeriesIdsOfMostViewedSeries();

    @Query(value = "SELECT * from Views v where v.comic_id = :comic_id", nativeQuery = true)
    List<Views> getAllViewsInComic(@Param("comic_id") Integer comic_id);
}