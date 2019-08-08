package com.rong.src.study.util;

import java.util.Collection;
import java.util.Map;

/**
 * @Description ∂œ—‘¿‡
 * @author rongh
 * @Copyright: Copyright (c) 2018
 */
public class Assert {

	public static boolean isEmpty(Collection<?> obj) {
		return (obj == null) || (obj.isEmpty());
	}

	public static boolean isEmpty(Map<?, ?> obj) {
		return (obj == null) || (obj.isEmpty());
	}

	public static boolean isEmpty(Object[] obj) {
		return (obj == null) || (obj.length == 0);
	}

	public static boolean isEmpty(Object obj) {
		return obj == null;
	}

	public static void assertNotEmpty(Map<?, ?> obj, String name) {
		if (isEmpty(obj)) {
			if ((!isEmpty(name)) && (name.length() > 2)
					&& ("_V".equals(name.substring(name.length() - 2, name.length())))) {
				name = name.substring(0, name.length() - 2);
			}
			throw new RuntimeException(name);
		}
	}

	public static void assertNotEmpty(Object[] obj, String name) {
		if (isEmpty(obj)) {
			if ((!isEmpty(name)) && (name.length() > 2)
					&& ("_V".equals(name.substring(name.length() - 2, name.length())))) {
				name = name.substring(0, name.length() - 2);
			}
			throw new RuntimeException(name);
		}
	}

	public static void assertNotEmpty(Object obj, String name) {
		if (isEmpty(obj)) {
			if ((!isEmpty(name)) && (name.length() > 2)
					&& ("_V".equals(name.substring(name.length() - 2, name.length())))) {
				name = name.substring(0, name.length() - 2);
			}
			throw new RuntimeException(name);
		}
	}

	public static void assertNotEmpty(Collection<?> obj, String name) {
		if (isEmpty(obj)) {
			if ((!isEmpty(name)) && (name.length() > 2)
					&& ("_V".equals(name.substring(name.length() - 2, name.length())))) {
				name = name.substring(0, name.length() - 2);
			}
			throw new RuntimeException(name);
		}
	}

	public static boolean notEmpty(Collection<?> obj) {
		return !isEmpty(obj);
	}

	public static boolean notEmpty(Map<?, ?> obj) {
		return !isEmpty(obj);
	}

	public static boolean notEmpty(Object[] obj) {
		return !isEmpty(obj);
	}

	public static boolean notEmpty(Object obj) {
		return !isEmpty(obj);
	}
}