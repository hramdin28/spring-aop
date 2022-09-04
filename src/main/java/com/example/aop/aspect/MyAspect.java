package com.example.aop.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class MyAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAspect.class);

    @Around("@annotation(com.example.aop.annotation.LogMethodExecution)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println();
        System.out.println("logAround() is running!");
        System.out.println("hijacked method : " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        System.out.println("******");
        System.out.println();
        return result;
    }

    @AfterReturning(pointcut = "execution(* com.example.aop.service.InformationService.getReturnValue(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        System.out.println("logAfterReturning() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("return value : " + result);
        System.out.println("******");
        System.out.println();
    }

    @AfterThrowing(pointcut = "execution(* com.example.aop.service.InformationService.throwException(..))",
            throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

        Signature signature = joinPoint.getSignature();
        String method = signature.getName();

        LOGGER.error("An exception occurred while running {}",
                method,
                error);

    }

}
