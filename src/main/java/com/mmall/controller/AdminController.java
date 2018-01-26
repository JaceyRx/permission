package com.mmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 管理员controller
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/26 11:11
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/index.page")
    public ModelAndView index() {
        return new ModelAndView("admin");
    }

}
