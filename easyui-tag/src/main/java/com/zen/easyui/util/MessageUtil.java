package com.zen.easyui.util;

import com.zen.easyui.enums.LanguageEnum;
import com.zen.easyui.web.SessionContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
public class MessageUtil {

    protected static Locale locale;

    private static ReloadableResourceBundleMessageSource messageSource;

    /**
     * 通过language获取Locale
     */
    public static Locale getLocale(String language) {
        Locale locale;
        LanguageEnum languageEnum = LanguageEnum.getLanguageEnum(language);
        if (null == languageEnum) {
            return Locale.getDefault();
        }
        switch (languageEnum) {
            case ZH_CN:
                locale = Locale.SIMPLIFIED_CHINESE;
                break;
            case US_EN:
                locale = Locale.US;
                break;
            default:
                locale = Locale.getDefault();
        }
        return locale;
    }

    /**
     * 获取资源属性名称
     *
     * @param language
     * @param messageId
     * @param defaultMessage
     * @return
     */
    public String getMessage(String language, String messageId, String defaultMessage) {
        return getMessage(getLocale(language), messageId, null, defaultMessage);
    }

    /**
     * 获取资源属性名称
     *
     * @param language
     * @param messageId
     * @return
     */
    public static String getMessage(String language, String messageId) {
        return getMessage(getLocale(language), messageId, null);
    }

    /**
     * 获取资源属性名称
     *
     * @param language
     * @param messageId
     * @param args
     * @param defaultMessage
     * @return
     */
    public String getMessage(String language, String messageId, Object[] args, String defaultMessage) {
        return getMessage(getLocale(language), messageId, args, defaultMessage);
    }

    /**
     * 获取资源属性名称
     *
     * @param language
     * @param messageId
     * @param args
     * @return
     */
    public String getMessage(String language, String messageId, Object[] args) {
        return getMessage(getLocale(language), messageId, args);
    }

    /**
     * 获取资源属性名称
     */

    public String getMessage(Locale locale, String messageId, Object[] args, String defaultMessage) {
        String message = messageSource.getMessage(messageId, args, defaultMessage, locale);

        return !RegulationUtil.isEmpty(message) ? message.trim() : message;
    }

    /**
     * 获取资源属性名称
     */
    public static String getMessage(Locale locale, String messageId, Object[] args) {
        String message = messageSource.getMessage(messageId, args, locale);

        return !RegulationUtil.isEmpty(message) ? message.trim() : message;
    }

    /**
     * 通过spring bean注入
     *
     * @param messageSource
     */
    public void setMessageSource(ReloadableResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // Locale Name Locale
    // =======================================
    // Locale.CHINA zh_CN
    // Locale.CHINESE zh
    // Locale.SIMPLIFIED_CHINESE zh_CN
    // Locale.TRADITIONAL_CHINESE zh_TW
    // Locale.PRC zh_CN
    // Locale.TAIWAN zh_TW
    // Locale.ENGLISH en
    // Locale.UK en_GB
    // Locale.US en_US
    // Locale.FRANCE fr_FR
    // Locale.FRENCH fr

    /**
     * 获取资源属性名称
     *
     * @param ServletRequest
     * @param messageId
     * @return
     */
    public static String getMessage(ServletRequest ServletRequest, String messageId) {
        return getMessage(ServletRequest, messageId, null);
    }

    public static String getMessage(ServletRequest ServletRequest, String messageId, Object[] args) {
        String msg = "";
        HttpServletRequest request = (HttpServletRequest) ServletRequest;
        locale = SessionContainer.getUserLocale(request);
        try {
            msg = RequestContextUtils.findWebApplicationContext(request).getMessage(messageId, args, locale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
}
