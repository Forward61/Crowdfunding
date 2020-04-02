package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

///ControllerAdvice 表明当前类是一个机遇注解的异常类
@ControllerAdvice
public class CrowdExecptionResolver {

    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolverNullPointExecption(
            //实际捕获到的异常类型
            NullPointerException exception,
            //当前请求对象
            HttpServletRequest request,
            //当前响应对象
            HttpServletResponse response
            ) throws IOException {

//        boolean judgeResult = CrowdUtil.judgeRequestType(request);
//        if (judgeResult){
//            ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());
//            Gson gson = new Gson();
//            String json = gson.toJson(resultEntity);
//            response.getWriter().write(json);
//            return null;
//        }
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("exception",exception);
//        modelAndView.setViewName("system-error");

        String viewName = "system-error";
        return commonResolver(viewName,exception,request,response);
    }

    private ModelAndView commonResolver(String viewName,Exception exception, HttpServletRequest request,HttpServletResponse response) throws IOException {
        boolean judgeResult = CrowdUtil.judgeRequestType(request);
        if (judgeResult){
            ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(resultEntity);
            response.getWriter().write(json);
            return null;
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception",exception);
        modelAndView.setViewName(viewName);
        return modelAndView;

    }
}
