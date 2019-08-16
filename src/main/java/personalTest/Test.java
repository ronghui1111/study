package personalTest;

/**
 * @author rongh
 * @date 2019-03-14 09:03
 * @Copyright: Copyright (c) 2018
 */
public class Test {
	private long initNum = 2;

	public long getSize(int month) {
		if (month < 3) {
			return initNum;
		}
		if (month >= 3) {
			initNum *= 2;
			getSize(month - 3);
		}
		return initNum;
	}

	public static void main(String[] args) {
		Test test = new Test();
		test.getSize(1101);
		System.out.println(test.initNum);
	}
}
