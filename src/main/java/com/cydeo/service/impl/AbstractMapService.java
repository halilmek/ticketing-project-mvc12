package com.cydeo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapService <T, ID> { // Neden abstract? Cünkü object ve
//implementation i burada istemiyorum.
//Parametrelerin sirasi önemli degil.

    protected Map<ID, T> mapOfUser = new HashMap<>();

//Genel olarak interface deki method larin yüzeysel uygulamasi
//implementation i.
    T save (ID id, T obj){

        mapOfUser.put(id, obj);
        return obj;
    }

    List<T> findAll () {

        return new ArrayList<>(mapOfUser.values());
    }

    T findById (ID id) {

        return mapOfUser.get(id);
    }

    void deleteById (ID id) {

        mapOfUser.remove(id);
    }

    void update (ID id, T obj) {

        mapOfUser.put(id, obj);
    }
}
