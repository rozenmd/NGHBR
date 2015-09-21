package com.kmji.nghbr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;

import com.kmji.nghbr.model.User;
import com.kmji.nghbr.service.UserService;

@Controller
public class UserController extends AbstractController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        model.addAttribute("users", userService.findAllUsers());
        return "user/admin";
    }

    @RequestMapping(value = "/db", method = RequestMethod.GET)
    public String dbaPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "user/dba";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {

        if (getPrincipal() != "anonymousUser") {
            return "redirect:/";
        } else {
            return "user/login";
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profilePage(ModelMap model) {
        try{

        }catch(Exception e){
            //something something
        }
        return "user/profile";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(ModelMap model) {
        // create a test user
        try {
            User user = new User();
            user.setFirstName("test");
            user.setLastName("user");
            user.setSsoId("test");
            user.setEmail("test@example.com");
            user.setPassword("Pass.123");
            userService.save(user);
            model.addAttribute("user", user);
        } catch(Exception e) {
            model.addAttribute("user", userService.findBySso("test"));
        }

        model.addAttribute("user", getPrincipal());
        return "user/register";
    }


}
