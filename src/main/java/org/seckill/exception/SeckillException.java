package org.seckill.exception;
/**
 *所有秒杀相关业务异常
 *
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillException(String message) {
        super(message);
    }

}
