package personalTest.demoClass.factory;

/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-12 13:38
 * @Copyright: Copyright (c) 2018
 */
public class TableSqlFactory implements AbstractSqlFactory {

	/**
	 * @Description TODO
	 * @author rongh
	 * @date 2019-08-12 13:41
	 * @Copyright: Copyright (c) 2018
	 * @return
	 */
	@Override
	public SQL commit(int type) {
		DMLBean table = null;
		switch (type) {
		case 1:
			table = new TableA();
			break;
		default:
			table = new TableB();
			break;
		}
		table.insert();
		table.update();
		table.delete();
		System.out.println("commit the DDL");
		return table;
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
		DMLBean table = null;
		switch (type) {
		case 1:
			table = new TableA();
			break;
		default:
			table = new TableB();
			break;
		}
		table.insert();
		table.update();
		table.delete();
		System.out.println("rollback the DDL");
		return table;
	}
}
