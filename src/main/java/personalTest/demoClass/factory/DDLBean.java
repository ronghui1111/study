package personalTest.demoClass.factory;

/**
 * @author rongh
 * @date 2019-08-09 16:38
 * @Copyright: Copyright (c) 2018
 */
public abstract class DDLBean implements SQL {
	public abstract void alert();

	public abstract void drop();

	public abstract void create();
}
