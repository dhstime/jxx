package com.jxx.designfuction.abstractFactory;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public class AccessDepartment implements IDepartment{
    @Override
    public void insert(Department department) {
        System.out.println("在Access中给Department表增加一条数据");
    }

    @Override
    public Department getDepartment(Integer id) {
        System.out.println("在Access中根据Id得到Department表一条数据");
        return null;
    }
}
