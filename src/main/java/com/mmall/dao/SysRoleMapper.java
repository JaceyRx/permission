package com.mmall.dao;

import com.mmall.model.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * 获取全部角色
     * @return
     */
    List<SysRole> getAll();

    /**
     * 判断是否重名
     * @param name
     * @param id
     * @return
     */
    int countByName(@Param("name") String name,@Param("id") Integer id);

}