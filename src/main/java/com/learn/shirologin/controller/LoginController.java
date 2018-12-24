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
        if (username == null || password == null) {
            //return ResponseBo.warn("验证码不能为空！");
            return "login";
        }
        try {
            userService.dologin(username, password);
            //return ResponseBo.ok();

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            //return ResponseBo.error("认证失败！");
            return "login.html";
        }
        return "redirect:/index";
    }

    @RequestMapping(value = "/loginout")
    public String loginout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/gologin";
    }
    @RequestMapping(value = "user")
    public String user(){return "userpage.html";}
    @RequestMapping(value = "/index")
    public String index(){return "user.html";}


}
