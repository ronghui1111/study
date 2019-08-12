package personalTest.demoClass.factory;

/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-12 13:27
 * @Copyright: Copyright (c) 2018
 */
public interface AbstractSqlFactory {
	SQL commit(int type);

	SQL rollback(int type);
}
