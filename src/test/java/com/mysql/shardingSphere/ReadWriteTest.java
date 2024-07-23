package com.mysql.shardingSphere;

import com.mysql.shardingSphere.dto.TbItem;
import com.mysql.shardingSphere.mapper.TbItemMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReadWriteTest {

    @Resource
    TbItemMapper tbItemMapper;

    /**
     * 插入数据，确认数据是否主从同步
     */
    @Test
    public void testInsert(){
        tbItemMapper.insert(new TbItem().setCategory("222").setBrand("111").setStatus("000"));
    }


    /**
     * 测试事务
     */
    @Transactional
    @Test
    public void testTrans(){
        tbItemMapper.insert(new TbItem().setCategory("333").setBrand("333").setStatus("1"));
        List<TbItem> tbItems = tbItemMapper.selectList(null);
        System.out.println("tbItems = " + tbItems);
    }
}
