package com.mysql.shardingSphere.proxy;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mysql.config.ConfigTest;
import com.mysql.shardingSphere.jdbc.dto.TbDict;
import com.mysql.shardingSphere.jdbc.dto.TbOrder;
import com.mysql.shardingSphere.jdbc.dto.TbOrderItem;
import com.mysql.shardingSphere.jdbc.dto.TbUser;
import com.mysql.shardingSphere.jdbc.mapper.TbDictMapper;
import com.mysql.shardingSphere.jdbc.mapper.TbOrderItemMapper;
import com.mysql.shardingSphere.jdbc.mapper.TbOrderMapper;
import com.mysql.shardingSphere.jdbc.mapper.TbUserMapper;
import com.mysql.shardingSphere.jdbc.vo.OrderItemVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProxyShardingTest {


    @Resource
    TbOrderMapper orderMapper;

    @Resource
    TbUserMapper userMapper;

    @Resource
    TbOrderItemMapper orderItemMapper;

    @Resource
    TbDictMapper dictMapper;


    /**
     * 垂直分库测试  新增
     */
    @Test
    public void testVertical1(){
        TbOrder tbOrder = new TbOrder();
        tbOrder.setUserId(1L);
        tbOrder.setOrderNo("123");
        tbOrder.setAmount("100");
        tbOrder.setId(1L);
        orderMapper.insert(tbOrder);

        TbUser tbUser = new TbUser();
        tbUser.setId(1L);
        tbUser.setUsername("张三");
        userMapper.insert(tbUser);
    }


    /**
     * 垂直分库测试  查询
     */
    @Test
    public void testVertical2(){
        TbOrder tbOrder = new TbOrder();
        tbOrder.setUserId(1L);
        tbOrder.setOrderNo("777");
        tbOrder.setAmount("100");
        tbOrder.setId(10L);
        orderMapper.insert(tbOrder);
        //List<TbOrder> tbOrders = orderMapper.selectList(null);
        //System.out.println("tbOrders = " + tbOrders);

        TbUser tbUser = new TbUser();
        tbUser.setId(10L);
        tbUser.setUsername("李四");
        userMapper.insert(tbUser);
        //List<TbUser> tbUsers = userMapper.selectList(null);
        //System.out.println("tbUsers = " + tbUsers);
    }


    /**
     * 水平分片 插入测试
     */
    @Test
    public void testLevel1(){
        TbOrder tbOrder = new TbOrder();
        tbOrder.setUserId(4L);
        tbOrder.setOrderNo("123");
        tbOrder.setAmount("100");
        //tbOrder.setId(5L);
        orderMapper.insert(tbOrder);

        TbUser tbUser = new TbUser();
        tbUser.setId(4L);
        tbUser.setUsername("张三");
        //userMapper.insert(tbUser);
    }


    /**
     * order表的分库策略  将数据插入到不同库的表中
     */
    @Test
    public void testShardingStrategy_insert(){
        for (int i = 0; i < 5; i++) {
            TbOrder tbOrder = new TbOrder();
            tbOrder.setUserId(10L+i);
            tbOrder.setOrderNo("123");
            tbOrder.setAmount("100");
            //tbOrder.setId(5L);
            orderMapper.insert(tbOrder);
        }
    }


    /**
     * 分库分表查询测试
     */
    @Test
    public void testShardingStrategy_select(){
        List<TbOrder> tbOrders = orderMapper.selectList(null);
        System.out.println("data count ->"+tbOrders.size());
        for (TbOrder tbOrder : tbOrders) {
            System.out.println(tbOrder);
        }
    }


    /**
     * 分表测试
     */
    @Test
    public void testShardingTablesStrategy_insert(){
        for (int i = 0; i < 5; i++) {
            TbOrder tbOrder = new TbOrder();
            tbOrder.setUserId(1L);
            tbOrder.setOrderNo("order000"+i);
            tbOrder.setAmount("100");
            //tbOrder.setId(5L);
            orderMapper.insert(tbOrder);
        }
    }


    /**
     * 水平分库分表测试
     */
    @Test
    public void testLevel_database_table_insert(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                TbOrder tbOrder = new TbOrder();
                tbOrder.setUserId( (long) i );
                tbOrder.setOrderNo("order000"+j);
                tbOrder.setAmount("100");
                //tbOrder.setId(5L);
                orderMapper.insert(tbOrder);
            }
        }
    }


    /**
     * 分库分表查询测试  单条数据查询测试
     */
    @Test
    public void testShardingStrategy_selectOne(){
        QueryWrapper<TbOrder> userId = new QueryWrapper<>();
        userId.eq("user_id", "1");
        List<TbOrder> tbOrders = orderMapper.selectList(userId);
        System.out.println("data count ->"+tbOrders.size());
        for (TbOrder tbOrder : tbOrders) {
            System.out.println(tbOrder);
        }
    }


    /**
     * 测试关联表插入，可以将关联表以相同规则插入到相同的数据库中
     */
    @Test
    public void testOrder_Item_insert(){

        for (int i = 0; i < 5; i++) {
            TbOrder tbOrder = new TbOrder().setUserId(1L).setOrderNo("order" + i);
            orderMapper.insert(tbOrder);

            for (int j = 0; j < 4; j++) {
                TbOrderItem tbOrderItem = new TbOrderItem().setUserId(1L).setOrderNo("order" + i)
                        .setPrice(new BigDecimal(100)).setCount(99);
                orderItemMapper.insert(tbOrderItem);
            }
        }


        for (int i = 5; i < 10; i++) {
            TbOrder tbOrder = new TbOrder().setUserId(2L).setOrderNo("order" + i);
            orderMapper.insert(tbOrder);

            for (int j = 0; j < 4; j++) {
                TbOrderItem tbOrderItem = new TbOrderItem().setUserId(2L).setOrderNo("order" + i)
                        .setPrice(new BigDecimal(100)).setCount(99);
                orderItemMapper.insert(tbOrderItem);
            }
        }
    }


    /**
     * 分库分表 多表关联查询
     */
    @Test
    public void selectOrderItemVo(){
        List<OrderItemVo> orderAmount = orderItemMapper.getOrderAmount();
        System.out.println("data count ->"+orderAmount.size());
        for (OrderItemVo tbOrder : orderAmount) {
            System.out.println(tbOrder);
        }
    }


    /**
     * 广播表插入数据
     */
    @Test
    public void insertBroadcast(){
        dictMapper.insert(new TbDict().setDictType("汉族"));
    }


    /**
     * 广播表查询数据
     */
    @Test
    public void selectBroadcast(){
        List<TbDict> tbDicts = dictMapper.selectList(null);
        System.out.println("size ->"+tbDicts.size());
        for (TbDict tbDict : tbDicts) {
            System.out.println(tbDict);
        }
    }


    @Resource
    ConfigTest configTest;

    @Test
    public void configTest(){
        System.out.println(configTest);
        System.out.println(configTest.getTables());
    }
}
