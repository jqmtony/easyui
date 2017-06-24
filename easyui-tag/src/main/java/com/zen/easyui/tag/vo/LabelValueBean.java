package com.zen.easyui.tag.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class LabelValueBean implements Serializable {

    private static final long serialVersionUID = -7969354945746683950L;

    private String pk;

    private String value;

    private String label;

    private String labelValue;

    private String fieldType;

    private String fieldId;

    private String recordFlag;

    private List<Map<String, Object>> fieldParams;// 字段控件对应属性值列表

    public LabelValueBean() {
    }

    public LabelValueBean(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public LabelValueBean(String pk, String label, String value) {
        this.pk = pk;
        this.label = label;
        this.value = value;
    }

}
