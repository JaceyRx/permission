package com.mmall.dao;

import com.mmall.model.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    /**
     * 获取全部部门
     * @return
     */
    List<SysDept> getAllDept();

    /**
     * 根据层级level 查找子节点
     * @param level
     * @return
     */
    List<SysDept> getChildDeptListByLevel(@Param("level") String level);

    /**
     * 批量更新部门层级
     * @param sysDeptList
     */
    void batchUpdateLevel(@Param("sysDeptList") List<SysDept> sysDeptList);

    /**
     * 根据部门名和父级id 查找在该层级下是否存在相同部门
     * @param parentId 父级id
     * @param name  部门名
     * @param id    部门id (可为null)
     * @return
     */
    int countByNameAndParentId(@Param("parentId") Integer parentId,@Param("name") String name, @Param("id") Integer id);
}