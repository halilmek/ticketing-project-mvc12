package com.cydeo.service;

import com.cydeo.dto.RoleDTO;

import java.util.List;

public interface RoleService extends CRUDService <RoleDTO, Long> {//CRUD interface ile
//tekrar eden methodlari sürekli yazmaktan kurtulduk. Generic type larini
// burada belirtmemiz gerekiyordu ve RoleDTO ve Long olarak yazdik-

//Interface deki methodlar CRUD ile iliskili !!!
    /*RoleDTO save (RoleDTO roleDTO); *///Neden return ediyoruz?
//Bu methodu kullandigimiz is akislarinda objenin tuttugu data lara ihtiyacimiz olabilir.
//Örnegin role kullanildi ve ben bunu DB ye kayit edecegim. Eger return etmezsem nereden
//girilen / secilen degerleri / datalari görecegim.

    /*
    RoleDTO findById (Long id);
    List<RoleDTO> findAll();
    void delete (RoleDTO roleDTO);
    void deleteById (Long id);

     */

}
