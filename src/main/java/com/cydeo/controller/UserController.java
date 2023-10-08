package com.cydeo.controller;


import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {

    RoleService roleDTOService;
    UserService userDTOService;

    public UserController(RoleService roleDTOService, UserService userDTOService) {
        this.roleDTOService = roleDTOService;
        this.userDTOService = userDTOService;
    }

    @GetMapping("/create")//Bu end point ile .html file (return edilen)
//ayni olmak zorunda degil. Raslanti diyelim.
    public String createUser (Model model) {

        model.addAttribute("newUser", new UserDTO());

        model.addAttribute("listOfRoles", roleDTOService.findAll());
//Attribute name, attribute value => which is like list this time.
        model.addAttribute("allUser", userDTOService.findAll());

//Printing the map => To see the results !!!
        roleDTOService.findAll().forEach(System.out::println);

/*
Yukaridaki obje nin save() methodu bunlari return ediyor.
RoleDTO(id=1, description=Admin)
RoleDTO(id=2, description=Manager)
RoleDTO(id=3, description=Employee)
 */

        userDTOService.findAll().forEach(System.out::println);
/*
Yukaridaki userDTOService obje si ise bunlari return ediyor.
UserDTO(firstName=Halil, lastName=Baba, userName=ztrkhll@gmail.com, password=oppo, isEnabled=true, phone=123456, roleDTO=RoleDTO(id=2, description=Manager), gender=MALE)
UserDTO(firstName=Padisah, lastName=Sultan, userName=üpoiu@gmail.com, password=oppo, isEnabled=true, phone=123456, roleDTO=RoleDTO(id=2, description=Manager), gender=FEMALE)
...
Yani her obje kendisine AbstractMapService araciligiyla tahsis edilen MAP te
save islemlerini yapiyor.
 */
        return "/user/create";
    }


    @PostMapping("/create")
    public String insertUser (@ModelAttribute("newUser") UserDTO userDTO, Model model) {

        userDTOService.save(userDTO);//Böyle dalyarak gibi objeyi pass edemezsin.
//Cünkü role ü String e cevirmekte sorun yasiyoruz. Role bir obje oldugu icin.

        //return "redirect:/user/create";//Böylece eski page de kalma imkanina sahibiz.
//return "redirect:/user/create"; => user dan önce / (slash) var diye run etmedi.

        return "redirect:/user/create";
    }


    /*




    @PostMapping("/create")
    public String insertUser (@ModelAttribute("newUser") UserDTO userDTO, Model model) {

        model.addAttribute("newUser", new UserDTO());//Bunu
//tekrar bos bir .html form u getirmek icin kullaniyorum. Bunun icin
//@GetMapping deki gibi empty user getiriyorum ve .html file da empty user in firstName
// lastName i vs i görünecek ki onlar da null henüz !!! Ayrica =>

//<form th:action="@{/user/create}" method="post" th:object="${newUser}"> daki newUser
//          ("${newUser}") tanimsiz kalacagi icin tam bir obje create edilemeyecek.


        model.addAttribute("listOfRoles", roleDTOService.findAll());//Bunu
//dropdown olan roles da roles leri göstermek icin kullaniyorum. Cünkü yukarida
//bos form getir diyorum. Böylece dropdown da bos geliyor.


        userDTOService.save(userDTO);//Böyle dalyarak gibi objeyi pass edemezsin.
//Cünkü role ü String e cevirmekte sorun yasiyoruz. Role bir obje oldugu icin.


        model.addAttribute("allUser", userDTOService.findAll());//Table da
//tüm create edilen user lari show etmek icin bunu kullaniyorum. Aksi halde MAP deki
//diger user lari göremiyorum.


        //return "redirect:/user/create";//Böylece eski page de kalma imkanina sahibiz.
//redirect i kullaninca table a sadece isim ve userName bilgisi geldi hata verdi !!!
        return "/user/create";
    }


    */
}