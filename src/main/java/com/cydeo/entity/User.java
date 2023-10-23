package com.cydeo.entity;

import com.cydeo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
//@AllArgsConstructor => Neden bunu burada kullanmiyorum?
//Cünkü lombok süper class in field lerini @AllArgsConstructor a ekleyemiyor.
//Bundan dolayi AllArgsConstructor i manuel olarak kendim create ediyorum.

public class User extends BaseEntity {

    private String firstName;
    private String lastName;
private String userName; //Bu EMAIL icin ama bunu as uniq value olarak kullancagiz.
    private String passWord;
    private boolean isEnabled;
    private String phone;
    private Role role;
    private Gender gender;

    public User(Long id, LocalDateTime insertDateTime, Long insertUserId, LocalDateTime lastUpdateDateTime,
                Long lasUpdateUserId, //Bundan önceki Parent class dan.
                String firstName, String lastName, String userName, String passWord, boolean isEnabled,
                String phone, Role role, Gender gender) {
        super(id, insertDateTime, insertUserId, lastUpdateDateTime, lasUpdateUserId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passWord = passWord;
        this.isEnabled = isEnabled;
        this.phone = phone;
        this.role = role;
        this.gender = gender;
    }

}
