package personalTest.DesginPattern;

import java.util.Random;

import personalTest.demoClass.observer.CustomerObserver;
import personalTest.demoClass.observer.Subject;

/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-12 14:36
 * @Copyright: Copyright (c) 2018
 */
public class Observer {
	public static void main(String[] args) {
		CustomerObserver jack = new CustomerObserver("jack");
		CustomerObserver tom = new CustomerObserver("tom");
		CustomerObserver rose = new CustomerObserver("rose");
		Subject sub = new Subject();
		sub.addObserver(rose);
		sub.addObserver(tom);
		sub.addObserver(jack);
		for (int i = 0; i < new Random().nextInt(10); i++) {
			sub.notifyObserver();
		}
	}
}
