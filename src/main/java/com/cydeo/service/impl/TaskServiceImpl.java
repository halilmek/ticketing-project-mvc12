package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.scheduling.config.Task;
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
    public void update(TaskDTO taskDTO) {

        System.out.println("taskDTO = " + taskDTO);

        TaskDTO updatedTask = findById(taskDTO.getId());

        //taskDTO.setId(updatedTask.getId());

        taskDTO.setTaskStatus(updatedTask.getTaskStatus());

        taskDTO.setAssignedDate(updatedTask.getAssignedDate());


        super.update(taskDTO.getId(), taskDTO);

        System.out.println("taskDTO = " + taskDTO);
        System.out.println("updatedTask = " + updatedTask);
    }

    @Override
    public List<TaskDTO> findTasksByManager(UserDTO manager) {

        return super.findAll().stream()
                .filter(eachTask -> eachTask.getProjectDTO().getAssignedManager().equals(manager))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findAllTasksByStatusIsNotComplete(Status taskStatus) {

//Burada ! ile not anlami kattigimiz stream ile aranan task lari buluyoruz.
        return findAll().stream()
                .filter(eachTask -> !eachTask.getTaskStatus().equals(Status.COMPLETE))
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(TaskDTO taskDTO) {

        //System.out.println("taskDTO = taskStatus " + taskDTO);

        findById(taskDTO.getId()).setTaskStatus(taskDTO.getTaskStatus());
        //findById(taskDTO.getId()).setAssignedDate(taskDTO.getAssignedDate());
        //mapOfUser.put(taskDTO.getId(), taskDTO);
        //update(taskDTO);

        System.out.println("taskDTO = " + taskDTO);
    }

    @Override
    public List<TaskDTO> findAllTasksByStatus() {

        return findAll().stream()
                .filter(eachTasks -> eachTasks.getTaskStatus().equals(Status.COMPLETE))
                .collect(Collectors.toList());
    }
}
