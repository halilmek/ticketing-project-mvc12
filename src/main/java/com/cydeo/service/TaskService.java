package com.cydeo.service;

import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import org.springframework.scheduling.config.Task;

import java.util.List;

public interface TaskService extends CRUDService<TaskDTO, Long> {

    List<TaskDTO> findTasksByManager(UserDTO manager);

    List<TaskDTO> findAllTasksByStatusIsNotComplete(Status taskStatus);

    void updateStatus(TaskDTO taskDTO);


    List<TaskDTO> findAllTasksByStatus();
}
