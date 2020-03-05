package com.jxx.TestAop;

import com.jxx.TestAop.annotations.SetNx;
import com.jxx.excel.SkuDto;
import com.jxx.pojo.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("aop/")
@RestController
public class UserController {

    @SetNx(className = Integer.class)
    @RequestMapping("/test")
    public String test(@RequestBody  User user, SkuDto skuDto,Integer t){
        System.out.println("test方法"+user.getName());

        return user.getName();
    }
}
