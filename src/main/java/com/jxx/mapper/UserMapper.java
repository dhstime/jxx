package com.jxx.mapper;

import com.jxx.fuction.abstractFactory.User;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public interface UserMapper {

    public Integer insert(User user);

    public Integer update(User user);

    User getUserByName(String dd);
}
