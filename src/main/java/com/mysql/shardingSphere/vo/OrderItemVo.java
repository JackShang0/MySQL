package com.mysql.shardingSphere.vo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemVo {

    String orderNo;
    BigDecimal amount;
}
