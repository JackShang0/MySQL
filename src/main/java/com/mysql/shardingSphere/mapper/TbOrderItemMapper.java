package com.mysql.shardingSphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysql.shardingSphere.dto.TbOrder;
import com.mysql.shardingSphere.dto.TbOrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbOrderItemMapper extends BaseMapper<TbOrderItem> {
}
