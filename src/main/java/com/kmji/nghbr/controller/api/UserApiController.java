package com.kmji.nghbr.controller.api;

/**
 * Created by khanh on 20/09/15.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kmji.nghbr.controller.AbstractController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.kmji.nghbr.model.User;
import com.kmji.nghbr.service.UserService;

@Controller
public class UserApiController extends AbstractController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/api/users/authenticate", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public User authenticate(@RequestBody User requestUser, HttpServletRequest request) {

        System.out.println(requestUser.toString());

        User user = userService.findByFacebookId(requestUser.getFacebookId());

        System.out.println(user);

        if (user == null) {
            userService.save(requestUser);
            user = requestUser;
        }

        Authentication authentication =  new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return user;
    }
}
