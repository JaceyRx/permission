package com.mmall.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 层级工具类
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/22 15:19
 */
public class LevelUtil {

    /** 分割符 */
    public final static String SEPARTOR = ".";

    /** 根字节 */
    public final static String ROOT = "0";

    /**
     * 计算 层级 Level
     *  0
     *  0.1
     *  0.1.2
     *  0.1.3
     *  0.4
     * @param parentLevel
     * @param parentId
     * @return
     */
    public static String calculateLevel(String parentLevel, int parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel, SEPARTOR, parentId);
        }
    }

}
