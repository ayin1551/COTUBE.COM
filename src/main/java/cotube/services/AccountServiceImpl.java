package cotube.services;

import cotube.domain.Account;
import cotube.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    public void setProductRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {

        List<Account> accounts = (List<Account>) accountRepository.findAll();

        return accounts;
    }



}