package com.chen.mycardsystembackstage.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Project : MyCardSystem - ControllerAspect
 * Powered by GeorgeChen On 2022-12-15 22:50:59
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/

@Component
@Aspect
@Slf4j
public class ControllerAspect {

    /**
     * (* com.chen.mycardsystembackstage.*.*(..))
     * 第一个 * 代表所有的方法
     * 第二个 * 前面 com.chen.mycardsystembackstage.* 下面的所有类
     * 第三个 * 代表以及子类 最后面括号里面的
     * .. 代表所有参数
     *
     * 定义切入点，切入点为 com.chen.mycardsystembackstage 下的所有函数
     */
    @Pointcut("execution(* com.chen.mycardsystembackstage.*.*(..))")
    private void pointCut() {}

    // 方法用 execution 指定,注解用 @annotation 指定，注解的类型是 @interface，算是一种特殊的接口
    // @Pointcut("@annotation(com.chy.mall.annotation.UploadObs)")

    /**
     * 前置通知，在连接点前面执行，前置通知不会影响连接点的执行，除非此处抛出异常。
     */
    // @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        // 接收到请求，RequestContextHolder来获取请求信息，Session信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        // getSignature() 获取封装了署名信息的对象,在该对象中可以获取到目标方法名，所属类的Class等信息
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        // getArgs() 获取传入目标方法的参数对象
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        System.out.println("正在执行前置通知");
    }

    /**
     * 后置通知，在连接点正常执行完成后执行，如果连接点抛出异常，则不会执行。
     */
    // @After("pointCut()")
    public void after() {
        System.out.println("正在执行后置通知");
    }

    /**
     * 后置返回通知，可传递目标方法的返回值，在连接点执行完成后执行，不管是正常执行完成，还是抛出异常，都会执行返回通知中的内容。 obj 目标方法的返回值
     */
    // @AfterReturning(value = "pointCut()", returning = "obj")
    public void afterReturning(Object obj) {
        System.out.println("目标方法的返回值是：" + obj);
    }

    /**
     * 后置异常通知，可传递异常对象，在连接点抛出异常后执行。
     */
    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        log.info(joinPoint.getSignature().getName());
        System.out.println("异常信息：" + e.getMessage());
    }

    /**
     * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    // @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 前增强
        System.out.println("正在执行前置通知");
        // 调用目标方法
        Object object = proceedingJoinPoint.proceed();
        // 后增强
        System.out.println("正在执行后置通知");
        // 返回目标方法的返回值
        return object;
    }
}
