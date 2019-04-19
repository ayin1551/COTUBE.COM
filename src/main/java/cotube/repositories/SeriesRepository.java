package cotube.repositories;

import cotube.domain.Comic;
import cotube.domain.Series;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SeriesRepository extends CrudRepository<Series, Integer> {

    @Query(value = "SELECT * from Series s where s.series_id = :series_id", nativeQuery = true)
    Series getSeriesBySeries_id(@Param("series_id") Integer series_id);


}