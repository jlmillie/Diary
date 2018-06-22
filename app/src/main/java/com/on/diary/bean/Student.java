package com.on.diary.bean;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/11.
 */

public class Student {
    private String mess="Student的实例是注解方式注入的";
    @Inject
     public Student(){

     }
    public String showMessage(){
        return mess;
    }

}
