package com.jxx.designfuction.abstractFactory;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public class AccessFactory implements IFactory{
    @Override
    public IUser createUser() {
        return new AccessUser();
    }

    @Override
    public IDepartment createDepartment() {
        return new AccessDepartment();
    }
}
