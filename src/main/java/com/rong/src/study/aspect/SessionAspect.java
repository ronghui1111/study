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
	public void ouAspect() {
	}

	@Before("ouAspect()")
	public void before() {
		System.out.println("进入切面");
	}

	@After("ouAspect()")
	public void after() {
		System.out.println("退出切面");
	}
}
