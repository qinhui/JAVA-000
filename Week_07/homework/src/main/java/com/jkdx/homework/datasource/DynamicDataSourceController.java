package com.jkdx.homework.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




/**
 * @author qinhui
 * @version 1.0 2020/12/3
 */

@RestController
@RequestMapping(value = "/dds")
public class DynamicDataSourceController {


    @Autowired
    private DynamicDataSourceService service;

    @RequestMapping(value = "/user/{id}")
    public User getAllUserData(@PathVariable Integer id){

        return service.getUserData(id);


    }





    }
