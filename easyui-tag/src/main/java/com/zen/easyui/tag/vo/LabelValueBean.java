package com.zen.easyui.tag.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author hexuming
 */
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

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public List<Map<String, Object>> getFieldParams() {
        return fieldParams;
    }

    public void setFieldParams(List<Map<String, Object>> fieldParams) {
        this.fieldParams = fieldParams;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getRecordFlag() {
        return recordFlag;
    }

    public void setRecordFlag(String recordFlag) {
        this.recordFlag = recordFlag;
    }


}
