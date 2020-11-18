package com.jkdx.example.homework05.annTest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControllerTest {

    @Autowired
    private ServiceTest serviceTest_;
    @Autowired
    private ComponentTest componentTest_;
    @Autowired
    private BeanTest beanTest;

    @RequestMapping("/test/get")
    @ResponseBody
    public String TestGet() {

//       User user =  beanTest.insert();

        return componentTest_.Test() + serviceTest_.testService() + beanTest;
    }


}
