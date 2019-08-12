package personalTest.demoClass.observer;

/**
 * @Description 观察者
 * @author rongh
 * @date 2019-08-12 14:06
 * @Copyright: Copyright (c) 2018
 */
public class CustomerObserver {
	private String name;
	private int messageNumber = 0;

	public CustomerObserver(String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the messageNumber
	 */
	public int getMessageNumber() {
		return messageNumber;
	}

	/**
	 * @param messageNumber
	 *            the messageNumber to set
	 */
	public void setMessageNumber(int messageNumber) {
		this.messageNumber = messageNumber;
	}

	public void update() {
		this.messageNumber++;
		System.out.println(name + "观察到第" + messageNumber + "条消息");
	}
}
