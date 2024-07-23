package com.mysql.shardingSphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysql.shardingSphere.dto.TbDict;
import com.mysql.shardingSphere.dto.TbUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbDictMapper extends BaseMapper<TbDict> {
}
