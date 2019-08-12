package personalTest.DesginPattern;

import personalTest.demoClass.proxy.ProxyDemo;

/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-09 16:30
 * @Copyright: Copyright (c) 2018
 */
public class Proxy {
	private ProxyDemo demo;

	/**
	 * @author rongh
	 * @date 2019-08-09 16:32
	 * @Copyright: Copyright (c) 2018
	 * @param demo
	 */
	public Proxy(ProxyDemo demo) {
		super();
		this.demo = demo;
	}

	public void setName(String name) {
		System.out.println("-----代理前-----");
		this.demo.setName(name);
		System.out.println("-----代理后-----");
	}

	public static void main(String[] args) {
		ProxyDemo demo = new ProxyDemo();
		Proxy proxy = new Proxy(demo);
		proxy.setName("tom");
	}
}
