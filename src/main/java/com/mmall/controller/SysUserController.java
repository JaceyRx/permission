package com.mmall.controller;

import com.mmall.common.JsonData;
import com.mmall.param.DeptParam;
import com.mmall.param.UserParam;
import com.mmall.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/26 0:53
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 保存用户
     * @param userParam
     * @return
     */
    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveUser(UserParam userParam) {
        sysUserService.save(userParam);
        return JsonData.success();
    }

    /**
     * 更新用户
     * @param userParam
     * @return
     */
    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateUpdate(UserParam userParam) {
        sysUserService.update(userParam);
        return JsonData.success();
    }

}
