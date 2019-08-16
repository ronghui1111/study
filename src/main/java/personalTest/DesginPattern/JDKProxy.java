package personalTest.DesginPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author rongh
 * @date 2019-08-16 15:44
 * @Copyright: Copyright (c) 2018
 */
public class JDKProxy implements InvocationHandler {
	private Object object;

	/**
	 * @author rongh
	 * @date 2019-03-14 15:45
	 * @Copyright: Copyright (c) 2018
	 * @param object
	 */
	public JDKProxy(Object object) {
		this.object = object;
	}

	/**
	 * @author rongh
	 * @date 2019-03-14 15:44
	 * @Copyright: Copyright (c) 2018
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		System.out.println("前置代理");
		Object obj = arg1.invoke(object, arg2);
		System.out.println("后置代理");
		return obj;
	}

}
