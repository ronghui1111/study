package com.rong.src.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class CountThread extends Thread {
	private static AtomicInteger senderQps = new AtomicInteger(0);

	public static void increment() {
		senderQps.getAndIncrement();
	}

	private int getTps(int num1, int num2) {
		return (num2 - num1) / 10;
	}

	@Override
	public void run() {
		while (true) {
			int now = senderQps.get();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int after = senderQps.get();
			System.out.println("µ±Ç°TPSÎª  : " + getTps(now, after));
		}
	}

}
