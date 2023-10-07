package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/create")//Bu end point ile .html file (return edilen)
//ayni olmak zorunda degil. Raslanti diyelim.
    public String createUser (Model model) {

        model.addAttribute("newUser", new UserDTO());

        model.addAttribute("roles", );
        return "/user/create";
    }
}
