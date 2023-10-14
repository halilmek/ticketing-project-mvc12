package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;

import java.util.List;

public interface ProjectService extends CRUDService<ProjectDTO, String> {

//Bu kismi eklemedigim icin 2 saattir ayni yerde oyalaniyorum. Generic type kullaninca
//implementation icin Obje türlerini diamond parantez de belirtmek gerekiyor.


    void complete (ProjectDTO projectDTO);//Bana ProjectDTO objesi
    //ver diyoruz.
}
