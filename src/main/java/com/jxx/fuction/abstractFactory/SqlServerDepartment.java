package com.jxx.fuction.abstractFactory;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public class SqlServerDepartment implements IDepartment{
    @Override
    public void insert(Department department) {
        System.out.println("在Sqlserver中给Department表增加一条数据");
    }

    @Override
    public Department getDepartment(Integer id) {
        System.out.println("在Sqlserver中根据Id得到Department表一条数据");
        return null;
    }
}
