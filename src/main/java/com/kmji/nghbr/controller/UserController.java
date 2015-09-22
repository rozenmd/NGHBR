package com.kmji.nghbr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kmji.nghbr.model.Postcode_db;
import com.kmji.nghbr.service.PostcodeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.annotation.Autowired;

import com.kmji.nghbr.model.User;
import com.kmji.nghbr.service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Controller
public class UserController extends AbstractController {

    @Autowired
    UserService userService;
    @Autowired
    PostcodeService postcodeService;

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
    public ModelAndView profilePage(){
        ModelAndView model = new ModelAndView("user/profile");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            User user = userService.findBySso(getPrincipal());
            model.addObject("user", user);
            //get postcode row value
            try{
                if(user.getSuburb().length() > 0 && user.getPostcode() > 0){
                    Postcode_db postcode = postcodeService.findByPostcodeSuburb(
                            user.getPostcode(),
                            user.getSuburb()
                    );
                    model.addObject("lat", postcode.getLat());
                    model.addObject("lon", postcode.getLon());
                }else if (user.getPostcode() > 0){
                    Postcode_db postcode = postcodeService.findByPostcode(user.getPostcode());
                    model.addObject("lat", postcode.getLat());
                    model.addObject("lon", postcode.getLon());
                } else if (user.getPostcode() < 0){
                    Postcode_db postcode = postcodeService.findBySuburb(user.getSuburb());
                    model.addObject("lat", postcode.getLat());
                    model.addObject("lon", postcode.getLon());
                }
            }catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        }
        return model;

    }
    @RequestMapping(value = "/user/update", method = RequestMethod.GET)
    public ModelAndView updateProfile() {
        ModelAndView model = new ModelAndView("user/update");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            User user = userService.findBySso(getPrincipal());
            model.addObject("user", user);
        }
        return model;
    }
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public ModelAndView updateProfilePOST(HttpServletRequest request){
        //int postcode=-1;
        ModelAndView model = new ModelAndView("/user/update");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        //try parse a postcode to int
        try{
            int postcode = Integer.parseInt(request.getParameter("postcode"));
            String suburb = request.getParameter("suburb");
            String email = request.getParameter("email");
            User user = userService.findBySso(getPrincipal());

            System.out.println(user.toString());

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPostcode(postcode);
            user.setSuburb(suburb);
            user.setEmail(email);
            userService.save(user);
            return new ModelAndView("redirect:../profile");
        }catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        return model;

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
