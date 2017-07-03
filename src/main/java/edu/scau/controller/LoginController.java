package edu.scau.controller;

import edu.scau.model.User;
import edu.scau.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shoulder on 2016/7/2.
 */
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    //返回书城首页
    @RequestMapping(path = {"/"}, method = {RequestMethod.GET})
    public String Index(Model model) {
        return "index";
    }


//    @RequestMapping(path = {"/login.action"}, method = {RequestMethod.POST})
//    public String login(Model model,  @RequestParam("username") String username,
//                        @RequestParam("password") String password)) {
//        return "login";
//    }

    //用户点击注册
    @RequestMapping(path = {"/register.action"}, method = {RequestMethod.POST})
    public String reg(Model model, @RequestParam("username") String username,
                      @RequestParam("password") String password) {
        //@RequestParam(value="rememberme", defaultValue = "false") boolean rememberme,
        // HttpServletResponse response
        try {
            /*Service层返回的Map集合中若带有错误提示msg，则停留在当前注册登录页
              否则说明注册成功，重定向到首页
             */
            Map<String, String> map = userService.register(username, password);
            if (map.containsKey("msg")) {
                model.addAttribute("msg", map.get("msg"));
                return "register";
            }
            return "redirect:/";
        } catch (Exception e) {
            logger.error("注册异常" + e.getMessage());
            model.addAttribute("msg", "服务器错误");
            return "register";
        }
    }


    //用户点击登录
    @RequestMapping(path = {"/login.action"}, method = {RequestMethod.POST})
    public String login(Model model, @RequestParam("username") String username,
                        @RequestParam("password") String password) {
        //@RequestParam(value = "rememberme", defaultValue = "false") boolean rememberme,
        //HttpServletResponse response
        try {
            /*Service层返回的Map集合中若带有错误提示msg，则停留在当前注册登录页
              否则说明用户名密码验证成功，重定向到首页
             */
            Map<String, String> map = userService.login(username, password);
            if (map.containsKey("msg")) {
                model.addAttribute("msg", map.get("msg"));
                return "login";
            }
            return "redirect:/";
        } catch (Exception e) {
            logger.error("登陆异常" + e.getMessage());
            return "login";
        }
    }


}
