package com.jxx.designfuction.abstractFactory;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public class SqlServerFactory implements IFactory{
    @Override
    public IUser createUser() {
        return new SqlServerUser();
    }

    @Override
    public IDepartment createDepartment() {
        return new SqlServerDepartment();
    }
}
