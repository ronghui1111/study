package personalTest.DesginPattern;

import personalTest.demoClass.ABean;
import personalTest.demoClass.BBean;
import personalTest.demoClass.BeanInterface;
import personalTest.demoClass.CBean;

/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-09 16:42
 * @Copyright: Copyright (c) 2018
 */
public class BeanFactory {
	public static BeanInterface getInstance(String type) {
		if ("A".equals(type))
			return new ABean();
		if ("B".equals(type))
			return new BBean();
		if ("C".equals(type))
			return new CBean();
		return null;

	}
	public static void main(String[] args) {
		
	}
}
