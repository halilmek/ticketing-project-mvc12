package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.RoleService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final ProjectService projectService;
    private final UserService userService;
    private final RoleService roleService;
    private final TaskService taskService;

    public TaskController(ProjectService projectService, UserService userService, RoleService roleService, TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.roleService = roleService;
        this.taskService = taskService;
    }

    @GetMapping("/create")
    public String taskPage (Model model) {

//Attributes => Empty Task obje
        model.addAttribute("emptyTask", new TaskDTO());

//Passing employees =>
        model.addAttribute("allEmployees", userService.allEmployees());

//Passing projects
        model.addAttribute("allProjects", projectService.findAll());

//Passing TASKs
        model.addAttribute("allTasks", taskService.findAll());

        taskService.findAll().stream()
                .forEach(System.out::println);

        return "/task/create";
    }

//Save button a islev kazandirmak ve java side saving
    @PostMapping("/create")
    public String newTaskSaving (@Valid @ModelAttribute("emptyTask") TaskDTO taskDTO,
                                 BindingResult bindingResult, Model model) {

//Hersey bittikten sonra validation !!!
        if (bindingResult.hasErrors()) {

            model.addAttribute("allProjects", projectService.findAll());
            model.addAttribute("allEmployees", userService.allEmployees());
            model.addAttribute("allTasks", taskService.findAll());

            return "/task/create";

        }

//önceki method da user verilen bos form dan setter ile girilen bilgileri almak
//icin @ModelAttribute u kullanalim. Ve save method ile save edelim
        taskService.save(taskDTO);

        System.out.println("taskDTO = " + taskDTO);

//Bu islem biraz uzun sürdü => 1. Task status, 2. Date, 3.Task id icin
//save() method a 3 tane ayri kosul eklendi.

        return "redirect:/task/create";
    }


//Delete button a isleve vermek.
    @GetMapping("/delete/{taskId}")
    public String deleteTask (@PathVariable("taskId") Long id) {

//Deleting task with an id
        taskService.deleteById(id);

        taskService.findAll().stream()
                .forEach(System.out::println);
        System.out.println("\"=================\" = " + "=================");
        return "redirect:/task/create";
    }

//Updating of tasks !!!
    @GetMapping("/update/{taskId}")
    public String updateTask (@PathVariable("taskId") Long id, Model model) {

//Normal page gibi herseyi getirecegiz. Tek fark olarak update icin secilen
//task i alip form u bununla dolduracagiz, empty task yerine !!! Özellikle
//update ederken @GetMapping() de ilk page redirect etmiyoruz, cünkü redirect
//yapilan page form empty task ile dolduruluyor.

        model.addAttribute("updatedTask", taskService.findById(id));
    //task/update.html deki attribute name i degistirelim. Cünkü copy / paste ile
    //herseyi /task/create.html den aldik.

//Diger attributes lar normal getiriliyor.
        //Passing employees =>
        model.addAttribute("allEmployees", userService.allEmployees());

//Passing projects
        model.addAttribute("allProjects", projectService.findAll());

//Passing TASKs
        model.addAttribute("allTasks", taskService.findAll());

        taskService.findAll().stream()
                .forEach(System.out::println);

        System.out.println("\"==========================\" = " + "==========================");


        return "/task/update";
    }


//Simdi bu degisiklikleri post method ile View den alip, update i yapalim ve
//redirect ile task/create e dönelim. Cünkü update islemi bitmis olacak.
/*
    @PostMapping ("/update/{taskId}")
    public String updateTask (@PathVariable("taskId") Long id, @ModelAttribute("updatedTask") TaskDTO taskDTO) {

        System.out.println("\"======================\" = " + "======================");
        taskDTO.setId(id);// => Biz TaskDTO da obje ye ID assig etmedigimiz icin hata
//aliyorduk. @GetMapping de ya da @PostMapping de taskId yi kullanmak istiyoruz ama
//obje de id yoktu.

        //Burada farkli bir senaryo var. Bundan dolayi id yi alip, kendimiz assign etmemiz gerekiyor.
        //System.out.println("id = " + id);
        taskService.update(taskDTO);
        System.out.println("taskDTO = " + taskDTO);
        System.out.println("\"======================\" = " + "======================");

        return "redirect:/task/create";
    }

 */

//PostMapping in farkli bir kullanimi => Spring @PathVariable annotation
//kullanmadan dahi end point teki parametreyi algilamasi. Ancak bunun
//icin parametre olarak end point de belirtilen variable ilgili objenin
//field / instance i olacak. Yani id olacak taskId degil. Cünkü TaskDTO da
//id, private Long id; olarak tanimlandi.
    @PostMapping("/update/{id}")
    public String updateTask (@Valid @ModelAttribute("updatedTask") TaskDTO taskDTO,
                              BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("allProjects", projectService.findAll());
            model.addAttribute("allEmployees", userService.allEmployees());
            model.addAttribute("allTasks", taskService.findAll());

            return "/task/update";
        }

//update() method has problem, not working !!!
        taskService.update(taskDTO);
        return "redirect:/task/create";
    }


}
