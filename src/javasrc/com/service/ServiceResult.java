package com.service;

/**
 * Created by Tinyhome on 16/8/26.
 */
public class ServiceResult {
    private String msg="";  // success error 0 1
    private String code=""; // 返回具体的出错数值或者信息

    public ServiceResult(String _msg,String _code){
        this.setCode(_code);
        this.setMsg(_msg);
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
