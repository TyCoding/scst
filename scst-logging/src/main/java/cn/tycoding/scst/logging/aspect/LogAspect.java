package cn.tycoding.scst.logging.aspect;

import cn.tycoding.scst.common.utils.HttpContextUtil;
import cn.tycoding.scst.common.utils.IPUtil;
import cn.tycoding.scst.logging.annotation.Log;
import cn.tycoding.scst.logging.entity.SysLog;
import cn.tycoding.scst.logging.service.SysLogService;
import cn.tycoding.scst.logging.utils.LogUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 日志切面
 *
 * @author tycoding
 * @date 2019-06-08
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private ObjectMapper objectMapper;

    @Pointcut("@annotation(cn.tycoding.scst.logging.annotation.Log)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object round(ProceedingJoinPoint proceedingJoinPoint) throws JsonProcessingException {
        log.info("this is round()...");
        Object result = null;
        long beginTime = System.currentTimeMillis();

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        HttpServletRequest httpServletRequest = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(httpServletRequest);
        long time = System.currentTimeMillis() - beginTime;
        SysLog log = new SysLog();
        //TODO
        log.setUsername("tycoding");
        log.setIp(ip);
        log.setTime(time);

        //获取方法、参数
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        Log annotation = method.getAnnotation(Log.class);
        if (annotation != null) {
            log.setOperation(annotation.value());
        }
        //获取类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = signature.getName();
        log.setMethod(className + "." + methodName + "()");
        //获取方法参数
        Object[] args = proceedingJoinPoint.getArgs();
        //Spring提供的类用户获取方法参数列表
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);
        if (args != null && parameterNames != null) {
            StringBuilder params = new StringBuilder();
            params = LogUtil.handlerParams(params, args, Arrays.asList(parameterNames), objectMapper);
            log.setParams(params.toString());
        }
        sysLogService.saveLog(log);
        return result;
    }
}
