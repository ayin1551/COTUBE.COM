package cotube.controller;

import cotube.domain.Account;
import cotube.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/setting.html")
public class ajaxSettingController{

    private AccountService accountService;
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value="/resetPassword",method = RequestMethod.POST)
    @ResponseBody
    public Boolean validateResetPassword(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("new_password");
        Account current = this.accountService.getAccountByUsername(username);
        if(current.getPassword().equals(password)){
            current.setPassword(newPassword);
            this.accountService.addAccount(current);
            return true;
        }
        return false;
    }

    @RequestMapping(value="/resetQuestion",method = RequestMethod.POST)
    @ResponseBody
    public Boolean validateResetQuestion(HttpServletRequest request){
        String username = request.getParameter("username");
        Integer originalQuestion = Integer.parseInt(request.getParameter("original_question"));
        String originalAnswer = request.getParameter("original_answer");
        Integer newQuestion = Integer.parseInt(request.getParameter("new_question"));
        String newAnswer = request.getParameter("new_answer");

        Account current = this.accountService.getAccountByUsername(username);
        if(current.getSecurity_question().equals(originalQuestion)){
            if(current.getSecurity_answer().equals(originalAnswer)){
                current.setSecurity_question(newQuestion);
                current.setSecurity_answer(newAnswer);
                this.accountService.addAccount(current);
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value="/changeProfile",method = RequestMethod.POST)
    @ResponseBody
    public Boolean setProfilePicture(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("new_password");
        Account current = this.accountService.getAccountByUsername(username);
        if(current.getPassword().equals(password)){
            current.setPassword(newPassword);
            this.accountService.addAccount(current);
            return true;
        }
        return false;
    }
}
