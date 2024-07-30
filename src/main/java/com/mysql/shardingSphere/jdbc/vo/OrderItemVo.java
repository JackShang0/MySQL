package com.mysql.shardingSphere.jdbc.vo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemVo {

    String orderNo;
    BigDecimal amount;
}
