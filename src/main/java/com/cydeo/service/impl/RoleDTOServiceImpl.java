package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service// @Component 1- Controller, 2- @Service, 3- Sonra gelecek DB icin.
public class RoleDTOServiceImpl extends AbstractMapService <RoleDTO, Long>
        implements RoleService {

    @Override
    public List<RoleDTO> findAll() {
        return super.findAll();
    }

    @Override
    public RoleDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Long id) {

        super.deleteById(id);
    }

    @Override
    public RoleDTO save(RoleDTO obj) {
        return super.save(obj.getId(), obj);
    }
}
