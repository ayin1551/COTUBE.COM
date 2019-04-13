package cotube.repositories;

import cotube.domain.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    //public List<Account> findByNameContainingIgnoreCase(String name);


}