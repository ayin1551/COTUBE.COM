package cotube.repositories;

import cotube.domain.FollowSeries;
import cotube.domain.RegularComic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowSeriesRepository extends CrudRepository<FollowSeries, Integer> {
    @Query(value = "SELECT * from FollowSeries fs where fs.series_id = :series_id", nativeQuery = true)
    List<FollowSeries> getAllFollowSeriesInSeries(@Param("series_id") Integer series_id);
}