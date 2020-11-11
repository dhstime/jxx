package com.jxx.sqlTest;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

/**
 * @author Strange
 * @ClassName SqlUtils.java
 * @Description TODO
 * @createTime 2020年11月11日 09:45:00
 */
public class SqlUtils {
    static Logger logger= LoggerFactory.getLogger(SqlUtils.class);


    public static  DataSource datasourc(String url, String name, String password, String driverClassName,String logSqlType) {

        MysqlDataSource source = new MysqlDataSource();
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(url);
            config.setDriverClassName(driverClassName);
            config.setUsername(name);
            config.setPassword(password);
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            config.addDataSourceProperty("minimumIdle", "5");
            //最大存活时间
            config.addDataSourceProperty("maxLifetime", "1000000");

            config.setMaximumPoolSize(60);
            //config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
            HikariDataSource dataSource = new HikariDataSource(config);

//            source.setRealDataSource(dataSource);
//            source.setLogType(logSqlType);

        }catch (Exception e){
            e.printStackTrace();
        }
        return source;
    }



}

