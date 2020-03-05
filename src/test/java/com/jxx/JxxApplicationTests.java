package com.jxx;

import com.jxx.Service.DataService;
import com.jxx.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JxxApplicationTests {
    @Autowired
    private DataService dataService;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        String data = dataService.getData();
        System.out.println("data = " + data);
    }

}
