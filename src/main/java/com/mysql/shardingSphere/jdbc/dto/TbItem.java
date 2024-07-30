package com.mysql.shardingSphere.jdbc.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("tb_item")
public class TbItem implements Serializable {


    /**
     *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
     *   `title` varchar(255) DEFAULT NULL COMMENT '商品标题',
     *   `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
     *   `price` bigint(20) DEFAULT NULL,
     *   `image` varchar(255) DEFAULT NULL COMMENT '图片',
     *   `category` varchar(255) DEFAULT NULL COMMENT '类目名称',
     *   `brand` varchar(255) DEFAULT NULL COMMENT '品牌名称',
     *   `spec` varchar(255) DEFAULT NULL COMMENT '规格',
     *   `status` int(1) NOT NULL COMMENT '状态1正常；2下架；3删除',
     *   `create_time` datetime DEFAULT NULL,
     *   `update_time` datetime DEFAULT NULL,
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String name;
    private String price;
    private String image;
    private String category;
    private String brand;
    private String spec;
    private String status;
    private String create_time;
    private String update_time;


}
