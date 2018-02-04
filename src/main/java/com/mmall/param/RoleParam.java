package com.mmall.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 角色参数类
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/2/4 18:10
 */
@Getter
@Setter
@ToString
public class RoleParam {

    /** 角色id */
    private Integer id;

    /** 角色名称 */
    @NotBlank(message = "角色名称不可以为空")
    @Length(min = 2, max = 20, message = "角色名称长度需要在2-20个字之间")
    private String name;

    /** 角色类型 */
    @Max(value = 1, message = "角色类型不合法")
    @Min(value = 2, message = "角色类型不合法")
    private Integer type;

    /** 角色状态 */
    @NotNull(message = "角色状态不可以为空")
    @Min(value = 0, message = "角色状态不合法")
    @Max(value = 1, message = "角色状态不合法")
    private Integer status;


    /** 角色备注 */
    @Length(min = 0, max = 200, message = "角色备注长度需要在200个字符以内")
    private String remark;

}
