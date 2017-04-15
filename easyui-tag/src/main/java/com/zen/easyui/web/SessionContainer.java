package com.zen.easyui.web;

import com.zen.easyui.constant.GlobalConstant;
import com.zen.easyui.util.TriRegulation;
import com.zen.easyui.util.MessageUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;


public class SessionContainer {

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 设置用户当前语言
     */
    public static void saveUserLanguage(HttpServletRequest request, String language) {
        request.getSession().setAttribute(GlobalConstant.LANGUAGE_KEY, TriRegulation.isEmpty(language) ? GlobalConstant.DEFAULT_LANGUAGE : language);

    }

    /**
     * 获取用户语言
     *
     * @param request
     * @return
     */
    public static String getUserLanguage(HttpServletRequest request) {
        String defaultLanguage = GlobalConstant.DEFAULT_LANGUAGE;
        if (request.getSession().getAttribute(GlobalConstant.LANGUAGE_KEY) != null) {
            defaultLanguage = (String) request.getSession().getAttribute(GlobalConstant.LANGUAGE_KEY);
        }
        return defaultLanguage;
    }

    /**
     * 设置用户当前语言  中文、英文
     *
     * @param request
     * @param language
     */
    public static void saveUserLanguageDesc(HttpServletRequest request, String languageDesc) {
        request.getSession().setAttribute(GlobalConstant.LANGUAGE_KEY_DESC, languageDesc);
    }

    /**
     * 获取用户当前语言描述 中文、英文
     *
     * @param request
     * @return
     */
    public static String getUserLanguageDesc(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(GlobalConstant.LANGUAGE_KEY_DESC);
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
