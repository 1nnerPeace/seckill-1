package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
//用的mybatis的param
import org.seckill.entity.Seckill;

public interface SeckillDao {

    /**
     * 秒杀成功后减库存 number-1 ,通过killtime进行秒杀判断
     * killtime对应数据库create_time
     * @return 有返回值 1=(true) 表示减库存成功
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据id查询秒杀对象
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表,例如只查询4个商品
     */
    List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);

}
