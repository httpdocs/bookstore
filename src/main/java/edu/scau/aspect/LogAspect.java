package edu.scau.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);


    @Before("execution(* edu.scau.controller.*Controller.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("before");

    }

    @After("execution(* edu.scau.controller.*Controller.*(..))")
    public void afterMethod() {
    	System.out.println("after");
        logger.info("after method" + new Date());
    }
}
