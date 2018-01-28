package com.mmall.beans;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    /** 邮件标题 */
    private String subject;

    /** 邮件主体 */
    private String message;

    /** 发送的用户 */
    private Set<String> receivers;
}
