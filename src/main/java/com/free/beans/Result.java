package com.free.beans;

public class Result {

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 状态码 0 表示成功
     */

    private Integer code;
    /**
     * 数据
     */
    private Object data;
    /**
     * 描述
     */
    private String msg;

    public Result() {
    }

    public Result(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 成功，不传入数据
     *
     * @return
     */
    public static Result buildSuccess() {
        return new Result(0, null, null);
    }

    /**
     * 成功，传入数据
     *
     * @param data
     * @return
     */
    public static Result buildSuccess(Object data) {
        return new Result(0, data, null);
    }

    /**
     * 成功，传入数据
     *
     * @param msg
     * @return
     */
    public static Result buildSuccess(String msg) {
        return new Result(0, null, msg);
    }

    /**
     * 成功，传入数据和描述信息
     * @param msg
     * @return
     */
    public static Result buildSuccess(Object data, String msg) {
        return new Result(0, data, msg);
    }

    /**
     * 失败，传入描述信息
     *
     * @param msg
     * @return
     */
    public static Result buildError(String msg) {
        return new Result(-1, null, msg);
    }


    /**
     * 自定义状态码和错误信息
     *
     * @param code
     * @param msg
     * @return
     */
    public static Result buildCodeAndMsg(int code, String msg) {
        return new Result(code, null, msg);
    }
}
