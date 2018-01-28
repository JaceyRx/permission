package com.mmall.dto;

import com.google.common.collect.Lists;
import com.mmall.model.SysAclModule;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/28 15:22
 */
@Getter
@Setter
@ToString
public class AclModuleLevelDTO extends SysAclModule{

    private List<AclModuleLevelDTO> aclModuleList = Lists.newArrayList();

    /** 原对象转dto */
    public static AclModuleLevelDTO adapt(SysAclModule aclModule) {
        AclModuleLevelDTO dto = new AclModuleLevelDTO();
        BeanUtils.copyProperties(aclModule, dto);
        return dto;
    }

}
