package com.mmall.service;

import com.mmall.beans.PageQuery;
import com.mmall.beans.PageResult;
import com.mmall.model.SysAcl;
import com.mmall.param.AclParam;

/**
 * 权限点service接口
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/28 21:03
 */
public interface SysAclService {

    /**
     * 保存
     * @param param
     */
    void save(AclParam param);

    /**
     * 更新
     * @param param
     */
    void update(AclParam param);

    /**
     * 根据权限模块id 分页获取权限点
     * @return
     */
    PageResult<SysAcl> getPageByAclModuleId(int aclModuleId, PageQuery pageQuery);


}
