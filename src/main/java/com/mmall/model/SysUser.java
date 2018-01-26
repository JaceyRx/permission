package com.mmall.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Builder
@NoArgsConstructor     // 生成无参构造方法注解
@AllArgsConstructor    // 生成全部参数构造方法注解
@ToString              // 生成toString 方法注解
public class SysUser {

    /** 用户id */
    private Integer id;

    /** 用户名 */
    private String username;

    /** 手机号 */
    private String telephone;

    /** 邮箱 */
    private String mail;

    /** 密码 */
    private String password;

    /** 部门id */
    private Integer deptId;

    /** 状态 */
    private Integer status;

    /** 备注 */
    private String remark;

    /** 操作者 */
    private String operator;

    /** 操作时间 */
    private Date operateTime;

    /** 操作者ip */
    private String operateIp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp == null ? null : operateIp.trim();
    }
}