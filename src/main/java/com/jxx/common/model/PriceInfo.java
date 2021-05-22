package com.jxx.common.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Strange
 * @ClassName PriceInfo.java
 * @Description TODO
 * @createTime 2021年05月20日 17:31:00
 */
@Data
public class PriceInfo {

    private String sku;

    private Integer allNum ;

    private BigDecimal totalAmount;
}
