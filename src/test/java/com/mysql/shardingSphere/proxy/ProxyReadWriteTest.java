package com.mysql.shardingSphere.proxy;


import com.mysql.shardingSphere.jdbc.dto.TbItem;
import com.mysql.shardingSphere.jdbc.mapper.TbItemMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProxyReadWriteTest {

    @Resource
    TbItemMapper itemMapper;


    @Test
    public void selectUserAll(){
        List<TbItem> tbItems = itemMapper.selectList(null);

        System.out.println("size ->"+tbItems.size());
        for (TbItem tbDict : tbItems) {
            System.out.println(tbDict);
        }
    }


    @Test
    public void insertUser(){

        TbItem item = new TbItem().setStatus("1").setBrand("李宁2")
                .setCategory("200").setPrice("199").setName("韦德")
                        .setSpec("鞋子");

        itemMapper.insert(item);
    }
}
