package com.mmall.service.impl;

import com.google.common.base.Preconditions;
import com.mmall.dao.SysDeptMapper;
import com.mmall.exception.ParamException;
import com.mmall.model.SysDept;
import com.mmall.param.DeptParam;
import com.mmall.service.SysDeptService;
import com.mmall.util.BeanValidator;
import com.mmall.util.LevelUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

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
        SysDept before = sysDeptMapper.selectByPrimaryKey(deptParam.getId());
        Preconditions.checkNotNull(before, "待更新的部门不存在");

        SysDept after = SysDept.builder().id(deptParam.getId())
                .name(deptParam.getName())
                .parentId(deptParam.getParentId())
                .seq(deptParam.getSeq())
                .remark(deptParam.getRemark()).build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(deptParam.getParentId()), deptParam.getParentId()));
        after.setOperator("system-update"); //TODO
        after.setOperateIp("127.0.0.1"); //TODO
        after.setOperateTime(new Date());

        updateWithChild(before, after);
    }

    /**
     * 更新子节点(未完)
     * @param befor
     * @param after
     */
    @Transactional
    private void updateWithChild(SysDept befor, SysDept after) {
        //TODO
    }

    /***
     * 判断是否存在同级部门
     * @param parentId
     * @param depName
     * @param deptId
     * @return
     */
    private boolean checkExist(Integer parentId, String depName, Integer deptId) {
        //TODO: 判断是否存在同级部门
        return true;
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
