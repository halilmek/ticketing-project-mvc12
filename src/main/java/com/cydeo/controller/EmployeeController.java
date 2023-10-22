package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    ProjectService projectService;
    TaskService taskService;

    public EmployeeController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/pending-tasks")
    public String pendingTasks (Model model) {

        model.addAttribute("nonCompletedTasks",
                taskService.findAllTasksByStatusIsNotComplete(Status.COMPLETE));
//Parametre olarak Status.COMPLETE olsa bile stream de ! ile complete olmayanlari
//getir diyorum.
        return "/task/pending-tasks";
    }

    @GetMapping("/update/{taskId}")
    public String taskUpdate (@PathVariable("taskId") Long taskId, Model model) {

//findById ile task i buluyoruz, ve onu UI da form kisminda ekliyor olacagiz.
        model.addAttribute("updatedTask", taskService.findById(taskId));

//System.out.println("taskService.findById(taskId) = " + taskService.findById(taskId));

//status-update.html page de tekrardan nonCompleted task lari view de call edecegiz.
        model.addAttribute("nonCompletedTasks",
                taskService.findAllTasksByStatusIsNotComplete(Status.COMPLETE));

//Task status lari drop down listesi ile verecegiz. Enum oldugu icin bunlari da obje gibi inject ediyoruz.
        model.addAttribute("allStatus", Status.values());
//Status.values() => return array => bundan dolayi list gibi alip kullanabiliyorum.
        return "/task/status-update";
    }

    @PostMapping("/taskEdited/{taskId}")
    public String savingEditedTask (@Valid @ModelAttribute("updatedTask") TaskDTO taskDTO, BindingResult bindingResult,
                                    Model model, @PathVariable("taskId") Long taskId) {

        System.out.println("taskService.findById(taskId) = " + taskService.findById(taskId));

        //System.out.println("taskDTO = " + taskDTO);

        if (bindingResult.hasErrors()) {

            model.addAttribute("nonCompletedTasks",
                    taskService.findAllTasksByStatusIsNotComplete(Status.COMPLETE));
            model.addAttribute("allStatus", Status.values());
//Status.values() => return array => bundan dolayi list gibi alip kullanabiliyorum.

            return "/task/status-update";
        }

        taskDTO.setId(taskService.findById(taskId).getId());
        taskDTO.setTaskDetail(taskService.findById(taskId).getTaskDetail());
        taskDTO.setAssignedDate(taskService.findById(taskId).getAssignedDate());
        System.out.println("taskDTO = " + taskDTO);

        taskService.updateStatus(taskDTO);
        //System.out.println("taskDTO = " + taskDTO);
        //taskService.save(taskDTO); //HashMap put() ile listeme koyacak, ancak HashMap
//duplication a izin vermedigi icin ayni id si olan eski obje kick off yapacak ;=)

        return "redirect:/employee/pending-tasks";//end point yaziliyor redirect ile

    }


//Archieve link and page loading...
    @GetMapping("/archive")
    public String taskArchive (Model model) {

        model.addAttribute("completedTasks", taskService.findAllTasksByStatus());
        return "/task/archive";
    }
}
