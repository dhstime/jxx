package com.jxx.designfuction.abstractFactory;

public class DataAccess {

//    private static final  String db = "SqlServer";
    private static final  String db = "Access";

    private static final String assemblyName = "com.jxx.fuction.abstractFactory";

    public static IUser createUser() {
        try {
            String classname = assemblyName +"."+db+"User";
            Class<?> aClass = Class.forName(classname);
            return (IUser)aClass.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static IDepartment createDepartment(){
        try{
            String classname = assemblyName +"."+db+"Department";
            Class<?> aClass = Class.forName(classname);
            return (IDepartment)aClass.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }
}
