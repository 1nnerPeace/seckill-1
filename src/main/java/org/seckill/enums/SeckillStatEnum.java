package org.seckill.enums;
/**
 * 使用枚举表述常量数据字典,
 * state和stateInfo参数信息应该是输出给前端的，
 * 但是我们不想在我们的return代码中硬编码这两个参数
 * 所以我们应该考虑用枚举的方式将这些常量封装起来创建一个枚举类型:
 */

public enum SeckillStatEnum {

    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATE_REWRITE(-3,"数据篡改");

    private int state;
    private String info;

    SeckillStatEnum(int state, String info) {
        this.state = state;
        this.info = info;
    }

    public int getState() {
        return state;
    }


    public String getInfo() {
        return info;
    }


    public static SeckillStatEnum stateOf(int index)
    {
        for (SeckillStatEnum state : values())
        {
            if (state.getState()==index)
            {
                return state;
            }
        }
        return null;
    }
}
