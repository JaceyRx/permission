package com.mmall.service;

import com.mmall.model.SysRole;
import com.mmall.param.RoleParam;

import java.util.List;

/**
 * 角色service层
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/2/4 18:08
 */
public interface SysRoleService {

    /**
     *  保存角色
      * @param roleParam
     */
    void save(RoleParam roleParam);

    /**
     * 更新角色
     * @param roleParam
     */
    void update(RoleParam roleParam);

    /**
     * 获取所有角色
     * @return
     */
    List<SysRole> getAll();

}
