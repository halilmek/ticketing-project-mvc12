package com.cydeo.bootstrap;

import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Role;
import com.cydeo.enums.Gender;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import com.cydeo.service.impl.RoleDTOServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataGenerator implements CommandLineRunner {
//CommandLineRunner, Whenever Spring runs, this class runs first.


    //RoleDTOServiceImpl roleDTOService;=> All injection THROUGH interface
//wiring icin implementation lara ve inject edilen yerlere @Component koymak gerekiyor.
    RoleService roleDTOService;
    UserService userDTOService;

    public DataGenerator(RoleService roleDTOService, UserService userDTOService) {
        this.roleDTOService = roleDTOService;
        this.userDTOService = userDTOService;
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


        UserDTO user1 = new UserDTO("Halil", "Baba", "ztrkhll@gmail.com", "oppo", true, "123456", managerRole, Gender.MALE);
        UserDTO user2 = new UserDTO("Hasan", "Kardes", "abc@gmail.com", "oppo", false, "123456", managerRole, Gender.FEMALE);
        UserDTO user3 = new UserDTO("Elif", "Ünsal", "123@gmail.com", "oppo", true, "123456", adminRole, Gender.MALE);
        UserDTO user4 = new UserDTO("Padisah", "Sultan", "üpoiu@gmail.com", "oppo", true, "123456", managerRole, Gender.FEMALE);
        UserDTO user5 = new UserDTO("Selim", "Han", "dsfgh@gmail.com", "oppo", false, "123456", adminRole, Gender.FEMALE);
        UserDTO user6 = new UserDTO("Saruhan", "Tulumpeyniri", "huijmkjhgv@gmail.com", "oppo", false, "123456", managerRole, Gender.MALE);

        userDTOService.save(user1);
        userDTOService.save(user2);
        userDTOService.save(user3);
        userDTOService.save(user4);
        userDTOService.save(user5);
        userDTOService.save(user6);
    }
}
