package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO, String>
    implements ProjectService {


    @Override
    public ProjectDTO save(ProjectDTO obj) {

        return super.save(obj.getProjectCode(), obj);
    }

    @Override
    public List<ProjectDTO> findAll() {

        return super.findAll();
    }

    @Override
    public ProjectDTO findById(String id) {

        return super.findById(id);
    }

    @Override
    public void deleteById(String id) {

        super.deleteById(id);
    }

    @Override
    public void update(ProjectDTO obj) {

        super.update(obj.getProjectCode(), obj);
    }
}
