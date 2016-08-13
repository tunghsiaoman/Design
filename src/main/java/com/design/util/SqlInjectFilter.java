package com.design.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dongjingwei on 14-9-29.
 */
public class SqlInjectFilter implements Filter {
    private static List<String> invalidsql = new ArrayList<String>();
    private static String error = "/sqlerrors.jsp";
    private static boolean debug = false;

    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain fc) throws IOException, ServletException {
        //防sql关键字注入
        if (debug) {
            System.out.println("prevent sql inject filter works");
        }
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        Map<String, String> params = request.getParameterMap();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            String value = request.getParameter(key);
            if (debug) {
                System.out.println("process params <key, value>: <" + key + ", " + value + ">");
            }
            for (String word : invalidsql) {
                if (word.equalsIgnoreCase(value) || value.contains(word)) {
                    if (value.contains("<")) {
                        value = value.replace("<", "<");
                    }
                    if (value.contains(">")) {
                        value = value.replace(">", ">");
                    }
                    if (value.contains("(")) {
                        value = value.replace("(", "（");
                    }
                    if (value.contains(")")) {
                        value = value.replace(")", "）");
                    }
                    request.getSession().setAttribute("sqlInjectError", "您输入的参数值  \"" + value + "\" 中包含关键字: \"" + word + "\"");
                    response.sendRedirect(request.getContextPath() + error);
                    return;
                }
            }
        }
        fc.doFilter(req, res);
    }

    public void init(FilterConfig conf) throws ServletException {
        String sql = conf.getInitParameter("invalidsql");
        String errorpage = conf.getInitParameter("error");
        String de = conf.getInitParameter("debug");
        if (errorpage != null) {
            error = errorpage;
        }
        if (sql != null) {
            //invalidsql = Arrays.asList(sql.split(" "));
            String[] sqlarr = sql.split(" ");
            for (String sqlword : sqlarr) {
                invalidsql.add(sqlword);
            }
            invalidsql.add("<");
            invalidsql.add(">");
            invalidsql.add("(");
            invalidsql.add(")");
        }
        if (de != null && Boolean.parseBoolean(de)) {
            debug = true;
            System.out.println("PreventSQLInject Filter staring...");
            System.out.println("print filter details");
            System.out.println("invalid words as fllows (split with blank):");
            for (String s : invalidsql) {
                System.out.print(s + " ");
            }
            System.out.println();
            System.out.println("error page as fllows");
            System.out.println(error);
            System.out.println();
        }
    }
}