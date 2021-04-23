package tech.codingclub.helix.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tech.codingclub.helix.database.GenericDB;
import tech.codingclub.helix.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

/**
 * User: rishabh
 */
@Controller
@RequestMapping("/")
public class MainController extends BaseController {

    private static Logger logger = Logger.getLogger(MainController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/helloworld")
    public String getQuiz(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) {
        return "hello";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/jetty")
    public String getJetty(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) {
        return "jetty";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/time")
    public @ResponseBody String getTime(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) {
        TimeApi time = new TimeApi(new Date().toString(), new Date().getTime());
        return new Gson().toJson(time);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/wiki/text")
    public @ResponseBody String getWikiResultTEXT(ModelMap modelMap, @RequestParam("keyword") String keyword ,HttpServletResponse response, HttpServletRequest request) {
        WikiDownloader wikiDownloader = new WikiDownloader(keyword);
        WikiResult wikiResult = wikiDownloader.getResult();
        return new Gson().toJson(wikiResult);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/wiki/html")
    public String getWikiResultHTML(ModelMap modelMap, @RequestParam("keyword") String keyword ,HttpServletResponse response, HttpServletRequest request) {
        WikiDownloader wikiDownloader = new WikiDownloader(keyword);
        WikiResult wikiResult = wikiDownloader.getResult();
        //pass attributes to wikiHTML
        modelMap.addAttribute("IMAGE", wikiResult.getImageURL());
        modelMap.addAttribute("DESCRIPTION", wikiResult.getTextResult());
        return "wikiapi";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/signup")
    public String signup(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        return "signup";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public @ResponseBody SignUpResponse signUpData(@RequestBody Member member, HttpServletRequest request, HttpServletResponse response) {

        boolean user_created = false;
        String message = "";
        if(GenericDB.getCount(tech.codingclub.helix.tables.Member.MEMBER, tech.codingclub.helix.tables.Member.MEMBER.EMAIL.eq(member.email)) > 0){
            message = "User already exists!";
        }else{
            member.role = "cm";
            new GenericDB<Member>().addRow(tech.codingclub.helix.tables.Member.MEMBER, member);
            user_created = true;
            message = "User registered successfully!";
        }

        return new SignUpResponse(message, user_created);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/welcome")
    public String welcome(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        Member member = ControllerUtils.getCurrentMember(request);
        modelMap.addAttribute("NAME", member.name);
        modelMap.addAttribute("MEMBER",member);
        return "welcome";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/handle")
    public
    @ResponseBody
    String handleEncrypt(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        return "ok";
    }
}