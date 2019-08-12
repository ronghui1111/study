package personalTest.demoClass.factory;

/**
 * @author rongh
 * @date 2019-08-09 16:38
 * @Copyright: Copyright (c) 2018
 */
public abstract class DMLBean implements SQL {
	public abstract void insert();

	public abstract void delete();

	public abstract void update();
}
