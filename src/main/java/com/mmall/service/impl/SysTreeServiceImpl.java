package com.mmall.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.mmall.dao.SysAclModuleMapper;
import com.mmall.dao.SysDeptMapper;
import com.mmall.dto.AclModuleLevelDTO;
import com.mmall.dto.DeptLevelDTO;
import com.mmall.model.SysAclModule;
import com.mmall.model.SysDept;
import com.mmall.service.SysTreeService;
import com.mmall.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/22 16:07
 */
@Service("sysTreeService")
public class SysTreeServiceImpl implements SysTreeService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysAclModuleMapper sysAclModuleMapper;

    /**
     * 部门树
     * @return
     */
    @Override
    public List<DeptLevelDTO> deptTree() {
        List<SysDept> deptList = sysDeptMapper.getAllDept();

        List<DeptLevelDTO> dtoList = deptList.stream()
                .map(e -> DeptLevelDTO.adapt(e)).collect(Collectors.toList());

        return deptListToTree(dtoList);
    }

    /**
     * 权限模块树
     * @return
     */
    @Override
    public List<AclModuleLevelDTO> aclModuleTree() {
        // 获取全部权限模块
        List<SysAclModule> aclModuleList = sysAclModuleMapper.getAllAclModule();

        // 转成dto list
        List<AclModuleLevelDTO> dtoList = aclModuleList.stream()
                .map(e -> AclModuleLevelDTO.adapt(e)).collect(Collectors.toList());

        return aclModuleListToTree(dtoList);
    }


    /**================================<<部门树内容>>=======================================*/
    /**
     * List 转树
     * @param deptLevelList
     * @return
     */
    public List<DeptLevelDTO> deptListToTree(List<DeptLevelDTO> deptLevelList) {
        // 判断是否为null，如果为null，则返回空的List回去
        if (CollectionUtils.isEmpty(deptLevelList)) {
            return Lists.newArrayList();
        }
        // level -> [dept1, dept2, dept3, .....]
        /**
         * 谷歌提供的高级的数据结构 Multimap
         * 用法跟Map类似，但put的值，如果key已经存在，将会以List的形式被追加储存
         * 其储存值将会被转化为  Multimap<String, List<Object>> 的形式
         */
        Multimap<String, DeptLevelDTO> levelDeptMap = ArrayListMultimap.create();
        List<DeptLevelDTO> rootList = Lists.newArrayList();

        // 遍历全部部门找出根节点部门List，后面作为生成部门树的初始List
        for (DeptLevelDTO dto : deptLevelList) {
            levelDeptMap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }
        // 按照seq从小到大排序
        Collections.sort(rootList, new Comparator<DeptLevelDTO>() {
            @Override
            public int compare(DeptLevelDTO o1, DeptLevelDTO o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });
        // 递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }

    // level: 0, 1, all 0->0.1, 0.2
    // level: 0.1
    // level: 0.2
    /**
     * 递归生成部门树节点
     * @param deptLevelList 同级部门列表
     * @param level         当前部门层级level
     * @param levelDeptMap  经过整理后的全部部门Map, 根据level分组
     */
    public void transformDeptTree(List<DeptLevelDTO> deptLevelList, String level, Multimap<String, DeptLevelDTO> levelDeptMap) {
        /**
         * 1. 遍历同级部门列表的每一项
         * 2. 根据当前部门的 level 计算出该部门的子部门的level
         * 3. 根据子部门的level ，查找 Multimap 获取子部门列表
         * 4. 子部门列表存在，将其设置进当前部门的 DeptLevelDTOList 里
         * 5. 子部门列表，子部门level 和 Multimap 作为参数继续递归，查找各自部门下，是否还有子部门
         * 6. 若子部门列表为空，跳出该递归，继续其他递归
         */
        for (int i = 0; i < deptLevelList.size(); i++) {
            // 1. 获取当前部门
            DeptLevelDTO deptLevelDTO = deptLevelList.get(i);
            // 2. 根据当前部门的level 生成下级部门的level
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDTO.getId());
            // 3. 根据下级部门level 获取下级部门list
            List<DeptLevelDTO> tempDeptList = (List<DeptLevelDTO>) levelDeptMap.get(nextLevel);
            // 4. 判断是否为空（递归跳出条件）
            if (CollectionUtils.isNotEmpty(tempDeptList)) {
                // 排序
                Collections.sort(tempDeptList, deptSeqComparator);
                // 设置下层部门
                deptLevelDTO.setDeptList(tempDeptList);
                // 递归进入下层处理
                transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
            }
         }
    }

    /** 部门排序 */
    public Comparator<DeptLevelDTO> deptSeqComparator = new Comparator<DeptLevelDTO>() {
        @Override
        public int compare(DeptLevelDTO o1, DeptLevelDTO o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };
    /**================================<<end>>=======================================*/

    /**=============================<<权限模块树内容>>============================================*/
    /**
     * 权限模块List 转 tree
     * @param aclModuleLevelList
     * @return
     */
    public List<AclModuleLevelDTO> aclModuleListToTree(List<AclModuleLevelDTO> aclModuleLevelList) {
        // 判断是否为null，如果为null，则返回空的List回去
        if (CollectionUtils.isEmpty(aclModuleLevelList)) {
            return Lists.newArrayList();
        }

        /** 谷歌提供的高级的数据结构 Multimap */
        Multimap<String, AclModuleLevelDTO> levelAclModuleMap = ArrayListMultimap.create();
        List<AclModuleLevelDTO> rootList = Lists.newArrayList();

        // 遍历全部部门找出根节点部门List，后面作为生成部门树的初始List
        for (AclModuleLevelDTO dto : aclModuleLevelList) {
            levelAclModuleMap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }

        // 按照seq从小到大排序
        Collections.sort(rootList, aclModuleSeqComparator);

        // 递归转树
        transformAclModuleTree(rootList, LevelUtil.ROOT, levelAclModuleMap);

        return rootList;
    }

    /**
     * 递归生成部门树节点
     * @param aclModuleLevelList
     * @param level
     * @param levelAclModuleMap
     */
    public void transformAclModuleTree(List<AclModuleLevelDTO> aclModuleLevelList, String level, Multimap<String, AclModuleLevelDTO> levelAclModuleMap) {
        for (int i = 0; i < aclModuleLevelList.size(); i++) {
            // 1. 获取当前权限模块
            AclModuleLevelDTO dto = aclModuleLevelList.get(i);
            // 2. 根据当前权限模块level生成下级权限模块level
            String nextLevel = LevelUtil.calculateLevel(level, dto.getId());
            // 3. 根据下级权限模块level 获取下级权限模块list
            List<AclModuleLevelDTO> tempAclModuleList = (List<AclModuleLevelDTO>)levelAclModuleMap.get(nextLevel);
            // 4. 判断是否为空（递归跳出条件）
            if (!CollectionUtils.isEmpty(tempAclModuleList)) {
                // 排序
                Collections.sort(tempAclModuleList, aclModuleSeqComparator);
                // 设置下层权限模块
                dto.setAclModuleList(tempAclModuleList);
                // 递归进入下次处理
                transformAclModuleTree(tempAclModuleList, nextLevel, levelAclModuleMap);
            }
        }
    }

    /**权限模块排序 */
    public Comparator<AclModuleLevelDTO> aclModuleSeqComparator = new Comparator<AclModuleLevelDTO>() {
        @Override
        public int compare(AclModuleLevelDTO o1, AclModuleLevelDTO o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    /**================================<<end>>=======================================*/

}
