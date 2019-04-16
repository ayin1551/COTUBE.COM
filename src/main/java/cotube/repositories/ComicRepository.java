package cotube.repositories;

import cotube.domain.Comic;
import org.springframework.data.repository.CrudRepository;

public interface ComicRepository extends CrudRepository<Comic, Integer> {

}