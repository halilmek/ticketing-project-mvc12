package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        //model.addAttribute("allUsers", userService.findAll());

//Simdi bunun yerine sadece manager lari getirelim.
        model.addAttribute("allUsers", userService.allManagers());

        return "/project/create";
    }

//Empty obje view de doldorulup save button a basilinca bu method work edecek
//ben de onu @ModelAttribute ile local degisken olan newProject üzerinden alip
//map ime save edecegim.
    @PostMapping("/create")
    public String addingProject (@ModelAttribute ("newProject") ProjectDTO projectDTO) {

//Redirect edecegimiz icin burada Model interface i call etmedik.

        //projectDTO.setProjectStatus(Status.OPEN); //Böyle de status assign edebiliriz.
//Ama bu dynamic bir yöntem degil. Bunlari Service class larinda yapmak daha mantikli.
        projectService.save(projectDTO);

        projectService.findAll().stream()
                .forEach(System.out::println);

        return "redirect:/project/create";
    }

//Delete button icin method
    @GetMapping("/delete/{projectCode}")
    public String deleteProject (@PathVariable("projectCode") String projectCode) {

        projectService.deleteById(projectCode);

        return "redirect:/project/create";
    }

//Complete button a eksin kazandiralim !!!
    @GetMapping("/complete/{projectCode}")
    public String completeProject (@PathVariable ("projectCode") String projectCode) {

//Bunun icin Service interface de yeni bir method create ediyoruz.
//Aslinda findById() methodu ile project i bulup status unu complete
//yapabilirdik. Ama bunu tercih etmiyoruz.

        projectService.complete(projectService.findById(projectCode));
//complete() methodu parametre olarak obje istedigi icin, findById() methodu ile obje
//return ediyoruz project code parameter olarak vererek.
        return "redirect:/project/create";
    }


//Simdi ise update icin GetMapping ve PostMapping ile 2 ayri method lar create edelim.
    @GetMapping("/update/{projectCode}")
    public String updateProject (@PathVariable ("projectCode") String projectCode, Model model) {

//Attributes => Kimler gelecek => normal create.html den tek farki empty project yerine
//fill edilmis, update icin secilen project in bilgileri gelecek.

        //form u fill edilmesi icin update icin secilen projet
        model.addAttribute("newProject", projectService.findById(projectCode));

        //Tüm project leri getirelim.
        model.addAttribute("allProjects", projectService.findAll());

        //tüm manager lar
        model.addAttribute("allUsers", userService.allManagers());

        return "/project/update";
    }

//Simdi update controller method unun database etkili olabilmesi icin PostMapping
    @PostMapping("/update")
    public String pushingUpdateInDB (@ModelAttribute("newProject") ProjectDTO projectDTO) {

//Degisiklikleri yapilan ve önceki method da local variable olan projeyi @ModelAttribute
//ile burada call ediyoruz ve save methodu üzerinden DB ye kaydediyoruz.
        projectService.update(projectDTO);

        return "redirect:/project/create";
    }
}
