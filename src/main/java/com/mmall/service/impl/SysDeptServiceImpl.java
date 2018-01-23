package com.mmall.service.impl;

import com.google.common.base.Preconditions;
import com.mmall.dao.SysDeptMapper;
import com.mmall.exception.ParamException;
import com.mmall.model.SysDept;
import com.mmall.param.DeptParam;
import com.mmall.service.SysDeptService;
import com.mmall.util.BeanValidator;
import com.mmall.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/22 15:11
 */
@Service("sysDeptService")
public class SysDeptServiceImpl implements SysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Override
    public void save(DeptParam deptParam) {
        BeanValidator.check(deptParam);
        if (checkExist(deptParam.getParentId(), deptParam.getName(), deptParam.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        // 构造参数
        SysDept dept = SysDept.builder().name(deptParam.getName())
                .parentId(deptParam.getParentId())
                    .seq(deptParam.getSeq())
                        .remark(deptParam.getRemark()).build();

        // 计算设置当前部门层级Level
        // 根据上级id获取上级层级，并计算出当前层级
        dept.setLevel(LevelUtil.calculateLevel(getLevel(deptParam.getParentId()), deptParam.getParentId()));
        dept.setOperator("system"); //TODO
        dept.setOperateIp("127.0.0.1"); //TODO
        dept.setOperateTime(new Date());

        sysDeptMapper.insertSelective(dept);
    }

    @Override
    public void update(DeptParam deptParam) {
        BeanValidator.check(deptParam);
        if (checkExist(deptParam.getParentId(), deptParam.getName(), deptParam.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        // 更新前
        SysDept before = sysDeptMapper.selectByPrimaryKey(deptParam.getId());
        Preconditions.checkNotNull(before, "待更新的部门不存在");

        // 更新后
        SysDept after = SysDept.builder().id(deptParam.getId())
                .name(deptParam.getName())
                .parentId(deptParam.getParentId())
                .seq(deptParam.getSeq())
                .remark(deptParam.getRemark()).build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(deptParam.getParentId()), deptParam.getParentId()));
        after.setOperator("system-update"); //TODO
        after.setOperateIp("127.0.0.1"); //TODO
        after.setOperateTime(new Date());

        // 更新其下的子节点
        updateWithChild(before, after);
    }

    /**
     * 更新子节点(未完)
     * @param befor   之前的部门
     * @param after   之后的部门
     */
    @Transactional   // 事务
    private void updateWithChild(SysDept befor, SysDept after) {

        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = befor.getLevel();
        // 如果层级不变的话，不更新子节点
        if (!newLevelPrefix.equals(oldLevelPrefix)) {
            List<SysDept> deptList = sysDeptMapper.getChildDeptListByLevel(oldLevelPrefix);
            if (CollectionUtils.isNotEmpty(deptList)) {
                for (SysDept dept : deptList) {
                    String level = dept.getLevel();
                    // 更新每个子节点，判断该节点是否在该部门的子节点
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dept.setLevel(level);
                    }
                }
                // 批量更新level
                sysDeptMapper.batchUpdateLevel(deptList);
            }
        }

        sysDeptMapper.updateByPrimaryKey(after);

    }

    /***
     * 判断是否存在同级重复部门
     * @param parentId
     * @param depName
     * @param deptId
     * @return
     */
    private boolean checkExist(Integer parentId, String depName, Integer deptId) {
        return sysDeptMapper.countByNameAndParentId(parentId, depName, deptId) > 0;
    }

    /**
     * 获取上级的层级Level
     * @param deptId
     * @return
     */
    private String getLevel(Integer deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (dept == null) {
            return null;
        }
        return dept.getLevel();
    }



}
