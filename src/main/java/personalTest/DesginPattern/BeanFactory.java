package personalTest.DesginPattern;

import java.util.BitSet;

import personalTest.demoClass.factory.AbstractSqlFactory;
import personalTest.demoClass.factory.DMLBean;
import personalTest.demoClass.factory.DataBaseSqlFactory;
import personalTest.demoClass.factory.TableA;
import personalTest.demoClass.factory.TableB;
import personalTest.demoClass.factory.TableSqlFactory;

/**
 * @Description 工厂模式跟抽象工厂
 * @author rongh
 * @date 2019-08-09 16:42
 * @Copyright: Copyright (c) 2018
 */
public class BeanFactory {
	public static DMLBean getInstance(String type) {
		if ("A".equals(type))
			return new TableA();
		if ("B".equals(type))
			return new TableB();
		return null;

	}

	public static void main(String[] args) {
		 // 工厂模式
		 DMLBean bean = BeanFactory.getInstance("B");
		 bean.insert();
		 bean.delete();
		 // 抽象工厂
		 AbstractSqlFactory tableSqlFactory = new TableSqlFactory();
		 tableSqlFactory.commit(1);
		
		 AbstractSqlFactory dataBaseSqlFactory = new DataBaseSqlFactory();
		 dataBaseSqlFactory.rollback(2);
	}

	public final int[] arr = { 1, 2, 3, 4, 5, 6, 711, 231, 23, 2, 231, 421, 415, 36, 476, 457, 568, 569, 96, 57, 568,
			24, 523, 4, 334, 636, 375, 735, 753, 634, 634, 6346, 346, 34623, 754, 89, 659, 56, 75, 56 };

	

	/**
	 * 输出数组中的数据
	 * 
	 * @param bytes
	 *            byte数组
	 */
	public void output(byte[] bytes) {
		int count = 0;
		for (int i = 0; i < bytes.length; i++) {
			for (int j = 0; j < 8; j++) {
				if (((bytes[i]) & (1 << j)) != 0) {
					count++;
					int number = (int) (((long) i * 8 + j) - (1l << 31));
					System.out.println("取出的第 " + count + "\t个数: " + number);
				}
			}
		}
	}

}
