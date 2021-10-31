package com.example.springshop.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class TimeAspect {


    @Around("execution(* com.example.springshop.service..*.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        System.out.println(joinPoint + " -> " + (System.currentTimeMillis() - startTime) + " ms");
        return result;
    }
}
