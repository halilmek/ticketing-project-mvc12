package com.cydeo.dto;

import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank; //Not hibernate
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDTO {

    @NotBlank
    private String projectName;

    @NotBlank
    private String projectCode;

    @NotNull
    private UserDTO assignedManager;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate endDate;

    @NotBlank
    private String projectDetail;
    private Status projectStatus;

// for Completed tasks and non-completed tasks, we need 2 more field / instance !!
    private int completedTaskCount;
    private int unfinishedTaskCount;

    public ProjectDTO(String projectName, String projectCode, UserDTO assignedManager,
          LocalDate startDate, LocalDate endDate, String projectDetail, Status projectStatus) {

        this.projectName = projectName;
        this.projectCode = projectCode;
        this.assignedManager = assignedManager;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectDetail = projectDetail;
        this.projectStatus = projectStatus;

//Completed tasks and non-completed tasks lari burada constructor a yazmiyoruz.
//Bunlari service class larda implement edip obje pass edip controller üerinden
//view e yansitacagiz.
    }
}
