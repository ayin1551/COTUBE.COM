package cotube.repositories;

import cotube.domain.Comments;
import org.springframework.data.repository.CrudRepository;

public interface CommentsRepository extends CrudRepository<Comments, Integer> {

}