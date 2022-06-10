package me.afek.orefinder.utils;

import org.bukkit.ChatColor;

public class StringHelper {

    /**
     * by Bars Склоняем слова правильно
     *
     * @param ed неизменяемая часть слова, которую нужно просклонять
     * @param a  окончание для слова, в случае если число оканчивается на 1
     * @param b  окончание для слова, в случае если число оканчивается на 2, 3
     *           или 4
     * @param c  окончание для слова, в случае если число оканчивается на 0,
     *           5...9 и 11...19
     * @param n  число, по которому идёт склонение
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
