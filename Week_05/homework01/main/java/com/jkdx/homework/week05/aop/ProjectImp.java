package com.jkdx.homework.week05.aop;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
@Data
public class ProjectImp  implements  IProject{

    @Autowired(required = true)
    private Project project;


    @Override
    public void editProject() {

        System.out.println("------ ProjectImp   editProject -- editMothed"+project);

    }




}
