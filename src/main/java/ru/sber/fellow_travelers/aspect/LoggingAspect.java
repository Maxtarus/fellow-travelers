package ru.sber.fellow_travelers.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* ru.sber.fellow_travelers.controller.*.*(..))")
    private void controllerMethods() {}


    @Before("controllerMethods()")
    public void logBeforeControllerMethod(JoinPoint joinPoint) {
        logger.info("Calling controller method: " + joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logAfterControllerMethod(JoinPoint joinPoint, Object result) {
        logger.info("Finished calling controller method: " + joinPoint.getSignature().toShortString());
    }

}