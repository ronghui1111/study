/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-07 16:55
 * @Title: ProducerThread.java
 * @Company: CORSWORK
 * @Copyright: Copyright (c) 2018
 */
package com.rong.src.study.thread;

import com.rong.src.study.service.RateLimitedService;

/**
 * @author rongh
 *
 */
public class ProducerThread extends Thread {
	public static boolean flag = true;

	@Override
	public void run() {
		while (flag) {
			while (RateLimitedService.tryAcquire()) {
				System.out.println(System.currentTimeMillis());
			}
		}
	}

}
