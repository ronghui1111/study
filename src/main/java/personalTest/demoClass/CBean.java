package personalTest.demoClass;

/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-09 16:39
 * @Copyright: Copyright (c) 2018
 */
public class CBean implements BeanInterface {

	/**
	 * @Description TODO
	 * @author rongh
	 * @date 2019-08-09 16:39
	 * @Copyright: Copyright (c) 2018
	 */
	@Override
	public void add() {
		System.out.println("the add() of C");

	}

	/**
	 * @Description TODO
	 * @author rongh
	 * @date 2019-08-09 16:39
	 * @Copyright: Copyright (c) 2018
	 */
	@Override
	public void delete() {
		System.out.println("the delete() of C");
	}

	/**
	 * @Description TODO
	 * @author rongh
	 * @date 2019-08-09 16:39
	 * @Copyright: Copyright (c) 2018
	 */
	@Override
	public void update() {
		System.out.println("the update() of C");
	}

	/**
	 * @Description TODO
	 * @author rongh
	 * @date 2019-08-09 16:39
	 * @Copyright: Copyright (c) 2018
	 */
	@Override
	public void select() {
		System.out.println("the select() of C");
	}

}
