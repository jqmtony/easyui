package com.zen.easyui.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;

@Slf4j
public class ObjectHelper {

    /**
     * 把字符串第一个字符大写.
     *
     * @param fieldName 字段名
     * @return String
     */
    public static String upperFirstChar(String fieldName) {
        if (fieldName == null)
            return null;
        return fieldName.length() > 0 ? Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1) : "";
    }

    /**
     * 获取GET方法名称，如getDeployId.
     *
     * @param fieldName 字段名
     * @return String
     */
    public static String getGetMethodName(String fieldName) {
        return "get" + upperFirstChar(fieldName);
    }


    /**
     * 获取get方法调用得到的值.
     *
     * @param bean      实体对象
     * @param getMethod get方法
     * @return Object
     */
    public static Object getGetMethodValue(Object bean, String getMethod) {
        try {
            Method method = bean.getClass().getMethod(getMethod);
            return method.invoke(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取字段属性值
     *
     * @param obj       对象
     * @param fieldName 字段名
     * @return Object
     */
    public static Object getFieldValue4Object(Object obj, String fieldName) {
        if (obj instanceof Map) {
            return getFieldValue4Map((Map) obj, fieldName);
        } else if (obj instanceof String) {
            return obj;
        } else {
            return getFieldValue4Bean(obj, fieldName);
        }
    }

    /**
     * 获取BEAN字段属性值
     *
     * @param bean      实体对象
     * @param fieldName 字段名
     * @return Object
     */
    public static Object getFieldValue4Bean(Object bean, String fieldName) {
        String getMethod = getGetMethodName(fieldName);
        return getGetMethodValue(bean, getMethod);
    }

    /**
     * 获取Map字段属性值
     *
     * @param map       map对象
     * @param fieldName 字段名
     * @return Object
     */
    public static Object getFieldValue4Map(Map map, String fieldName) {
        return map.get(fieldName);
    }

    /**
     * 获取对象（判断是否为空,空则返回"")
     *
     * @param o 对象
     * @return Object
     */
    public static Object getValue(Object o) {
        return !RegulationUtil.isEmpty(o) ? o : "";
    }

    /**
     * 获取对象（如果为空，则返回defaultObj)
     *
     * @param o          对象
     * @param defaultObj 默认值
     * @return Object
     */
    public static Object getValue(Object o, Object defaultObj) {
        return !RegulationUtil.isEmpty(o) ? o : defaultObj;
    }

}
