package cotube.controller;

import cotube.domain.Account;
import cotube.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value="/login.html")
public class ajaxLoginController{

    private AccountService accountService;
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }


    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public Boolean validateLogin(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean valid = this.validLogin(username,password);
        if (valid == true){
            return true;
        }
        return false;
    }

    public boolean validLogin(String username, String password){
        List<Account> allAccounts = accountService.getAllAccounts();
        for(Account acc: allAccounts){
            if (acc.getUsername().equals(username) && acc.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    @ResponseBody
    public Boolean validateRegister(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Integer security_question = Integer.parseInt(request.getParameter("security_question"));
        String security_answer = request.getParameter("security_answer");

        List<Account> accounts = this.accountService.getAllAccounts();
        for (Account acc: accounts){
            if(acc.getUsername().equals(username)) {
                return false;
            }
        }
        Account n = new Account();
        n.setUsername(username);
        n.setPassword(password);
        n.setSecurity_question(security_question);
        n.setSecurity_answer(security_answer);
        n.setProfile_pic_path("./img/profile.png");
        n.setAccount_role(0);
        this.accountService.addAccount(n);
        return true;
    }

    @RequestMapping(value="/reset",method = RequestMethod.POST)
    @ResponseBody
    public Boolean validateReset(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Integer security_question = Integer.parseInt(request.getParameter("security_question"));
        String security_answer = request.getParameter("security_answer");
        List<Account> accounts = this.accountService.getAllAccounts();
        for (Account acc: accounts) {
            if (acc.getUsername().equals(username)) {
                if (security_question.equals(acc.getSecurity_question())) {
                    if (security_answer.equals(acc.getSecurity_answer())) {
                        acc.setPassword(password);
                        this.accountService.addAccount(acc);
                        System.out.println("SAVED");
                        return true;
                    }
                }
            }
        }
        return false;
    }
    @RequestMapping(value="/checkAdmin",method = RequestMethod.POST)
    @ResponseBody
    public Boolean isAdmin(HttpServletRequest request) {
        String username = request.getParameter("username");
        Account check = this.accountService.getAccountByUsername(username);
        if (check.getAccount_role() == 1)
            return true;
        return false;
    }

}