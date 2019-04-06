package cotube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import org.json.*;

@Controller
@RequestMapping(value="/login.html")
public class ajaxLoginController{

    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public Boolean validateLogin(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        return true;
    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    @ResponseBody
    public Boolean validateRegister(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String security_question = request.getParameter("security_question");
        String security_answer = request.getParameter("security_answer");
        System.out.println(username);
        System.out.println(password);
        System.out.println(security_question);
        System.out.println(security_answer);
        return true;
    }

    @RequestMapping(value="/reset",method = RequestMethod.POST)
    @ResponseBody
    public Boolean validateReset(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String security_question = request.getParameter("security_question");
        String security_answer = request.getParameter("security_answer");
        System.out.println(username);
        System.out.println(password);
        System.out.println(security_question);
        System.out.println(security_answer);
        return true;
    }
}