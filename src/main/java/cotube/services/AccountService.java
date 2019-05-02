package cotube.services;

import cotube.domain.Account;
import java.util.List;

public interface AccountService {
    Account addAccount(Account account); //add account to db *C
    List<Account> getAllAccounts(); //get all accounts in db *R
    Account getAccountByUsername(String username);
    boolean usernameExist(String username);
}