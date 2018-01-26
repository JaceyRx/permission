package com.mmall.beans;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/26 15:14
 */
@Getter
@Setter
@ToString
@Builder
public class PageResult<T> {

    private List<T> data = Lists.newArrayList();

    private int total;
}
