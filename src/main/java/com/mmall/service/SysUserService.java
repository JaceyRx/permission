package com.mmall.service;

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

}
