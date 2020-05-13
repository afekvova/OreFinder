package ru.afek.orefinder.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import org.bukkit.ChatColor;

public class StringHelper {

	/**
	 * by Bars Склоняем слова правильно
	 * 
	 * @param ed
	 *            неизменяемая часть слова, которую нужно просклонять
	 * @param a
	 *            окончание для слова, в случае если число оканчивается на 1
	 * @param b
	 *            окончание для слова, в случае если число оканчивается на 2, 3
	 *            или 4
	 * @param c
	 *            окончание для слова, в случае если число оканчивается на 0,
	 *            5...9 и 11...19
	 * @param n
	 *            число, по которому идёт склонение
	 * @return правильно просклонённое слово по числу
	 */
	public static String padezh(String ed, String a, String b, String c,
			int n) {
		if (n < 0)
			n = -n;
		int last = n % 100;
		if (last > 10 && last < 21)
			return ed + c;
		last = n % 10;
		if (last == 0 || last > 4)
			return ed + c;
		if (last == 1)
			return ed + a;
		if (last < 5)
			return ed + b;
		return ed + c;
	}

	public static String replaceTime(String message, int time) {
		return message.replace("%time%", Integer.toString(time)).replace(
				"%formated-sec%", padezh("секунд", "у", "ы", "", time));
	}

	public static String join(Collection<?> collection, String delimiter) {
		return join(collection.toArray(), delimiter);
	}

	public static String joinOrdered(Collection<?> collection,
			String delimiter) {
		Object[] array = collection.toArray();
		Arrays.sort(array, new Comparator<Object>() {
			@Override
			public int compare(Object a, Object b) {
				return a.hashCode() - b.hashCode();
			}

		});
		return join(array, delimiter);
	}

	public static String join(Collection<?> collection, char delimiter) {
		return join(collection.toArray(), delimiter + "");
	}

	public static String join(Object[] array, String delimiter) {
		StringBuilder result = new StringBuilder();
		for (int i = 0, j = array.length; i < j; i++) {
			if (i > 0) {
				result.append(delimiter);
			}
			result.append(array[i]);
		}
		return result.toString();
	}

	public static String join(int[] array, String delimiter) {
		Integer[] wrapped = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			wrapped[i] = array[i];
		}
		return join(wrapped, delimiter);
	}

	public static boolean isEqual(String a, String b) {
		if ((a == null && b != null) || (a != null && b == null)) {
			return false;
		} else if (a == null /* implies that b is null */) {
			return false;
		}
		return a.equals(b);
	}

	public static String repeat(String s, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(s);
		}
		return sb.toString();
	}

	public static String color(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}
