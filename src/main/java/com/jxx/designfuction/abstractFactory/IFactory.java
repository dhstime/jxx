package com.jxx.designfuction.abstractFactory;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public interface IFactory {

    IUser createUser();

    IDepartment createDepartment();
}
