package com.mysql.shardingSphere.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("t_dict")
public class TbDict implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    private String dictType;

}
