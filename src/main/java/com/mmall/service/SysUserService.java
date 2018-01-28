package com.mmall.service;

import com.mmall.beans.PageQuery;
import com.mmall.beans.PageResult;
import com.mmall.model.SysUser;
import com.mmall.param.UserParam;

/**
 * user service层
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/26 1:00
 */
public interface SysUserService {

    /**
     * 保存用户
     * @param userParam
     */
    void save(UserParam userParam);

    /**
     * 更新用户
     * @param userParam
     */
    void update(UserParam userParam);

    /**
     * 根据电话或email查找用户
     * @param keyword
     * @return
     */
    SysUser findByKeyword(String keyword);

    /**
     * 根据部门id 分页获取用户
     * @return
     */
    PageResult<SysUser> getPageByDeptId(int deptId, PageQuery pageQuery);

}
