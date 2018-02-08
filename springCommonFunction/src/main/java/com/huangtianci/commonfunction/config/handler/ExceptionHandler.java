package com.huangtianci.commonfunction.config.handler;

import com.huangtianci.commonfunction.common.bean.Out;
import com.huangtianci.commonfunction.common.enums.ResultCodeEnum;
import com.huangtianci.commonfunction.common.exception.ParameterException;
import com.huangtianci.commonfunction.common.exception.ServiceException;
import com.huangtianci.commonfunction.utils.JsonUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Huang Tianci
 * @date 2017/11/23
 * controller层抛出的异常统一处理
 * 转成Out对象输出
 */
@Configuration
public class ExceptionHandler implements HandlerExceptionResolver {

    private static Logger LOGGER = Logger.getLogger(ExceptionHandler.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();

        LOGGER.error(ResultCodeEnum.FAIL.getDesc(), ex);

        String code = ResultCodeEnum.FAIL.getCode();

        StringBuilder messageBuilder = new StringBuilder();
        if (ex instanceof ParameterException) {
            messageBuilder.append("参数错误。错误信息：").append(ex.getMessage());
        } else if (ex instanceof ServiceException) {
            messageBuilder.append("系统内部错误，请联系管理员。错误信息：").append(ex.getMessage());
        } else if (ex instanceof NullPointerException) {
            messageBuilder.append("空指针异常。错误信息：").append(ex.getMessage());
        } else {
            messageBuilder.append("未知错误，请联系管理员。错误信息：").append(ex.getMessage());
        }

        MappingJackson2JsonView view = new MappingJackson2JsonView();
        Out out = Out.builder().fail(code, messageBuilder.toString()).build();
        Map map = JsonUtils.fromJson(JsonUtils.toJson(out), HashMap.class);
        view.setAttributesMap(map);
        mv.setView(view);
        return mv;
    }

    @Bean
    public ExceptionHandler createExceptionHandler() {
        return new ExceptionHandler();
    }
}