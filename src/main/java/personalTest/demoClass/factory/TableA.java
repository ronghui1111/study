package personalTest.demoClass.factory;

/**
 * @author rongh
 * @date 2019-08-09 16:39
 * @Copyright: Copyright (c) 2018
 */
public class TableA extends DMLBean {

	/**
	 * @author rongh
	 * @date 2019-08-09 16:39
	 * @Copyright: Copyright (c) 2018
	 */
	@Override
	public void insert() {
		System.out.println("the insert() of TableA");

	}

	/**
	 * @author rongh
	 * @date 2019-08-09 16:39
	 * @Copyright: Copyright (c) 2018
	 */
	@Override
	public void delete() {
		System.out.println("the delete() of TableA");
	}

	/**
	 * @author rongh
	 * @date 2019-08-09 16:39
	 * @Copyright: Copyright (c) 2018
	 */
	@Override
	public void update() {
		System.out.println("the update() of TableA");
	}

}
