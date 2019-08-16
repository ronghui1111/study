package personalTest.DesginPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import personalTest.demoClass.proxy.CglibProxy;
import personalTest.demoClass.proxy.ProxyDemo;
import personalTest.demoClass.proxy.ProxyInterface;

/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-09 16:30
 * @Copyright: Copyright (c) 2018
 */
public class ProxyTest {
	private ProxyDemo demo;

	/**
	 * @author rongh
	 * @date 2019-08-09 16:32
	 * @Copyright: Copyright (c) 2018
	 * @param demo
	 */
	public ProxyTest(ProxyDemo demo) {
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
		ProxyTest proxy = new ProxyTest(demo);
		proxy.setName("tom");
		// JDK代理
		ProxyInterface interProxy = new ProxyDemo("rose");
		InvocationHandler handler = new JDKProxy(interProxy);
		ProxyInterface jdkDemo = (ProxyInterface) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
				interProxy.getClass().getInterfaces(), handler);
		jdkDemo.sayHello();

		CglibProxy cglibProxy = new CglibProxy();
		ProxyDemo demo1 = (ProxyDemo) cglibProxy.getProxy(ProxyDemo.class);
		demo1.sayHello();
	}
}
