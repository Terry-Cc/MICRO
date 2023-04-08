package com.micro.common.web.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: www
 * @description: 接口工具
 * @author: XiongJiaMin
 * @create: 2021-11-29 17:25
 **/
@SuppressWarnings("all")
public class WebUtil {

    /**
     * <p>
     *     获取当前线程属性集合, 通过 ThreadLocal 获取当前线程下的
     *     多线程下请在父线程增加 @RequestInheritableThread 注解
     *     否则子线程获取不到
     * </p>
     * @return 请求属性集合
     */
    public static RequestAttributes getAttributes () {
        return RequestContextHolder.getRequestAttributes();
    }

    /**
     * <p>
     *     获取 请求对象
     * </p>
     * @return request
     */
    public static HttpServletRequest getRequest () {
        return ((ServletRequestAttributes) getAttributes()).getRequest();
    }

    /**
     * <p>
     *     获取请求定位符
     * </p>
     * @return url
     */
    public static String getUrl () {
        return getRequest().getRequestURL().toString();
    }

    /**
     * <p>
     *     获取请求标识符
     * </p>
     * @return uri
     */
    public static String getUri () {
        return getRequest().getRequestURI();
    }

    /**
     * <p>
     *     根据名字获取请求头参数
     * </p>
     * @param name 名字
     * @return 请求头
     */
    public static String getHeaderByName (String name) {
        return getRequest().getHeader(name);
    }

    /**
     * <p>
     *     根据浏览器 Cookies
     * </p>
     * @return Cookies
     */
    public static Cookie[] getCookies () {
        return getRequest().getCookies();
    }

    /**
     * <p>
     *     获取 会话对象
     * </p>
     * @param creatOrNot 是否为空自动新建 一般 false
     * @return 会话对象
     */
    public static HttpSession getSession (boolean creatOrNot) {
        return getRequest().getSession(creatOrNot);
    }

    /**
     * <p>
     *     根据名字获取请求参数
     * </p>
     * @param name 名字
     * @return 请求参数
     */
    public static Object getAttributeByName (String name) {
        return getRequest().getAttribute(name);
    }

    /**
     * <p>
     *     该方法返回请求中的参数部分（参数名+值)
     * </p>
     * @return String
     */
    public static String getQueryString () {
        return getRequest().getQueryString();
    }

    /**
     * <p>
     *     该方法返回请求的客户机的IP地址
     * </p>
     * @return String
     */
    public static String getRemoteAddr () {
        return getRequest().getRemoteAddr();
    }

    /**
     * <p>
     *     该方法返回请求的客户机的完整主机名
     * </p>
     * @return String
     */
    public static String getRemoteHost () {
        return getRequest().getRemoteHost();
    }

    /**
     * <p>
     *     该方法返回客户机所使用的网络端口号
     * </p>
     * @return String
     */
    public static int getRemotePort () {
        return getRequest().getRemotePort();
    }

    /**
     * <p>
     *     该方法返回web服务器所使用的网络端口号
     * </p>
     * @return String
     */
    public static int getLocalPort () {
        return getRequest().getLocalPort();
    }

    /**
     * <p>
     *     该方法返回WEB服务器的IP地址
     * </p>
     * @return String
     */
    public static int getLocalAddr () {
        return getRequest().getLocalPort();
    }

    /**
     * <p>
     *     方法返回WEB服务器的主机名
     * </p>
     * @return String
     */
    public static int getLocalName () {
        return getRequest().getLocalPort();
    }
}
