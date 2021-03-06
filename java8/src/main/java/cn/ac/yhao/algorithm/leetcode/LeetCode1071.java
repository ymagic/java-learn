package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LeetCode1071 {

    /**
     * 字符串的最大公因子
     *
     * 对于字符串S 和T，只有在 S = T + ... + T（T与自身连接 1 次或多次）时，我们才认定“T 能除尽 S”。
     *
     * 返回最长字符串X，要求满足X 能除尽 str1 且X 能除尽 str2。
     *
     *
     * 示例 1：
     *
     * 输入：str1 = "ABCABC", str2 = "ABC"
     * 输出："ABC"
     *
     * 示例 2：
     *
     * 输入：str1 = "ABABAB", str2 = "ABAB"
     * 输出："AB"
     *
     * 示例 3：
     *
     * 输入：str1 = "LEET", str2 = "CODE"
     * 输出：""
     *
     * @param str1
     * @param str2
     * @return
     */
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    public String gcdOfStrings2(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        if (len2 == 0) {
            return str1;
        }
        if (len2 > len1) {
            return gcdOfStrings2(str2, str1);
        }
        if (!str1.startsWith(str2)) {
            return "";
        }
        // 这里辗转相除和 a % b 有些区别，a % b 可能会去除 n 个 b 只留下余数
        // 但是对于 str，只能每一去掉一个 b！
        return gcdOfStrings2(str2, str1.substring(len2, len1));
    }

    public String gcdOfStrings3(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        if (len1 == 0) {
            return str2;
        }
        if (len2 > len1) {
            return gcdOfStrings3(str2, str1);
        }
        if (!str1.startsWith(str2)) {
            return "";
        }
        // 这里辗转相处和 a % b 有些区别，a % b 可能会去除 n 个 b 只留下余数
        // 但是对于 str，只能每一去掉一个 b！
        return gcdOfStrings3(str2, str1.substring(len2, len1));
    }

    /**
     * 最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    private int gcd(int a, int b) {
//        int r;
//        while (b != 0) {
//            r = a % b;
//            a = b;
//            b = r;
//        }
//        return a;
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    @Test
    public void test() {
        String str1 = "TAUXXTAUXXTAUXXTAUXXTAUXX", str2 = "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX";
        Assertions.assertEquals("TAUXX", this.gcdOfStrings(str1, str2));
        Assertions.assertEquals("TAUXX", this.gcdOfStrings2(str1, str2));
        Assertions.assertEquals("TAUXX", this.gcdOfStrings3(str1, str2));
    }

}
