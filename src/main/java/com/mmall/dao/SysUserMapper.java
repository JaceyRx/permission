package com.mmall.dao;

import com.mmall.beans.PageQuery;
import com.mmall.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 根据用户名或邮箱或手机查找用户
     * @param keyword
     * @return
     */
    SysUser findByKeyword(@Param("keyword") String keyword);

    /**
     * 统计邮箱是否存在
     * @param mail
     * @param id
     * @return
     */
    int countByMail(@Param("mail") String mail,@Param("id") Integer id);

    /**
     * 统计电话是否存在
     * @param telephone
     * @param id
     * @return
     */
    int countByTelephone(@Param("telephone") String telephone,@Param("id") Integer id);

    /**
     * 根据部门id查找是否存在用户
     * @param deptId
     * @return
     */
    int countByDeptId(@Param("deptId") int deptId);

    /**
     * 根据部门id分页获取用户
     * @return
     */
    List<SysUser> getPageByDeptId(@Param("deptId") int deptId,@Param("page") PageQuery page);
}