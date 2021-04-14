package com.jxx.methonlock;

import com.jxx.methonlock.annotations.SetNx;
import com.jxx.crawler.model.ChnRegister;
import com.jxx.excel.SkuDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("aop/")
@RestController
public class UserController {

    @SetNx(className = Integer.class)
    @RequestMapping("/test")
    public String test(@RequestBody ChnRegister chnRegister, SkuDto skuDto, Integer t){
        System.out.println("test方法"+chnRegister.getChnRegisterNumber());

        return chnRegister.getChnRegisterNumber();
    }
}
