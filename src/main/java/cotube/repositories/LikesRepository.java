package cotube.repositories;

import cotube.domain.Likes;
import org.springframework.data.repository.CrudRepository;

public interface LikesRepository extends CrudRepository<Likes, Integer> {

}