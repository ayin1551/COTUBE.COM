package cotube.repositories;

import cotube.domain.Favorite;
import org.springframework.data.repository.CrudRepository;

public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {

}