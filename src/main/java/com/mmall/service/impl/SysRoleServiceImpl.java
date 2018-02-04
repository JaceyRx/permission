package com.mmall.service.impl;

import com.google.common.base.Preconditions;
import com.mmall.common.RequestHolder;
import com.mmall.dao.SysRoleMapper;
import com.mmall.exception.ParamException;
import com.mmall.model.SysRole;
import com.mmall.param.RoleParam;
import com.mmall.service.SysRoleService;
import com.mmall.util.BeanValidator;
import com.mmall.util.IpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/2/4 18:09
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;


    @Override
    public void save(RoleParam roleParam) {
        BeanValidator.check(roleParam);
        if (checkExist(roleParam.getName(), roleParam.getId())) {
            throw new ParamException("角色名称已经存在");
        }
        SysRole role = SysRole.builder().name(roleParam.getName()).status(roleParam.getStatus()).type(roleParam.getType())
                .remark(roleParam.getRemark()).build();
        role.setOperator(RequestHolder.getCurrentUser().getUsername());
        role.setOperateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
        role.setOperateTime(new Date());
        sysRoleMapper.insertSelective(role);
    }

    @Override
    public void update(RoleParam roleParam) {
        BeanValidator.check(roleParam);
        if (checkExist(roleParam.getName(), roleParam.getId())) {
            throw new ParamException("角色名称已经存在");
        }
        SysRole before = sysRoleMapper.selectByPrimaryKey(roleParam.getId());
        Preconditions.checkNotNull(before, "待更新的角色不存在");

        SysRole after = SysRole.builder().id(roleParam.getId()).name(roleParam.getName()).status(roleParam.getStatus()).type(roleParam.getType())
                .remark(roleParam.getRemark()).build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
        after.setOperateTime(new Date());

        sysRoleMapper.updateByPrimaryKeySelective(after);
    }

    @Override
    public List<SysRole> getAll() {
        return sysRoleMapper.getAll();
    }


    /**
     * 检测是否出现同名冲突
     * @param name
     * @param id
     * @return
     */
    private boolean checkExist(String name, Integer id) {
        return sysRoleMapper.countByName(name, id) > 0;
    }

}
