package com.cydeo.service;

import java.util.List;

public interface CRUDService <T, ID>{
//Öncellikle bunu Spring Boot veriyor ve bunu düsünmemiz gerekmiyor.
//Peki bunu neden yaptik? Tekrar eden code lari yazmak yerine inheritance ile
// bu methodlari lazim olduklari interface ve class larda kullanmak icin.
// Diger interface lerde bu interface extends ederiz ve generic type larini belirtiriz.
// Böylece her interface CRUD ile iliskili methodlari tek tek yayzmamiz gerekmez. Ancak
// ilgili interface de baska ayrica bir method a ihtiyac duyarsak orada o interface e
// özel method lar create edebiliriz. Gidelim diger interface deki method lari silmeye
// ve inheritance kurmaya.-

    T save (T obj);
    List<T> findAll ();
    T findById (ID id);
    void deleteById(ID id);
    void update(T obj);
}
