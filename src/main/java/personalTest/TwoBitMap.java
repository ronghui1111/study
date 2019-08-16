package personalTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 * @author rongh
 * @date 2019-03-14 15:33
 * @Copyright: Copyright (c) 2018
 */
public class TwoBitMap {

	private byte[] bytes = null;
	private Map<Integer, RepeatNum> repeatMap = new HashMap<Integer, RepeatNum>();

	/**
	 * @author rongh
	 * @date 2019-03-14 15:39
	 * @Copyright: Copyright (c) 2018
	 * @param size
	 *            此字段表示要处理的int型数组大小，用byte数据来标识数据的村粗，byte的大小即为size/8
	 */
	public TwoBitMap() {
		this.bytes = new byte[(Integer.MAX_VALUE >> 2) + 1];
	}

	/**
	 * @Description BitMap
	 * @author rongh
	 * @date 2019-03-14 14:14
	 * @Copyright: Copyright (c) 2018
	 * @return
	 */
	public byte[] add(int num) {
		int index = num >> 2; // bit数组（虚拟）在byte数组中的索引
		int innerIndex = (num % 4) << 1; // bitIndex 在byte[]数组索引index 中的具体位置
		if (((bytes[index]) & (1 << innerIndex)) != 0) {
			if (repeatMap.containsKey(num)) {
				RepeatNum info = repeatMap.get(num);
				info.setRepeatNum(info.getRepeatNum() + 1);
			} else
				repeatMap.put(num, new RepeatNum(num, 2));
			bytes[index] = (byte) (bytes[index] | (1 << (innerIndex + 1)));
			return bytes;
		}
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
		int index = num >> 2; // bit数组（虚拟）在byte数组中的索引
		int innerIndex = (num % 4) << 1; // bitIndex 在byte[]数组索引index 中的具体位置
		if (((bytes[index]) & (1 << innerIndex)) != 0) {
			return true;
		}
		return false;
	}

	/**
	 * 输出数组中的数据
	 * 
	 * @param bytes
	 *            byte数组
	 */
	public void outputAll() {
		int count = 0;
		for (int i = 0; i < bytes.length; i++) {
			for (int j = 0; j < 8; j += 2) {
				if (((bytes[i]) & (1 << j)) != 0) {
					count++;
					int number = (i << 2) + (j >> 1);
					System.out.println("取出的第 " + count + "\t个数: " + number);
				}
			}
		}
	}

	/**
	 * 输出数组中的数据
	 * 
	 * @param bytes
	 *            byte数组
	 */
	public void outputRepeatNum() {
		for (int i = 0; i < bytes.length; i++) {
			for (int j = 0; j < 8; j += 2) {
				if (((bytes[i]) & (1 << (j + 1))) != 0) {
					int number = (i << 2) + (j >> 1);
					System.out.println(number + "出现重复");
				}
			}
		}
	}

	/**
	 * 输出数组中的数据
	 * 
	 * @param bytes
	 *            byte数组
	 */
	public void outputRepeatMap() {
		for (Entry<Integer, RepeatNum> entry : repeatMap.entrySet()) {
			System.out.println(entry.getKey() + "重复次数为 ：" + entry.getValue().getRepeatNum());
		}
	}

	/**
	 * 输出数组中的数据
	 * 
	 * @param bytes
	 *            byte数组
	 */
	public void outputRepeatMap(int size) {
		List<RepeatNum> list = new ArrayList<RepeatNum>(repeatMap.values());
		Collections.sort(list);
		for (int i = 0; i < size; i++) {
			System.out.println(list.get(i).getNum() + "重复次数为 ：" + list.get(i).getRepeatNum());
		}
	}

	/**
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}

	public static void main(String[] args) {
		TwoBitMap map = new TwoBitMap();
		Random random = new Random();
		for (int i = 0; i < 50000000; i++) {
			map.add(random.nextInt(Integer.MAX_VALUE));
		}
		map.outputRepeatMap(2);
	}

	class RepeatNum implements Comparable<RepeatNum> {
		private int num;
		private int repeatNum;

		/**
		 * @Description TODO
		 * @author rongh
		 * @date 2019-03-14 16:42
		 * @Copyright: Copyright (c) 2018
		 * @param num
		 * @param repeatNum
		 */
		public RepeatNum(int num, int repeatNum) {
			super();
			this.num = num;
			this.repeatNum = repeatNum;
		}

		/**
		 * @return the num
		 */
		public int getNum() {
			return num;
		}

		/**
		 * @param num
		 *            the num to set
		 */
		public void setNum(int num) {
			this.num = num;
		}

		/**
		 * @return the repeatNum
		 */
		public int getRepeatNum() {
			return repeatNum;
		}

		/**
		 * @param repeatNum
		 *            the repeatNum to set
		 */
		public void setRepeatNum(int repeatNum) {
			this.repeatNum = repeatNum;
		}

		/**
		 * @Description TODO
		 * @author rongh
		 * @date 2019-03-14 16:40
		 * @Copyright: Copyright (c) 2018
		 * @param paramT
		 * @return
		 */
		@Override
		public int compareTo(RepeatNum paramT) {
			if (paramT.repeatNum > this.getRepeatNum())
				return -1;
			return 1;
		}
	}
}