package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {

    /**
     * 记录购买明细,可过滤重复,用了联合主键(seckillId ,userphone),防止一个手机号多次购买一个商品
     * @return  插入成功的话返回1 (true)否则为0;
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据id和手机号查询SuccessKilled
     * 并携带秒杀产品对象实体
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

}
