package com.jxx.sqlTest;

import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.*;
import java.util.*;
/**
 * @author Strange
 * @ClassName Dao.java
 * @Description TODO
 * @createTime 2020年11月11日 09:46:00
 */
public class Dao {
    private static Dao dao=new Dao();
    private Dao(){

    }
    public static Dao getInstance(){
        return dao;
    }
    public   List<Map<String, String>> executeQueryString(DataSource dataSource,String sql, Object[] bindArgs) throws SQLException {
        List<Map<String, Object>> datas =executeQuery(dataSource,sql,bindArgs);
        List<Map<String, String>> datastr=new ArrayList<>(  );
        if(datas==null){
            return datastr;
        }
        datas.forEach(item->{
            Map<String,String> map=new HashMap<>(   );
            item.forEach((k,v) ->{
                map.put(k,String.valueOf(v));
            });
            datastr.add(map);
        });
        return datastr;
    }
    public  Map<String, Object> executeQueryOne(DataSource dataSource,String sql, Object[] bindArgs) throws SQLException {
        List<Map<String, Object>> list=executeQuery(dataSource,sql,bindArgs);
        return list.size()>0?list.get(0): Collections.emptyMap();
    }
    public   List<Map<String, Object>> executeQuery(DataSource dataSource,String sql, Object[] bindArgs) throws SQLException {
        List<Map<String, Object>> datas = new ArrayList<>(0);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (bindArgs != null) {
                for (int i = 0; i < bindArgs.length; i++) {
                    preparedStatement.setObject(i + 1, bindArgs[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            datas = getDatas(resultSet);
        } catch (Exception e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return datas;
    }
    public   long executeUpdate(DataSource dataSource,String sql, Object[] bindArgs) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            if (bindArgs != null) {
                for (int i = 0; i < bindArgs.length; i++) {
                    if(bindArgs[i] instanceof  Date){
                        preparedStatement.setTimestamp(i + 1,new Timestamp(((Date) bindArgs[i]).getTime()));
                    }else{
                        preparedStatement.setObject(i + 1, bindArgs[i]);
                    }
                }
            }
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                long generateKey = generatedKeys.getLong(1);
                return generateKey;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return 0;
    }


    public  Long executeCountQuery(DataSource dataSource,String sql, Object[] bindArgs) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (bindArgs != null) {
                for (int i = 0; i < bindArgs.length; i++) {
                    preparedStatement.setObject(i + 1, bindArgs[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Long count=resultSet.getLong(1);
                return count;
            }
            return 0l;

        } catch (Exception e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    public  List<Long> executeListOneQuery(DataSource dataSource,String sql, Object[] bindArgs) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (bindArgs != null) {
                for (int i = 0; i < bindArgs.length; i++) {
                    preparedStatement.setObject(i + 1, bindArgs[i]);
                }
            }
            List<Long> list=new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Long count=resultSet.getLong(1);
                list.add(count);
            }
            return list;

        } catch (Exception e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    /**
     * 将结果集对象封装成List<Map<String, Object>> 对象
     *
     * @param resultSet 结果多想
     * @return 结果的封装
     * @throws SQLException
     */
    private static List<Map<String, Object>> getDatas(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> datas = new ArrayList<>();
        /**获取结果集的数据结构对象**/
        ResultSetMetaData metaData = resultSet.getMetaData();
        while (resultSet.next()) {
            Map<String, Object> rowMap = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                try {
                    Object objectValue = resultSet.getObject(i);
                    if (objectValue != null && objectValue instanceof Timestamp) {
                        objectValue = objectValue == null ? "" : objectValue.toString();
                    }
                    if (StringUtils.isNotBlank(metaData.getColumnLabel(i))) {
                        rowMap.put(StringUtils.upperCase(metaData.getColumnLabel(i)), objectValue);
                    } else {
                        rowMap.put(StringUtils.upperCase(metaData.getColumnName(i)), objectValue);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    rowMap.put(StringUtils.upperCase(metaData.getColumnName(i)), "");
                }
            }
            datas.add(rowMap);
        }
        return datas;
    }

}
