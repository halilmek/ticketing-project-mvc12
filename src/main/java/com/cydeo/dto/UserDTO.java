package com.cydeo.dto;

import com.cydeo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor //Bunun comment out unu kaldirdik.
//@Data => Sanirim password ü pass etmesin diye !!! password icin sadece setter lar create
// edilecek sanirim.-
@ToString
public class UserDTO {

    @NotBlank
    @Size(max = 17, min = 2)
    private String firstName;

    @NotBlank
    @Size(max = 18, min = 2)
    private String lastName;

    @NotBlank
    @Email
    private String userName; //Email bilgisi uniq assume ediyoruz.

    @NotBlank
    @Pattern(regexp = "(?=.*\\d)(?=.*[A-Z]).{4,}")
    private String passWord;

    @NotNull
    private String confirmPassWord;

    private boolean isEnabled;

    @NotBlank
    @Pattern(regexp = "^\\d{10}$")
    private String phone;

    @NotNull
    private RoleDTO roleDTO;

    @NotNull
    private Gender gender;

    public String getPassWord() {

        return passWord;
    }

    public void setPassWord(String passWord) {

        this.passWord = passWord;

        checkConfirmPassWord();
    }

    public String getConfirmPassWord () {

        return confirmPassWord;
    }

    public void setConfirmPassWord(String confirmPassWord) {

        this.confirmPassWord = confirmPassWord;

        checkConfirmPassWord();
    }

    private void checkConfirmPassWord() {

        if (this.passWord == null || this.confirmPassWord == null) {

            return;
        }else if (!this.passWord.equals(confirmPassWord)) {

            this.confirmPassWord = null;
        }

    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public boolean isEnabled() {

        return isEnabled;
    }

    public void setEnabled(boolean enabled) {

        isEnabled = enabled;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public RoleDTO getRoleDTO() {

        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {

        this.roleDTO = roleDTO;
    }

    public Gender getGender() {

        return gender;
    }

    public void setGender(Gender gender) {

        this.gender = gender;
    }

    /*
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
     */
}


