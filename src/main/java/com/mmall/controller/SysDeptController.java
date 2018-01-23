package com.mmall.controller;

import com.mmall.common.JsonData;
import com.mmall.dto.DeptLevelDTO;
import com.mmall.param.DeptParam;
import com.mmall.service.SysDeptService;
import com.mmall.service.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.awt.print.Pageable;
import java.util.List;

/**
 * 部门controller
 *
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/22 15:06
 */
@Controller
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {

    @Resource
    private SysDeptService sysDeptService;

    @Resource
    private SysTreeService sysTreeService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(DeptParam deptParam) {
        sysDeptService.save(deptParam);
        return JsonData.success();
    }

    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonData tree() {
        List<DeptLevelDTO> dtoList = sysTreeService.deptTree();
        return JsonData.success(dtoList);
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateDept(DeptParam deptParam) {
        sysDeptService.update(deptParam);
        return JsonData.success();
    }

    @RequestMapping("/dept.page")
    public ModelAndView page() {
        return new ModelAndView("/dept");
    }


}
