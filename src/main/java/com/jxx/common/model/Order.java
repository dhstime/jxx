package com.jxx.common.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Strange
 * @ClassName Order.java
 * @Description TODO
 * @createTime 2020年08月06日 15:24:00
 */

@Data
public class Order {

    private  Integer id;

    private Integer num;

    private BigDecimal price;

    private BigDecimal totalAmount;

    private String traderName;

    private String traderId;

    private String orderNo;
}
