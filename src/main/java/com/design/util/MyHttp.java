package com.design.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: dongjingwei
 * Date: 14-6-4
 * Time: 下午3:11
 * To change this template use File | Settings | File Templates.
 */
public class MyHttp {

    public static HttpServletRequest getHttpServletRequest() {
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            return servletRequestAttributes.getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获得当前服务器前缀
     *
     * @return
     */
    public static String getServerPath() {
        HttpServletRequest request = MyHttp.getHttpServletRequest();
        // 生成连接地址
        String domain = new StringBuilder(request.getScheme()).append("://").append(request.getServerName())
                .append((request.getServerPort() != 80) ? ":" + request.getServerPort() : "").toString();
        return domain;
    }
}
