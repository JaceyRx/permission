package com.mmall.util;

import java.util.Date;
import java.util.Random;

/**
 * 密码生成工具类
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/26 9:31
 */
public class PasswordUtil {

    public final static String[] word = {
            "a", "b", "c", "d", "e", "f", "g",
            "h", "j", "k", "m", "n",
            "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G",
            "H", "J", "K", "M", "N",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    };

    public final static String[] num = {
            "2", "3", "4", "5", "6", "7", "8", "9"
    };

    /***
     * 随机生成指定长度的密码
     * @return
     */
    public static String randomPassword() {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random(new Date().getTime());
        // 随机数字还是字符的标识
        Boolean flag = false;
        // 随机密码长度，要么八位要么九位要么十位
        int length = random.nextInt(3) + 8;
        for (int i = 0; i < length; i++) {
            if (flag) {
                stringBuffer.append(num[random.nextInt(num.length)]);
            } else {
                stringBuffer.append(word[random.nextInt(word.length)]);
            }
            // 保证生成数字文字交替的密码
            flag = !flag;
        }

        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(randomPassword());
    }

}
