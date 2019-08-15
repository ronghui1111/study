package personalTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/** * @author zheng * @date 2019/3/30 14:45 */
public class Count {
	public static int[] arrs(int arrs[]) {
		Set<Map.Entry<Integer, Integer>> set = new TreeSet<>(new MyCompare());
		Map<Integer, Integer> map = new TreeMap<>();
		for (int i : arrs) {
			map.put(i, map.containsKey(i) ? map.get(i) + 1 : 1);
		}
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			set.add(entry);
		}
		int[] newArrs = new int[map.size()];
		List<Map.Entry<Integer, Integer>> list = new ArrayList<>(set);
		for (int i = 0; i < newArrs.length; i++) {
			newArrs[i] = list.get(i).getKey();
		}
		return newArrs;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 3, 2, 7, 2, 6, 7, 1, 1, 1, 1 };
		System.out.println(Arrays.toString(arrs(arr)));
	}

}

class MyCompare implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		Map.Entry<Integer, Integer> key_value_01 = (Map.Entry<Integer, Integer>) o1;
		Map.Entry<Integer, Integer> key_value_02 = (Map.Entry<Integer, Integer>) o2;
		int num = key_value_01.getValue() - key_value_02.getValue();
		if (num == 0) {
			return key_value_01.getKey() - key_value_02.getKey();
		}
		return num;
	}
}