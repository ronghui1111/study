/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-07 16:43
 * @Title: RateLimitedService.java
 * @Company: CORSWORK
 * @Copyright: Copyright (c) 2018
 */
package com.rong.src.study.service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author rongh
 *
 */
@Component
public class RateLimitedService {
	private static RateLimiter rateLimiter = RateLimiter.create(1);

	public static boolean tryAcquire() {
		return rateLimiter.tryAcquire(0, TimeUnit.MILLISECONDS);
	}

	public static void setRate(long l) {
		rateLimiter.setRate(l);
	}
}
