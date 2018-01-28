package com.mmall.service;

import com.mmall.dto.AclModuleLevelDTO;
import com.mmall.dto.DeptLevelDTO;

import java.util.List;

/**
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/22 16:04
 */
public interface SysTreeService {

    /**
     * 部门树
     * @return
     */
    List<DeptLevelDTO> deptTree();

    /**
     * 权限模块树
     * @return
     */
    List<AclModuleLevelDTO> aclModuleTree();

}
