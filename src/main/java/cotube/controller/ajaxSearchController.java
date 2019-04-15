package cotube.controller;

import cotube.domain.Account;
import cotube.services.AccountService;
import cotube.services.FollowUserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/searchResult.html")
public class ajaxSearchController{

    private FollowUserService followUserService;
    @Autowired
    public void setFollowUserService(FollowUserService followUserService) {
        this.followUserService = followUserService;
    }

    private AccountService accountService;
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value="/author",method = RequestMethod.POST)
    @ResponseBody
    public String searchByAuthor(HttpServletRequest request){
        String author = request.getParameter("author");
        List<Account> accounts = accountService.getAllAccounts();

        List<Integer> followerCount = new ArrayList<Integer>();
        List<String> matches = new ArrayList<String>();
        for(Account acc: accounts){
            if (acc.getUsername().contains(author)){
                followerCount.add(followUserService.getFollowerCount(acc.getUsername()));
                matches.add(acc.getUsername());
            }
        }
        JSONObject result = new JSONObject();
        result.put("account", matches);
        result.put("followers", followerCount);
        System.out.println(result.toString());
        return result.toString();
    }

/*    @RequestMapping(value="/keyword",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject searchByKeyword(HttpServletRequest request){
        String keyword = request.getParameter("keyword");
        List<Account> accounts = accountService.searchAccountContainingUsername(author);
        List<Integer> followerCount = new ArrayList<Integer>();
        for(int i = 0; i < accounts.size(); i++){
            followerCount.add(followUserService.getFollowerCount(accounts.get(i).getUsername()));
        }
        JSONObject result = new JSONObject();
        result.put("account", accounts);
        result.put("followers", followerCount);
        return result;
    }
*/
    @RequestMapping(value="/check",method = RequestMethod.POST)
    @ResponseBody
    public Boolean check(HttpServletRequest request){
        String username = request.getParameter("username");
        String following = request.getParameter("following");
        System.out.println(username);
        System.out.println(following);


        return true;
    }

}
