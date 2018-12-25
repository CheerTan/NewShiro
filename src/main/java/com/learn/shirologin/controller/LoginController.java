package com.learn.shirologin.controller;

import com.learn.shirologin.configuration.domain.ResponseBo;
import com.learn.shirologin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/gologin")
    public String gologin(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login")

    public String login(String username, String password, Model model) { //String vcode, Boolean rememberMe
        System.out.println("-----> get in!");
        if (username == null || password == null) {
            System.out.println("empty");
            //return ResponseBo.warn("验证码不能为空！");
            return "login";
        }
        try {
            System.out.println("---->i am logining!");
            userService.dologin(username, password);
            System.out.println("----->finish");
            //return ResponseBo.ok();

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            System.out.println("Ouch!login is fail!");
            //return ResponseBo.error("认证失败！");
            return "login.html";
        }
        return "redirect:/user";
    }

    @RequestMapping(value = "/loginout")
    public String loginout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/gologin";
    }
    @RequestMapping(value = "/user")
    public String user(){return "main.html";}
    @RequestMapping(value = "/index")
    public String index(){return "fortest.html";}
    @RequestMapping(value = "/top")
    public String top(){return "top.html";}
    @RequestMapping(value = "/main_left")
    public String main_left(){return "main_left.html";}
    @RequestMapping(value = "/main_right")
    public String main_right(){return "main_right.html";}
    @RequestMapping(value = "/devicelist")
    public String devicelist(){return "devicelist.html";}

}
