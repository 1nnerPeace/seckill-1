package org.seckill.entity;

import java.util.Date;

public class SuccessKilled {

    private long seckillId;
    private long userPhone;
    private short state;
    private Date createTime;
    //多对一.
    //秒杀成功后一个对象可以查询出对应商品的具体信息
    private Seckill seckill;

    public Seckill getSeckill() {
        return seckill;
    }
    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }
    public long getSeckillId() {
        return seckillId;
    }
    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }
    public long getUserPhone() {
        return userPhone;
    }
    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }
    public short getState() {
        return state;
    }
    public void setState(short state) {
        this.state = state;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Override
    public String toString() {
        return "SuccessKilled [seckillId=" + seckillId + ", userPhone=" + userPhone + ", state=" + state
                + ", createTime=" + createTime + "]";
    }

}
