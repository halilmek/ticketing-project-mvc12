package com.cydeo.dto;

import com.cydeo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private String firstName;
    private String lastName;
    private String userName; //Email bilgisi uniq assume ediyoruz.
    private String password;
    private boolean isEnabled;
    private String phone;
    private RoleDTO roleDTO;
    private Gender gender;

    public UserDTO(String firstName, String lastName, String userName, String password,
                   boolean isEnabled, String phone, RoleDTO roleDTO, Gender gender) {
//Extend olmadigi icin entity package indaki gibi super() call etmiyoruz. Sadece
//ilgili class in constructor ini create ediyoruz.-
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.isEnabled = isEnabled;
        this.phone = phone;
        this.roleDTO = roleDTO;
        this.gender = gender;
    }
}


