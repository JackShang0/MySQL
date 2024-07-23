package com.mysql.shardingSphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysql.shardingSphere.dto.TbItem;
import com.mysql.shardingSphere.dto.TbOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbOrderMapper extends BaseMapper<TbOrder> {
}
