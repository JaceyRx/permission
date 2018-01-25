package com.mmall.service.impl;

import com.mmall.dao.SysUserMapper;
import com.mmall.exception.ParamException;
import com.mmall.param.UserParam;
import com.mmall.service.SysUserService;
import com.mmall.util.BeanValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/26 1:01
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public void save(UserParam userParam) {
        BeanValidator.check(userParam);
        if (checkEmailExist(userParam.getMail(), userParam.getId())) {
            throw new ParamException("邮箱已被占用");
        }
        if (checkTelephoneExist(userParam.getTelephone(), userParam.getId())) {
            throw new ParamException("电话已被占用");
        }

        String password = "123456";
     }

    /**
     * 检测邮箱是否存在
     * @param maill
     * @param userId
     * @return
     */
    public boolean checkEmailExist(String maill, Integer userId) {
        return false;
    }

    /**
     * 检测电话是否存在
     * @param telephone
     * @param userId
     * @return
     */
    public boolean checkTelephoneExist(String telephone, Integer userId) {
        return false;
    }

}
