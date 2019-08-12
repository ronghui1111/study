
package personalTest;

import java.util.Arrays;
import java.util.Random;

/**
 * @author rongh
 * @date 2017-08-06 13:29
 * @Title: Sort.java
 * @Company: CORSWORK
 * @Copyright: Copyright (c) 2017
 */
public class Sort {
	public static void main(String[] args) {
		int[] array = new int[10];
		Random random = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(500);
		}
		System.out.println("----------------------before sort---------------------" + Arrays.toString(array));
		selectSort(array);
		System.out.println("----------------------after sort---------------------" + Arrays.toString(array));
	}

	public static void bulobuloSort(int[] array) {
		int sum = 0;
		boolean flag;
		int temp;
		for (int i = 0; i < array.length; i++) {
			flag = false;
			for (int j = 0; j < array.length - i - 1; j++) {
				if (array[j] < array[j + 1]) {
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					flag = true;
					System.out.println(Arrays.toString(array));
				}
				sum++;
			}
			if (!flag)
				break;
		}
		System.out.println(sum);
	}

	public static void selectSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int min = i;
			int temp;
			for (int j = i + 1; j < array.length; j++) {
				if (array[min] > array[j]) {
					min = j;
				}
			}
			if (min != i) {
				temp = array[i];
				array[i] = array[min];
				array[min] = temp;
			}
		}
	}

}
