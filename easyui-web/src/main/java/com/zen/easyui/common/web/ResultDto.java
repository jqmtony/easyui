package com.zen.easyui.common.web;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回结果对象
 *
 * @author zen E-mail: xinjingziranchan@gmail.com
 * @version 1.0.0
 * @ClassName ResultDto.java
 * @Date 2017年3月17日 上午9:40:32
 */
@Data
public class ResultDto<T> implements Serializable {

    private static final long serialVersionUID = 4907893237411537857L;

    /**
     * 成功标识
     */
    private boolean success = true;

    /**
     * 返回状态码
     */
    private String code = "0";

    /**
     * 返回描述信息
     */
    private String msg = "操作成功";

    /**
     * 返回数据
     */
    private T data;

}
