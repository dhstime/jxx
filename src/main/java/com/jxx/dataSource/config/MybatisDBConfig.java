package com.jxx.dataSource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.ttqia.ez.sqlog.EzSqlogDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author Strange
 * @ClassName MybatisDBConfig.java
 * @Description TODO
 * @createTime 2021年04月10日 11:27:00
 */
@Configuration
public class MybatisDBConfig {

    @Bean(name = "dataSourceTarget")
    @Primary
    public DataSource dataSourceTarget(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://192.168.1.53:3306/erp_ivedeng_com?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true");
        dataSource.setUsername("erpuser");
        dataSource.setPassword("neweu3453563");

        EzSqlogDataSource ezSqlogDataSource = new EzSqlogDataSource();
        ezSqlogDataSource.setRealDataSource(dataSource);
        return ezSqlogDataSource;
    }
}
