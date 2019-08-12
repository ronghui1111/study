package personalTest.demoClass.factory;

/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-09 16:39
 * @Copyright: Copyright (c) 2018
 */
public class DataBaseA extends DDLBean {

	/**
	 * @Description TODO
	 * @author rongh
	 * @date 2019-08-12 11:15
	 * @Copyright: Copyright (c) 2018
	 */
	@Override
	public void alert() {
		System.out.println("the alert() of DataBaseA");
	}

	/**
	 * @Description TODO
	 * @author rongh
	 * @date 2019-08-12 11:15
	 * @Copyright: Copyright (c) 2018
	 */
	@Override
	public void drop() {
		System.out.println("the drop() of DataBaseA");

	}

	/**
	 * @Description TODO
	 * @author rongh
	 * @date 2019-08-12 11:15
	 * @Copyright: Copyright (c) 2018
	 */
	@Override
	public void create() {
		System.out.println("the create() of DataBaseA");
	}

}
