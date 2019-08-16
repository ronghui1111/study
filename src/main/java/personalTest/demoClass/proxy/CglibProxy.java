package personalTest.demoClass.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-16 16:03
 * @Copyright: Copyright (c) 2018
 */
public class CglibProxy implements MethodInterceptor {
	private Enhancer enHancer = new Enhancer();

	public Object getProxy(Class<?> clazz) {
		enHancer.setSuperclass(clazz);
		enHancer.setCallback(this);
		return enHancer.create();

	}

	/**
	 * @Description TODO
	 * @author rongh
	 * @date 2019-08-16 16:04
	 * @Copyright: Copyright (c) 2018
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param proxy
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy proxy) throws Throwable {
		System.out.println("前置代理");
		Object obj = proxy.invokeSuper(arg0, arg2);
		System.out.println("后置代理");
		return obj;
	}

}
