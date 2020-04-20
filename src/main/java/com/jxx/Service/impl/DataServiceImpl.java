package com.jxx.Service.impl;


import com.jxx.fuction.abstractFactory.User;
import com.jxx.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class DataServiceImpl implements com.jxx.Service.DataService {

    @Resource
    private UserMapper userMapper;

    @Override
    public String getData() {

        return "id是"+1+"名字是"+2;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false,propagation = Propagation.REQUIRED)
    public void insert() throws Exception {
        User user = new User();
        user.setName("dd");
        user.setAge(12);
        User user1  = userMapper.getUserByName("dd");
        if(user1 == null){
            userMapper.insert(user);
        }
        System.out.println(user1.toString());
        update(user1);
        User user12  = userMapper.getUserByName("dd");
        System.out.println(user12.toString());
        throw new RuntimeException();

    }
//    @Transactional(rollbackFor = Exception.class, readOnly = false,propagation = Propagation.REQUIRED)
    public void update(User user)  throws Exception{
        try {
            user.setAge(13);
            userMapper.update(user);
            User dd = userMapper.getUserByName("dd");
            System.out.println(dd.toString());
//            throw new RuntimeException();
        }catch (Exception e){}

    }
}
