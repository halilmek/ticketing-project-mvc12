package com.cydeo.enums;

public enum Gender {

    MALE ("Male"), FEMALE ("Female");
//Kücük harf veya bosluk (No Progress) kullanmak icin enum lar bu sekilde
//de kullaniliyor. Bu bize kullanacagimiz value yu modifiye etmek imkani veriyor.
// Bir de JavaScript icinde 0 ve 1 seklinde kullanimi varmis.

    private final String value;

    Gender (String value) {
        this.value//Bu parametredeki value
                = value;//Bu instance / field olarak tanimlanan value.
    }

    public String getValue () {//Klasik getter method!
//Klasik olmayan enum icinde bu kullanim.

        return value;
    }
}
