package personalTest.demoClass.proxy;

/**
 * @Description 设计模式demo
 * @author rongh
 * @date 2019-08-09 16:30
 * @Copyright: Copyright (c) 2018
 */
public class ProxyDemo implements ProxyInterface {
	private String name;
	private String type;

	/**
	 * @Description TODO
	 * @author rongh
	 * @date 2019-08-16 16:01
	 * @Copyright: Copyright (c) 2018
	 * @param name
	 */
	public ProxyDemo(String name) {
		super();
		this.name = name;
	}

	public ProxyDemo() {
		super();
	}

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

	/**
	 * @Description TODO
	 * @author rongh
	 * @date 2019-08-16 15:43
	 * @Copyright: Copyright (c) 2018
	 */
	@Override
	public void sayHello() {
		System.out.println("Hello " + name);

	}

	/**
	 * @Description TODO
	 * @author rongh
	 * @date 2019-08-16 15:43
	 * @Copyright: Copyright (c) 2018
	 */
	@Override
	public void sayBye() {
		System.out.println("goodBye " + name);

	}
}
