package com.zen.easyui.web;

import com.zen.easyui.constant.EasyuiTagGlobalConstant;
import com.zen.easyui.util.MessageUtil;
import com.zen.easyui.util.RegulationUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
public class SessionContainer {

    /**
     * 设置用户当前语言
     */
    public static void saveUserLanguage(HttpServletRequest request, String language) {
        request.getSession().setAttribute(EasyuiTagGlobalConstant.LANGUAGE_KEY, RegulationUtil.isEmpty(language) ? EasyuiTagGlobalConstant.DEFAULT_LANGUAGE : language);

    }

    /**
     * 获取用户语言
     *
     * @param request
     * @return
     */
    public static String getUserLanguage(HttpServletRequest request) {
        String defaultLanguage = EasyuiTagGlobalConstant.DEFAULT_LANGUAGE;
        if (request.getSession().getAttribute(EasyuiTagGlobalConstant.LANGUAGE_KEY) != null) {
            defaultLanguage = (String) request.getSession().getAttribute(EasyuiTagGlobalConstant.LANGUAGE_KEY);
        }
        return defaultLanguage;
    }

    /**
     * 设置用户当前语言  中文、英文
     *
     * @param request
     * @param languageDesc
     */
    public static void saveUserLanguageDesc(HttpServletRequest request, String languageDesc) {
        request.getSession().setAttribute(EasyuiTagGlobalConstant.LANGUAGE_KEY_DESC, languageDesc);
    }

    /**
     * 获取用户当前语言描述 中文、英文
     *
     * @param request
     * @return
     */
    public static String getUserLanguageDesc(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(EasyuiTagGlobalConstant.LANGUAGE_KEY_DESC);
    }

    /**
     * 获取用户语言Locale
     *
     * @param request
     * @return
     */
    public static Locale getUserLocale(HttpServletRequest request) {
        return MessageUtil.getLocale(getUserLanguage(request));
    }

}
