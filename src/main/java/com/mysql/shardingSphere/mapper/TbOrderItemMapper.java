package com.mysql.shardingSphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysql.shardingSphere.dto.TbOrder;
import com.mysql.shardingSphere.dto.TbOrderItem;
import com.mysql.shardingSphere.vo.OrderItemVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TbOrderItemMapper extends BaseMapper<TbOrderItem> {

    /**
     * 多表查询在分库分表中注意要写的表名是逻辑表名
     * @return data
     */
    @Select({"select o.order_no,SUM(oi.price * oi.count) amount",
             "from t_order o join t_order_item oi on o.order_no=oi.order_no",
             "group by o.order_no"})
    List<OrderItemVo> getOrderAmount();
}
