package com.zen.easyui.enums;

import lombok.Getter;

@Getter
public enum LanguageEnum {

    ZH_CN("zh_CN", "中文"),
    US_EN("us_EN", "英文");

    /**
     * 语言
     */
    private String language;

    /**
     * 语言描述
     */
    private String languageDesc;

    /**
     * 有参构造函数
     *
     * @param language     语言
     * @param languageDesc 语言描述
     */
    LanguageEnum(String language, String languageDesc) {
        this.language = language;
        this.languageDesc = languageDesc;
    }

    /**
     * 根据语言获取语言枚举
     *
     * @param language 语言
     * @return LanguageEnum
     */
    public static LanguageEnum getLanguageEnum(String language) {
        for (LanguageEnum languageEnum : LanguageEnum.values()) {
            if (languageEnum.language.equalsIgnoreCase(language)) {
                return languageEnum;
            }
        }
        return null;
    }

    /**
     * 根据语言获取语言描述
     *
     * @param language 语言
     * @return String
     */
    public static String getLanguageDesc(String language) {
        for (LanguageEnum languageEnum : LanguageEnum.values()) {
            if (languageEnum.language.equalsIgnoreCase(language)) {
                return languageEnum.languageDesc;
            }
        }
        return null;
    }
}
