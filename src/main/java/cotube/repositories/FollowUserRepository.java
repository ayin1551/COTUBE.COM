package cotube.repositories;

import cotube.domain.FollowUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FollowUserRepository extends CrudRepository<FollowUser, Integer> {

    @Query(value = "SELECT COUNT(following_username) from followuser a where a.following_username = :following_username", nativeQuery = true)
    Integer getFollowerCount(@Param("following_username") String following_username);

}