package com.mysql.shardingSphere;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mysql.shardingSphere.dto.TbOrder;
import com.mysql.shardingSphere.dto.TbUser;
import com.mysql.shardingSphere.mapper.TbOrderMapper;
import com.mysql.shardingSphere.mapper.TbUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShardingTest {


    @Resource
    TbOrderMapper orderMapper;

    @Resource
    TbUserMapper userMapper;


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
        tbOrder.setOrderNo("123");
        tbOrder.setAmount("100");
        tbOrder.setId(1L);
        //orderMapper.insert(tbOrder);
        List<TbOrder> tbOrders = orderMapper.selectList(null);
        System.out.println("tbOrders = " + tbOrders);

        TbUser tbUser = new TbUser();
        tbUser.setId(1L);
        tbUser.setUsername("张三");
        //userMapper.insert(tbUser);
        List<TbUser> tbUsers = userMapper.selectList(null);
        System.out.println("tbUsers = " + tbUsers);
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

}
