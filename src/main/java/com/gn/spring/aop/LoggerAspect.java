package com.gn.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
	
	private static final Logger LOGGER 
	= LoggerFactory.getLogger(LoggerAspect.class);
	
	@Before("execution(* com.gn.spring..*(..))")
	public void methodBefore(JoinPoint jp) {
		String className = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		LOGGER.info(className+"."+methodName+"() 실행");
	}
	
}
