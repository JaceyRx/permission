package com.mmall.service.impl;

import com.google.common.base.Preconditions;
import com.mmall.dao.SysUserMapper;
import com.mmall.exception.ParamException;
import com.mmall.model.SysUser;
import com.mmall.param.UserParam;
import com.mmall.service.SysUserService;
import com.mmall.util.BeanValidator;
import com.mmall.util.MD5Util;
import com.mmall.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

        String password = PasswordUtil.randomPassword();

        password = "123456";
        // MD5 加密
        String encryptedPassword = MD5Util.encrypt(password);
        SysUser sysUser = SysUser.builder().mail(userParam.getMail())
                .telephone(userParam.getTelephone()).username(userParam.getUsername())
                .deptId(userParam.getDeptId()).status(userParam.getStatus()).password(encryptedPassword)
                .remark(userParam.getRemark()).build();
        sysUser.setOperator("system"); //TODO
        sysUser.setOperateIp("127.0.0.1"); //TODO
        sysUser.setOperateTime(new Date());

        // TODO: 要有发送邮件通知用户操作，告诉其密码

        sysUserMapper.insertSelective(sysUser);
     }

    public void update(UserParam userParam) {
         BeanValidator.check(userParam);
         if (checkEmailExist(userParam.getMail(), userParam.getId())) {
             throw new ParamException("邮箱已被占用");
         }
         if (checkTelephoneExist(userParam.getTelephone(), userParam.getId())) {
             throw new ParamException("电话已被占用");
         }
         SysUser before = sysUserMapper.selectByPrimaryKey(userParam.getId());
         Preconditions.checkNotNull(before, "待更新的用户不存在");
         SysUser after = SysUser.builder().id(userParam.getId()).mail(userParam.getMail())
                 .telephone(userParam.getTelephone()).username(userParam.getUsername())
                 .deptId(userParam.getDeptId()).status(userParam.getStatus())
                 .remark(userParam.getRemark()).build();
         sysUserMapper.updateByPrimaryKeySelective(after);
    }

    @Override
    public SysUser findByKeyword(String keyword) {
        return sysUserMapper.findByKeyword(keyword);
    }

    /**
     * 检测邮箱是否存在
     * @param maill
     * @param userId
     * @return
     */
    public boolean checkEmailExist(String maill, Integer userId) {
        return sysUserMapper.countByMail(maill,userId) > 0;
    }

    /**
     * 检测电话是否存在
     * @param telephone
     * @param userId
     * @return
     */
    public boolean checkTelephoneExist(String telephone, Integer userId) {
        return sysUserMapper.countByTelephone(telephone,userId) > 0;
    }

}
