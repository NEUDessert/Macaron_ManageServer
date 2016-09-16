package Controller;

import Service.IUserManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import Eneity.*;

/**
 * Created by killeryuan on 2016/9/10.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Resource(name="userManager")
    private IUserManager userManager;
    @RequestMapping("login")
    public void login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        crossDomainSetting(response, request);
        if(userManager.login(username, password)){
            out.println("{\"error\":\"0\"}");
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
        }else {
            out.println("{\"error\":\"1\"}");
        }
    }
    @RequestMapping("newlogin")
    public String  newlogin(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException{
        System.out.println(username +"++++"+password);
        PrintWriter out = response.getWriter();

        crossDomainSetting(response, request);
        if(userManager.login(username, password)){
            //out.println("{\"error\":\"0\"}");
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            request.setAttribute("result","{\"error\":\"0\"}");
        }else {
            out.println("{\"error\":\"1\"}");
            request.setAttribute("result","{\"error\":\"1\"}");
        }
        return "result";
    }

    @RequestMapping("getUsername")
    public void getUsername(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String username;
        PrintWriter out = response.getWriter();

        crossDomainSetting(response,request);
        if ((username = (String)(request.getSession(true).getAttribute("username")))== null) {
            out.println("{\"error\":\"1\",\"username\":\"\"}");
        }else{
            out.println("{\"error\":\"0\",\"username\":\""+username+"\"}");
        }
    }

    @RequestMapping("register")
    public void register(IntelUserInfoEntity userInfo, HttpServletRequest request,HttpServletResponse response) throws IOException{
        String result = userManager.register(userInfo);
        PrintWriter out = response.getWriter();

        crossDomainSetting(response,request);
        if(result.equals("0")) {
            HttpSession session = request.getSession(true);
            String username = userInfo.getUsername();
            session.setAttribute("username",username);
        }
        out.println("{\"error\":\""+result+"\"}");
    }

    @RequestMapping("getBasicInfo")
    public void getBasicInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();

        crossDomainSetting(response, request);

        String username = (String)(request.getSession(true).getAttribute("username"));
        System.out.println(username);
        HttpSession se = request.getSession(true);
        username = (String)se.getAttribute("username");
        String json = userManager.getBasicInfo(username);
        out.println(json);
    }

    @RequestMapping("getCurrentStatus")
    public void getCurrentStatus(HttpServletRequest request, HttpServletResponse response)throws JsonProcessingException , IOException{

        String username = (String)request.getSession(true).getAttribute("username");
        String json =userManager.getCurrentStatus(username);
        PrintWriter out = response.getWriter();
        crossDomainSetting(response, request);
        out.println(json);
    }

    @RequestMapping("getLogs")
    public void getLogs(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException,ParseException{
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        crossDomainSetting(response, request);
        String username = (String)request.getSession(true).getAttribute("username");
        out.println(userManager.getLogs(username));
    }
    @RequestMapping("logOperation")
    public void getLogs(HttpServletRequest request, HttpServletResponse response, String method, String alertId) throws IOException{
        PrintWriter out = response.getWriter();
        crossDomainSetting(response, request);
        if(userManager.logOperation(method, alertId)){
            out.println("{\"error\":\"0\"}");

        }else {
            out.println("{\"error\":\"1\"}");
        }
    }
    @RequestMapping("getDevices")
    public void getDevices(HttpServletRequest request, HttpServletResponse response)throws IOException,ParseException{
        PrintWriter out = response.getWriter();
        crossDomainSetting(response, request);
        String username = (String)request.getSession(true).getAttribute("username");
        out.println(userManager.getDevice(username));
    }
//    PrintWriter out = response.getWriter();
    @RequestMapping("addDevice")
    public void addDevice(String location, HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        crossDomainSetting(response, request);
        String username = (String)(request.getSession(true).getAttribute("username"));
        if(userManager.addDevice(location, username)){
            out.println("{\"error\":\"0\"}");
        }else {
            out.println("{\"error\":\"1\"}");
        }
    }
    @RequestMapping("destroyDevice")
    public void destroyDevice(String deviceName, HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        crossDomainSetting(response, request);
        String username = (String)(request.getSession(true).getAttribute("username"));
        if(userManager.destroyDevice(username, deviceName)){
            out.println("{\"error\":\"0\"}");
        }else {
            out.println("{\"error\":\"1\"}");
        }
    }
    /**
     * cross domain Setting
     */
    public void crossDomainSetting(HttpServletResponse response, HttpServletRequest request){
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods","POST");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
        response.setHeader("Access-Control-Allow-Credentials","true"); //�Ƿ�֧��cookie����

    }

}
