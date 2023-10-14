package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final UserService userService;
    private final RoleService roleService;
    private final ProjectService projectService;

    public ProjectController(UserService userService, RoleService roleService, ProjectService projectService) {
        this.userService = userService;
        this.roleService = roleService;
        this.projectService = projectService;
    }


    @GetMapping("/create")
    public String projectCreate (Model model) {

        //What attributes do i need to pass?
        model.addAttribute("newProject", new ProjectDTO());
        model.addAttribute("allProjects", projectService.findAll());

//Burada simdilik tüm user lari aliyorum. Ama bana view de sadece manager lar lazim !!!
        model.addAttribute("allUsers", userService.findAll());

        return "/project/create";
    }

//Empty obje view de doldorulup save button a basilinca bu method work edecek
//ben de onu @ModelAttribute ile local degisken olan newProject üzerinden alip
//map ime save edecegim.
    @PostMapping("/create")
    public String addingProject (@ModelAttribute ("newProject") ProjectDTO projectDTO) {

//Redirect edecegimiz icin burada Model interface i call etmedik.

        projectService.save(projectDTO);

        return "redirect:/project/create";
    }
}
