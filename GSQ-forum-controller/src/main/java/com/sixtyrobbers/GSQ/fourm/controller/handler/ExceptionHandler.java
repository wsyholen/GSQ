package com.sixtyrobbers.GSQ.fourm.controller.handler;

import com.sixtyrobbers.GSQ.fourm.controller.entity.BaseResult;
import com.sixtyrobbers.GSQ.fourm.controller.entity.ResponseCodeEnum;
import com.sixtyrobbers.GSQ.fourm.controller.forumController.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:36
 * Version: V1.0
 * </pre>
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv = new ModelAndView();
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Cache-Control", "no-cache, must-revalidate");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Cache-Control");
        httpServletResponse.setContentType("text/json;charset=utf-8");
        try {
            httpServletResponse.getWriter().write("{\"success\":\"false\", \"errCode\":\"" + ResponseCodeEnum.ERROR_CODE_SYS.getCode() + "\",\"errMsg\":\"" + ResponseCodeEnum.ERROR_CODE_SYS.getValue() + "\",\"data\":\"" + e.toString() + "\"}");
            logger.error("系统内部异常-"+"异常位置："+o.toString()+"-异常信息:"+ e.getMessage(), e);
        } catch (IOException e1) {
            logger.error("系统内部异常:" + e.getMessage(), e);
        }

        return mv;
    }
}
