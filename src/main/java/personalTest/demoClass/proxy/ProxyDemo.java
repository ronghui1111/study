package personalTest.demoClass.proxy;

/**
 * @Description 设计模式demo
 * @author rongh
 * @date 2019-08-09 16:30
 * @Copyright: Copyright (c) 2018
 */
public class ProxyDemo {
	private String name;
	private String type;

	/**
	 * @return the name
	 */
	public String getName() {
		System.out.println("name : " + name);
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		System.out.println("name : " + name);
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		System.out.println("type : " + type);
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		System.out.println("type : " + type);
		this.type = type;
	}
}
