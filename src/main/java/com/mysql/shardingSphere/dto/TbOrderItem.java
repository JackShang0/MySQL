package com.mysql.shardingSphere.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@TableName("t_order_item")
@Accessors(chain = true)
public class TbOrderItem {

    @TableId(type = IdType.AUTO)  //主键自增id
    //@TableId(type = IdType.ASSIGN_ID)   //分布式id，依赖于mybatis-plus
    Long id;
    String orderNo;
    Long userId;
    BigDecimal price;
    Integer count;
}
