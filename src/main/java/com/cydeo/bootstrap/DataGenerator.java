package com.cydeo.bootstrap;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Role;
import com.cydeo.enums.Gender;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.RoleService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import com.cydeo.service.impl.RoleDTOServiceImpl;
import com.cydeo.service.impl.TaskServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataGenerator implements CommandLineRunner {
//CommandLineRunner, Whenever Spring runs, this class runs first.


    //RoleDTOServiceImpl roleDTOService;=> All injection THROUGH interface
//wiring icin implementation lara ve inject edilen yerlere @Component koymak gerekiyor.
    RoleService roleDTOService;
    UserService userDTOService;
    private final ProjectService projectService;
    private final TaskService taskService;


    public DataGenerator(RoleService roleDTOService, UserService userDTOService, ProjectService projectService, TaskService taskService) {
        this.roleDTOService = roleDTOService;
        this.userDTOService = userDTOService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public void run(String... args) throws Exception {

        RoleDTO adminRole = new RoleDTO(1l, "Admin");
        RoleDTO managerRole = new RoleDTO(2l, "Manager");
        RoleDTO employeeRole = new RoleDTO(3l, "Employee");

//How to add those roles into a map? => new Keyword means tight coupling
        //RoleDTOServiceImpl roleDTOService1 = new RoleDTOServiceImpl();

        roleDTOService.save(adminRole);
        roleDTOService.save(managerRole);
        roleDTOService.save(employeeRole);//Biz bunlari hangi map e save ettik?
//Nerede bu amk map i? Bunu interface ve abstract class üzerinden direk call
//edebiliyorum. Peki bu map hem roller (id ve description olarak) hem de user lari
//hold ediyor mu? Print edelim, ama neyi?
// Abstract class interface lerin implement i hakkinda bize blueprint veriyor.
// Buna göre roller icin ayri map (bunu implementation dan call edebilirim ama
// intreface üzerinden call etmem daha mantikli, belki sonra baska bir implementation
//da bu interface i kullanacak, bilmiyoruz) user lar icin ayri map abstract class
//create ediyor ve bunlari her implementation aktive ederek save methodunu calistiriyor.
//Diger methodlari da save() ile saklanan veriler üzerinden calistiriyor. Yani
// genel bir java class (String) ve methodu gibi (length() veya subString() gibi).-


        UserDTO user1 = new UserDTO("Halil", "Baba", "ztrkhll@gmail.com", "123456", "123456", true, "oppo", employeeRole, Gender.MALE);
        UserDTO user2 = new UserDTO("Hasan", "Kardes", "kardesHasan@gmail.com", "123456", "123456", false, "oppo", managerRole, Gender.FEMALE);
        UserDTO user3 = new UserDTO("Elif", "Ünsal", "unsalElif@gmail.com", "123456", "123456", true, "oppo", adminRole, Gender.MALE);
        UserDTO user4 = new UserDTO("Padisah", "Sultan", "sultanPadisah@gmail.com", "123456", "123456", true, "oppo", managerRole, Gender.FEMALE);
        UserDTO user5 = new UserDTO("Selim", "Han", "hanSelim@gmail.com", "123456", "123456", false, "oppo", adminRole, Gender.FEMALE);
        UserDTO user6 = new UserDTO("Saruhan", "Tulumpeyniri", "tulumpeyniri@gmail.com", "123456", "123456", false, "oppo", employeeRole, Gender.MALE);


        userDTOService.save(user1);
        userDTOService.save(user2);
        userDTOService.save(user3);
        userDTOService.save(user4);
        userDTOService.save(user5);
        userDTOService.save(user6);


        ProjectDTO project1 = new ProjectDTO("Hasan MVC project 1", "PR001", user2, LocalDate.now(), LocalDate.now(), "Halil Baba kafasina göre takiliyor!", Status.OPEN);
        ProjectDTO project2 = new ProjectDTO("Hasan ORM project 2", "PR002", user2, LocalDate.now(), LocalDate.now(), "ORRRRRR ARE M", Status.IN_PROGRESS);
        ProjectDTO project3 = new ProjectDTO("Padisah project 3", "PR003", user4, LocalDate.now(), LocalDate.now(), "CONTAINER!", Status.COMPLETE);
        ProjectDTO project4 = new ProjectDTO("Hasan - DI project 4", "PR004", user2, LocalDate.now(), LocalDate.now(), "Dependency Injection", Status.IN_PROGRESS);
        ProjectDTO project5 = new ProjectDTO("Padisah Inversion of Control - IoC project 5", "PR005", user4, LocalDate.now(), LocalDate.now(), "Inversion of Control!", Status.OPEN);
        ProjectDTO project6 = new ProjectDTO("Padisah keyfin keyf !!! project 6", "PR006", user4, LocalDate.now(), LocalDate.now(), "Wiring - Passing the data to view!!!", Status.COMPLETE);

        projectService.save(project1);
        projectService.save(project2);
        projectService.save(project3);
        projectService.save(project4);
        projectService.save(project5);
        projectService.save(project6);

//Yeni TaskDTO lar ekleyelim !!! Ama önce AllArgsConstructor annotation iptal
//edelim. Cünkü TaskId yi UUID ile otomatik olarak assign edecegim. Normal DB
//de birsey yapmama gerek yok. Örnegin PostgreSql bunu PK (Primary Key) olarak
//otomatik assign ediyor.
        TaskDTO task1 = new TaskDTO(project1, user6, "Controller task 1", "RequestMapping task 1", Status.COMPLETE, LocalDate.now().minusDays(4));
        TaskDTO task2 = new TaskDTO(project3, user1, "Configuration task 2", "Database Connection task 2", Status.OPEN, LocalDate.now().minusDays(12));
        TaskDTO task3 = new TaskDTO(project3, user6, "Mapping task 3", "One-To-Many task 3", Status.IN_PROGRESS, LocalDate.now().minusDays(8));
        TaskDTO task4 = new TaskDTO(project2, user1, "Dependency Injection task 4", "Autowired", Status.IN_PROGRESS, LocalDate.now().minusDays(20));
        TaskDTO task5 = new TaskDTO(project2, user1, "Dependency task 5", "Autowired", Status.OPEN, LocalDate.now().minusDays(20));
        TaskDTO task6 = new TaskDTO(project4, user6, "Dependency task 6", "task 6", Status.IN_PROGRESS, LocalDate.now().minusDays(20));


        taskService.save(task1);
        taskService.save(task2);
        taskService.save(task3);
        taskService.save(task4);
        taskService.save(task5);
        taskService.save(task6);




    }
}
