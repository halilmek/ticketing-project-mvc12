package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService extends CRUDService <UserDTO, String> {

    /*
    UserDTO save (UserDTO userDTO);
    UserDTO findById (String username);
    List<UserDTO> findAll ();
    void delete (UserDTO userDTO);
    void deleteById (String username);

     */

    List<UserDTO> allManagers ();
}
