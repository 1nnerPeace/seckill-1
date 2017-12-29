package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
//用的mybatis的param
import org.seckill.entity.Seckill;

public interface SeckillDao {

    /**
     * 减库存
     * killtime对应数据库create_time
     * @return 如果返回值>1,表示更新的记录行数
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据id查询秒杀对象
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     */
    List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);

}
