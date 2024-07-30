package com.mysql.shardingSphere.jdbc.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("t_order")
@Accessors(chain = true)
public class TbOrder {

    @TableId(type = IdType.AUTO)  //主键自增id
    //@TableId(type = IdType.ASSIGN_ID)   //分布式id，依赖于mybatis-plus
    Long id;
    String orderNo;
    Long userId;
    String amount;
}
