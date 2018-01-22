package com.mmall.service;

import com.mmall.param.DeptParam;
import org.springframework.stereotype.Service;

/**
 * 部门service层
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/22 15:09
 */
public interface SysDeptService {

    /**
     * 保存接口
     * @param deptParam
     */
    void save(DeptParam deptParam);

    /**
     * 更新接口
     * @param deptParam
     */
    void update(DeptParam deptParam);

}
