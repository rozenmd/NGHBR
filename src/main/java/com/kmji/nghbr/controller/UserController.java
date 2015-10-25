package com.kmji.nghbr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kmji.nghbr.model.Suburb;
import com.kmji.nghbr.service.SuburbService;
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
    SuburbService suburbService;

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
                if(user.getSuburb().getSuburbName().length() > 0 && user.getSuburb().getPostcode() > 0){

                    Suburb suburb = suburbService.findByPostcodeSuburb(
                            user.getSuburb().getPostcode(),
                            user.getSuburb().getSuburbName()
                    );
                    model.addObject("lat", suburb.getLat());
                    model.addObject("lon", suburb.getLon());
                }else if (user.getSuburb().getPostcode() > 0){
                	//Will just pick first suburb in list...
                	Suburb suburb = suburbService.findByPostcode(user.getSuburb().getPostcode()).get(0);
                    model.addObject("lat", suburb.getLat());
                    model.addObject("lon", suburb.getLon());
                } else if (user.getSuburb().getPostcode() < 0){

                	Suburb suburb = suburbService.findBySuburb(user.getSuburb().getSuburbName());
                    model.addObject("lat", suburb.getLat());
                    model.addObject("lon", suburb.getLon());
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
            Suburb suburb = null;
            if(request.getParameter("suburb").length() > 0 && postcode > 0){
                 suburb = suburbService.findByPostcodeSuburb(
                         postcode,
                        request.getParameter("suburb")
                );
            }else if (postcode > 0){
                 suburb = suburbService.findByPostcode(postcode).get(0);
            } else if (!(postcode > 0) ){
                 suburb = suburbService.findBySuburb(request.getParameter("suburb"));
            }
            System.out.println("***** " + suburb.getSuburbName() + " " + suburb.getPostcode());
            String email = request.getParameter("email");
            User user = userService.findBySso(getPrincipal());

            System.out.println(user.toString());

            user.setFirstName(firstName);
            user.setLastName(lastName);
            if(suburb != null){
                user.setSuburb(suburb);
                System.out.println("2nd one *** " + suburb.getId() + " " + suburb.getSuburbName() + " " + suburb.getPostcode());
            }

            //suburbService.save()
            System.out.println(user.getSuburb().toString());
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
