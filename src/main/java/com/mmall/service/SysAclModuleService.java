package com.mmall.service;

import com.mmall.param.AclModuleParam;
import com.mmall.param.DeptParam;

/**
 * 权限模块service接口
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/28 14:11
 */
public interface SysAclModuleService {

    /**
     * 保存接口
     * @param param
     */
    void save(AclModuleParam param);

    /**
     * 更新接口
     * @param param
     */
    void update(AclModuleParam param);


}
