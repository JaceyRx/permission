package com.mmall.common;

import com.mmall.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Http请求前后监听类
 *
 * 应用场景
 *        如判断一个正常请求处理所消耗的时长
 *        日志输出请求的参数
 *        获取请求者的ip 等
 *
 * @auther ruanjunxu
 * @email ruanjunxu@qq.com
 * @create 2018/1/22 14:16
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter{

    private static final String START_TIME = "requestStartTime";

    /** 处理请求之前执行 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI().toString();
        Map parameterMap = request.getParameterMap();
        log.info("request start. url:{}, params:{}", url, JsonMapper.obj2String(parameterMap));
        Long start = System.currentTimeMillis();
        request.setAttribute(START_TIME, start);
        return true;
    }

    /** 正常请求处理之后执行 */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    /** 任何情况下请求处理之后 */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURL().toString();
        Map parameterMap = request.getParameterMap();
        Long start = (Long) request.getAttribute(START_TIME);
        Long end = System.currentTimeMillis();
        log.info("request completed. url:{}, cost:{} ,params:{}", url, end - start,JsonMapper.obj2String(parameterMap));

    }
}
