package personalTest.demoClass.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 被观察者
 * @author rongh
 * @date 2019-08-12 14:11
 * @Copyright: Copyright (c) 2018
 */
public class Subject {
	public static final List<CustomerObserver> observerList = new ArrayList<CustomerObserver>();

	public void addObserver(CustomerObserver o) {
		System.out.println("\"" + o.getName() + "\" 用户加入订阅");
		observerList.add(o);
	}

	public void deleteObserver(CustomerObserver o) {
		System.out.println("\"" + o.getName() + "\" 用户取消订阅");
		observerList.remove(o);
	}

	public void notifyObserver() {
		if (observerList.size() != 0) {
			for (CustomerObserver o : observerList) {
				o.update();
			}
		}
	}
}
