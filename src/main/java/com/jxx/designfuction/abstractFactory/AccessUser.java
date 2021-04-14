package com.jxx.designfuction.abstractFactory;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public class AccessUser implements IUser{
    @Override
    public void insert(User user) {
        System.out.println("在Access中给User表增加一条数据");
    }

    @Override
    public User getUser(Integer id) {
        System.out.println("在Access中根据Id得到User表一条数据");
        return null;
    }
}
