package com.mmall.controller;

import com.mmall.common.JsonData;
import com.mmall.param.RoleParam;
import com.mmall.service.SysRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 角色controller
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/2/4 18:07
 */
@Controller
@RequestMapping("/sys/role")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 角色管理页
     * @return
     */
    @RequestMapping("/role.page")
    private ModelAndView page() {
        return new ModelAndView("role");
    }

    /**
     * 保存角色
     * @param param
     * @return
     */
    @PostMapping("/save.json")
    @ResponseBody
    private JsonData SaveRole(RoleParam param) {
        sysRoleService.save(param);
        return JsonData.success();
    }

    /**
     * 更新角色
     * @param param
     * @return
     */
    @PostMapping("/update.json")
    @ResponseBody
    private JsonData UpdateRole(RoleParam param) {
        sysRoleService.update(param);
        return JsonData.success();
    }

    /**
     * 获取角色列表
     * @return
     */
    @GetMapping("/list.json")
    @ResponseBody
    private JsonData list() {
        return JsonData.success(sysRoleService.getAll());
    }


}
