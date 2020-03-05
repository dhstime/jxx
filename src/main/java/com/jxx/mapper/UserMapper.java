package com.jxx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.jxx.pojo.User;

/**
*  @author
*/

public interface UserMapper  {

    int insertUser(User user);

    int updateUser(User user);

    int update(User user);

    List<User> queryUser(User user);

    User queryUserLimit1(User user);

    ArrayList<User> findall();

    User queryUserLimit2(User user);
}