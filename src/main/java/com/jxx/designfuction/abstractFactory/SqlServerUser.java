package com.jxx.designfuction.abstractFactory;

public class SqlServerUser implements IUser{

    @Override
    public void insert(User user){
        System.out.println("在SQL server中给User表增加一条数据");
    }
    @Override
    public User getUser(Integer id){
        System.out.println("在SQL server中根据Id得到User表一条数据");
        return null;
    }
}
