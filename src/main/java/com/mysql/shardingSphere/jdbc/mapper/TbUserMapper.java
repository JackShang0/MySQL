package com.mysql.shardingSphere.jdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysql.shardingSphere.jdbc.dto.TbUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbUserMapper extends BaseMapper<TbUser> {
}
