package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDTOServiceImpl extends AbstractMapService <UserDTO, String>
        implements UserService {

    @Override
    public UserDTO save(UserDTO obj) {
        return super.save(obj.getUserName(), obj);
    }

    @Override
    public List<UserDTO> findAll() {
        return super.findAll();
    }

    @Override
    public UserDTO findById(String id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(String id) {

        mapOfUser.remove(id);
    }
}