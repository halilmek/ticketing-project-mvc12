package com.cydeo.dto;

import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
//@AllArgsConstructor ==> id yi UUID ile assign edecegim onun icin bu field / instance
//in constructor da olmamasi önemli. Bir eksikle constructor Lombok ile create edilemiyor.
//Bunun icin custom constructor create ediyoruz (Hersey dahil id eksik !!!).
@Data
public class TaskDTO {

    private ProjectDTO projectDTO;
    private UserDTO assignedEmployee;
    private String taskSubject;
    private String taskDetail;
    private Status taskStatus;
    private LocalDate assignedDate;

//Peki bu class in uniq i kim? As a developer benim bunu düsünmem gerekiyor !!!
    private Long id;

    public TaskDTO(ProjectDTO projectDTO, UserDTO assignedEmployee, String taskSubject,
                   String taskDetail, Status taskStatus, LocalDate assignedDate) {
        this.projectDTO = projectDTO;
        this.assignedEmployee = assignedEmployee;
        this.taskSubject = taskSubject;
        this.taskDetail = taskDetail;
        this.taskStatus = taskStatus;
        this.assignedDate = assignedDate;

//ID, constructor da object create edilir edilmez java tarafindan assign ediliyor.
        this.id = UUID.randomUUID().getMostSignificantBits();
    }
}
