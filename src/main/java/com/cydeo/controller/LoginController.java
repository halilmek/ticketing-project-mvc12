package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping(value = {"/login", "/"})
//http://localhost:8080 => ya da => http://localhost:8080/login
//ile login page e gidebiliyorum. Coklu option verilmis list / array icinde.
    public String login () {

        return "login";
    }

    @RequestMapping("/welcome")
    public String welcome () {

        return "welcome";
    }
}
