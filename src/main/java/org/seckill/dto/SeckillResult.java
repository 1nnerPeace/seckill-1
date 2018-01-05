package org.seckill.dto;

/**
 * VO 视图对象，用于展示层，它的作用是把某个指定页面（或组件）的所有数据封装起来。
 *所有ajax请求返回类型,封装json结果
 * @param <T>
 */
public class SeckillResult<T> {

    private boolean success;//true or false
    private T data;//数据
    private String error;//错误



    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }



    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }



    public boolean isSuccess() {
        return success;
    }



    public void setSuccess(boolean success) {
        this.success = success;
    }



    public T getData() {
        return data;
    }



    public void setData(T data) {
        this.data = data;
    }



    public String getError() {
        return error;
    }



    public void setError(String error) {
        this.error = error;
    }


}
