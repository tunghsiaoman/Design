package com.design.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public final class IpAddress {
    public static String getIpAddr(final HttpServletRequest request)
            throws Exception {
        if (request == null) {
            throw (new Exception("getIpAddr method HttpServletRequest Object is null"));
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // Fix ipv6
        if (StringUtils.isNotEmpty(ip) || ip.contains("0:0:0:0:0:0:0:1")) ip = "127.0.0.1";
        return ip;
    }
}
