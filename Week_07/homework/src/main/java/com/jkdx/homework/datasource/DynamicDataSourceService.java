package com.jkdx.homework.datasource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
/**
 * @author qinhui
 * @version 1.0 2020/12/3
 */

@Service
public class DynamicDataSourceService {


    @Autowired
    private UserMapper userMapper;


    public User getUserData(Integer id) {

        return userMapper.selectByPrimaryKey(id);


    }
}
