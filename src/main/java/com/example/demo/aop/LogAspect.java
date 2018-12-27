package com.example.demo.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(* com.example.demo..service.UserService.update*(..))")
    public void cutUpdate() {

    }
    @Pointcut("execution(* com.example.demo..service.UserService.delete*(..))")
    public void cutDelete() {

    }


    @Before(value = "cutUpdate()")
    public void beforeSave(JoinPoint joinPoint) {
        log.info("[beforeUpdate] param is: {}", Arrays.toString(joinPoint.getArgs()));
    }

    @Before(value = "cutDelete()")
    public void beforeDelete(JoinPoint joinPoint) {
        log.info("[beforeDelete] param is: {}", Arrays.toString(joinPoint.getArgs()));
    }

}