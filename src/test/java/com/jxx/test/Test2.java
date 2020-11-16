package com.jxx.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Strange
 * @ClassName Test2.java
 * @Description TODO
 * @createTime 2020年10月15日 10:31:00
 */
public class Test2 {
    @Test
    public void test(){
        String sku = "V123";
        String substring = sku.substring(1);
        System.out.println(substring);
    }

}
