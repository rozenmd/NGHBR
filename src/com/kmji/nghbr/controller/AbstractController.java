package com.kmji.nghbr.controller;

import com.kmji.nghbr.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public abstract class AbstractController {
    protected String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else if (principal instanceof User) {
            userName = ((User) principal).getSsoId();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}

