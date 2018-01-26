package com.mmall.controller;

import com.mmall.model.SysUser;
import com.mmall.service.SysUserService;
import com.mmall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录controller
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/26 10:41
 */
@Controller
public class UserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/login.page")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        SysUser sysUser = sysUserService.findByKeyword(username);
        // 返回给前端的错误信息
        String errorMsg = "";
        /**
         * 有时用户跳到登陆页时，有可能访问某个页面时，需要没有权限，需要登录才能访问
         * 这时跳转到登录页面，登录之后要跳转到上次访问的地方，而不是固定首页
         * 这时这个 ret 就起到作用了
         */
        String ret = request.getParameter("ret");

        if (StringUtils.isBlank(username)) {
            errorMsg = "用户名不可以为空";
        } else if (StringUtils.isBlank(username)) {
            errorMsg = "密码不可以为空";
        } else if (sysUser == null) {
            errorMsg = "查询不到指定的用户";
        }else if (!MD5Util.encrypt(password).equals(sysUser.getPassword())) {
            errorMsg = "用户名或密码错误";
        } else if (sysUser.getStatus() != 1) {
            errorMsg = "用户已被冻结，请联系管理员";
        } else {
            // login success
            request.getSession().setAttribute("user", sysUser);
            if (StringUtils.isNoneBlank(ret)) {
                response.sendRedirect(ret);
            } else {
                //TODO
                response.sendRedirect("/admin/index.page");
            }
            return;
        }

        request.setAttribute("error", errorMsg);
        request.setAttribute("username", username);
        if (StringUtils.isNotBlank(ret)) {
            request.setAttribute("ret", request);
        }
        String path = "signin.jsp";
        request.getRequestDispatcher(password).forward(request, response);
    }

    @RequestMapping("/logout.page")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        String path = "signin.jsp";
        response.sendRedirect(path);
    }

}
