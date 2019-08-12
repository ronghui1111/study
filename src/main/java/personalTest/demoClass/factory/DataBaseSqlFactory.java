package personalTest.demoClass.factory;

/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-12 13:38
 * @Copyright: Copyright (c) 2018
 */
public class DataBaseSqlFactory implements AbstractSqlFactory {

	/**
	 * @Description TODO
	 * @author rongh
	 * @date 2019-08-12 13:41
	 * @Copyright: Copyright (c) 2018
	 * @return
	 */
	@Override
	public SQL commit(int type) {
		DDLBean dataBase = null;
		switch (type) {
		case 1:
			dataBase = new DataBaseA();
			break;
		default:
			dataBase = new DataBaseB();
			break;
		}
		dataBase.create();
		dataBase.alert();
		dataBase.drop();
		System.out.println("commit the DML");
		return dataBase;
	}

	/**
	 * @Description TODO
	 * @author rongh
	 * @date 2019-08-12 13:41
	 * @Copyright: Copyright (c) 2018
	 * @return
	 */
	@Override
	public SQL rollback(int type) {
		DDLBean dataBase = null;
		switch (type) {
		case 1:
			dataBase = new DataBaseA();
			break;
		default:
			dataBase = new DataBaseB();
			break;
		}
		dataBase.create();
		dataBase.alert();
		dataBase.drop();
		System.out.println("rollback the DML");
		return dataBase;
	}

}
