package com.mmall.common;

import com.mmall.exception.ParamException;
import com.mmall.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 *
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/20 15:14
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        String url = request.getRequestURL().toString();
        ModelAndView mv = null;
        String defaultMsg = "System error";

        // 统一，json的请求 url以 .json 结尾，页面请求统一以 .page 结尾
        // 这样容易判断是页面请求或者是json 请求
        if (url.endsWith(".json")) {
            // 假如该异常时我们自己的异常时，处理它
            if (e instanceof PermissionException || e instanceof ParamException) {
                JsonData result = JsonData.fail(e.getMessage());
                // 返回json消息
                mv = new ModelAndView("jsonView", result.toMap());
            } else {
                log.error("【未知异常 json】 url = {}, e = {}", url, e);
                JsonData result = JsonData.fail(defaultMsg);
                mv = new ModelAndView("jsonView", result.toMap());
            }
        } else if (url.endsWith(".page")){
            log.error("【未知异常 page】 url = {}, e = {}", url, e);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("exception", result.toMap());
        } else {
            JsonData result = JsonData.fail(defaultMsg);
            log.error("【未知异常】 url = {}, e = {}", url, e);
            mv = new ModelAndView("jsonView", result.toMap());
        }


        return mv;
    }


}
