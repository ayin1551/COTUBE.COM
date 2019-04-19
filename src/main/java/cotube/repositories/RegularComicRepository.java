package cotube.repositories;

import cotube.domain.Comic;
import cotube.domain.RegularComic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegularComicRepository extends CrudRepository<RegularComic, Integer> {

    @Query(value = "SELECT * from RegularComic rc where rc.regular_comic_id = :regular_comic_id", nativeQuery = true)
    RegularComic getRegularComicByRegular_Comic_Id(@Param("regular_comic_id") Integer regular_comic_id);

    @Query(value = "SELECT * from RegularComic rc where rc.series_id = :series_id", nativeQuery = true)
    List<RegularComic> getAllRegularComicsInSeries(@Param("series_id") Integer series_id);

}