package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO, Long> implements TaskService {

    @Override
    public TaskDTO save(TaskDTO obj) {

        if (obj.getTaskStatus() == null) {

            obj.setTaskStatus(Status.OPEN);
        }

        if (obj.getAssignedDate() == null) {

            obj.setAssignedDate(LocalDate.now());
        }

        if (obj.getId() == null) {

            obj.setId(UUID.randomUUID().getMostSignificantBits());
        }


        return super.save(obj.getId(), obj);
    }

    @Override
    public List<TaskDTO> findAll() {

        return super.findAll();
    }

    @Override
    public TaskDTO findById(Long id) {

        return super.findById(id);
    }

    @Override
    public void deleteById(Long id) {

        super.deleteById(id);
    }

    @Override
    public void update(TaskDTO obj) {

        TaskDTO updatedTask = super.findById(obj.getId());

        if (obj.getId() == null) {

            obj.setId(updatedTask.getId());
        }


        if (obj.getTaskStatus() == null) {

            obj.setTaskStatus(updatedTask.getTaskStatus());
        }

        obj.setAssignedDate(updatedTask.getAssignedDate());


        super.update(obj.getId(), obj);
    }

    @Override
    public List<TaskDTO> findTasksByManager(UserDTO manager) {

        return super.findAll().stream()
                .filter(eachTask -> eachTask.getProjectDTO().getAssignedManager().equals(manager))
                .collect(Collectors.toList());
    }
}
