package com.mysql.shardingSphere.jdbc.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class TbUser {

    @TableId(type = IdType.AUTO)
    Long id;
    String username;
}
