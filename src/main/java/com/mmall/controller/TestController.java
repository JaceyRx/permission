package com.mmall.controller;

import com.mmall.common.ApplicationContextHelper;
import com.mmall.common.JsonData;
import com.mmall.dao.SysAclModuleMapper;
import com.mmall.dao.TestDAO;
import com.mmall.exception.ParamException;
import com.mmall.exception.PermissionException;
import com.mmall.model.SysAclModule;
import com.mmall.param.TestVo;
import com.mmall.util.BeanValidator;
import com.mmall.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * controller测试类
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/20 13:43
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {


    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        log.info("hello");
//        throw new PermissionException("Test exception");
        return JsonData.success("Hello, permission");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVo vo) throws ParamException{
        log.info("validate");
//        try {
//            Map<String, String> map = BeanValidator.validateObject(vo);
//            if (!MapUtils.isEmpty(map)) {
//                for (Map.Entry<String, String> entry: map.entrySet()) {
//                    log.info("{} -> {}", entry.getKey(), entry.getValue());
//                }
//            }
//        } catch (Exception e) {
//
//        }

        SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
        SysAclModule module = moduleMapper.selectByPrimaryKey(1);
        log.info(JsonMapper.obj2String(module));
        // 参数校验
        BeanValidator.check(vo);

        return JsonData.success("test, validate");
    }

}
