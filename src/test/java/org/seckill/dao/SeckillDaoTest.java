package org.seckill.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

/**
 * 配置spring和junit整合,juint启动时加载springIOC容器
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class SeckillDaoTest {
    // 注入Dao实现类依赖
    @Resource
    private SeckillDao seckilldao;

    @Test
    public void testQueryById() throws Exception {
        long id=1000;
        Seckill seckill = seckilldao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
        /**
         * 1000元秒杀iPhone8
         Seckill [seckillId=1000, name=1000元秒杀iPhone8, number=100,
         startTime=Sun Oct 01 00:00:00 CST 2017,
         endTime=Mon Oct 02 00:00:00 CST 2017,
         createTime=Mon Dec 25 17:04:04 CST 2017]*/
    }

    @Test
    public void testQueryAll() throws Exception {
		/*BindingException: Parameter 'offset' not found. Available parameters are [0, 1, param1, param2]*/
        //List<Seckill> queryAll(int offset,int limit)
        //java没有保存形参的记录:queryAll( int offset,int limit) ->>queryAll(arg0,arg1)
        //这样写 List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);
		/*
		 * Seckill [seckillId=1000, name=1000元秒杀iPhone8, number=100, startTime=Sun Oct 01 00:00:00 CST 2017, endTime=Mon Oct 02 00:00:00 CST 2017, createTime=Mon Dec 25 17:04:04 CST 2017]
			Seckill [seckillId=1001, name=500元秒杀ipad2017, number=200, startTime=Sun Oct 01 00:00:00 CST 2017, endTime=Mon Oct 02 00:00:00 CST 2017, createTime=Mon Dec 25 17:04:04 CST 2017]
			Seckill [seckillId=1002, name=300元秒杀小米6, number=300, startTime=Sun Oct 01 00:00:00 CST 2017, endTime=Mon Oct 02 00:00:00 CST 2017, createTime=Mon Dec 25 17:04:04 CST 2017]
			Seckill [seckillId=1003, name=200元秒杀红米note4x, number=400, startTime=Sun Oct 01 00:00:00 CST 2017, endTime=Mon Oct 02 00:00:00 CST 2017, createTime=Mon Dec 25 17:04:04 CST 2017]
		 	*/
        List<Seckill> seckills=seckilldao.queryAll(0, 100);
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }

    }

    @Test
    public void testReduceNumber() throws Exception {
        //updateCount=:0
        Date killTime=new Date();
        int updateCount = seckilldao.reduceNumber(1000L, killTime);
        System.out.println("updateCount=:"+updateCount);
    }
}
