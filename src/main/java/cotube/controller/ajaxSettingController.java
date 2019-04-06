package cotube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import org.json.*;

@Controller
@RequestMapping(value="/setting.html")
public class ajaxSettingController{

    @RequestMapping(value="/resetPassword",method = RequestMethod.POST)
    @ResponseBody
    public Boolean validateResetPassword(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("new_password");
        System.out.println(username);
        System.out.println(password);
        System.out.println(newPassword);

        return true;
    }

    @RequestMapping(value="/resetQuestion",method = RequestMethod.POST)
    @ResponseBody
    public Boolean validateResetQuestion(HttpServletRequest request){
        String username = request.getParameter("username");
        String originalQuestion = request.getParameter("original_question");
        String originalAnswer = request.getParameter("original_answer");
        String newQuestion = request.getParameter("new_question");
        String newAnswer = request.getParameter("new_answer");

        System.out.println(username);
        System.out.println(originalQuestion);
        System.out.println(originalAnswer);
        System.out.println(newQuestion);
        System.out.println(newAnswer);

        return true;
    }


}
