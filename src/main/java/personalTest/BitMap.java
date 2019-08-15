package personalTest;

import java.util.Random;

/**
 * @Description TODO
 * @author rongh
 * @date 2019-08-13 15:33
 * @Copyright: Copyright (c) 2018
 */
public class BitMap {

	private byte[] bytes = null;

	/**
	 * @author rongh
	 * @date 2019-08-13 15:39
	 * @Copyright: Copyright (c) 2018
	 * @param size
	 *            此字段表示要处理的int型数组大小，用byte数据来标识数据的村粗，byte的大小即为size/8
	 */
	public BitMap() {
		this.bytes = new byte[(Integer.MAX_VALUE >> 3) + 1];
	}

	/**
	 * @Description BitMap
	 * @author rongh
	 * @date 2019-08-13 14:14
	 * @Copyright: Copyright (c) 2018
	 * @return
	 */
	public byte[] add(int num) {
		int index = num / 8; // bit数组（虚拟）在byte数组中的索引
		int innerIndex = num % 8; // bitIndex 在byte[]数组索引index 中的具体位置
		bytes[index] = (byte) (bytes[index] | (1 << innerIndex));
		return bytes;
	}

	/**
	 * 输出数组中的数据
	 *
	 * @param bytes
	 *            byte数组
	 */
	public boolean isExist(int num) {
		int index = num / 8;
		int innerIndex = num % 8;
		if (((bytes[index]) & (1 << innerIndex)) != 0) {
			System.out.println(num + "已存入");
			return true;
		}
		System.out.println(num + "未存入");
		return false;
	}

	/**
	 * 输出数组中的数据
	 * 
	 * @param bytes
	 *            byte数组
	 */
	public void output() {
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

	/**
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}

	public static void main(String[] args) {
		int arrLength = Integer.MAX_VALUE >> 3;
		BitMap map = new BitMap();
		Random random = new Random();
		for (int i = 0; i < arrLength; i++) {
			int num = random.nextInt(Integer.MAX_VALUE);
			map.isExist(num);
			map.add(num);
			map.isExist(num);
		}
	}
}
