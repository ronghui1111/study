package com.rong.src.study.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-08 09:34
 * @Copyright: Copyright (c) 2018
 */
@Component
@Aspect
public class SessionAspect {
	@Pointcut("@annotation(com.rong.src.study.annotation.AspectTest)")
	public void annotationAspect() {
	}

	@Pointcut("execution(* com..controller.*.*(..))")
	public void patternAspect() {
	}

	@Before("annotationAspect()")
	public void annotationAspectBefore() {
		System.out.println("进入annotationAspect切面");
	}

	@After("annotationAspect()")
	public void annotationAspectAfter() {
		System.out.println("退出annotationAspect切面");
	}

	@Before("patternAspect()")
	public void patternAspectBefore() {
		System.out.println("进入patternAspect切面");
	}

	@After("patternAspect()")
	public void patternAfter() {
		System.out.println("退出patternAspect切面");
	}
}
