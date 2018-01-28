package com.mmall.common;

import com.mmall.model.SysUser;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/27 23:00
 */
public class RequestHolder {

    /**
     * ThreadLocal 相当于map 而里面的key，是当前进程
     * 所以当我们去取里面的值时，都是取到当前线程的内容
     * 在高并发的时候，很有用
     */
    public static final ThreadLocal<SysUser> userHolder = new ThreadLocal<SysUser>();

    public static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();

    public static void add(SysUser sysUser) {
        userHolder.set(sysUser);
    }

    public static void add(HttpServletRequest request) {
        requestHolder.set(request);
    }

    public static SysUser getCurrentUser() {
        return userHolder.get();
    }

    public static HttpServletRequest getCurrentRequest() {
        return requestHolder.get();
    }

    public static void remove() {
        userHolder.remove();
        requestHolder.remove();
    }



}
