package com.fcbox.easyui.common;

import java.io.Serializable;

public class ResultDto<T> implements Serializable {

    private static final long serialVersionUID = 4907893237411537857L;

    private boolean           success          = true;
    private String            code             = "0";
    private String            msg              = "操作成功";
    private T                 data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
