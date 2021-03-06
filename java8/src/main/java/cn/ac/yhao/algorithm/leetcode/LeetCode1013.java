package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LeetCode1013 {

    /**
     * 将数组分成和相等的三个部分
     *
     * 给你一个整数数组A，只有可以将其划分为三个和相等的非空部分时才返回true，否则返回 false。
     *
     * 形式上，如果可以找出索引i+1 < j且满足(A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1])就可以将数组三等分。
     *
     * @param A
     * @return
     */
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for(int i=0;i<A.length;i++) {
            sum += A[i];
        }
        if (sum%3 != 0) return false;
        int tem = sum/3;
        int i = 0, cur = 0;
        for (; i < A.length; i++) {
            cur += A[i];
            if (tem == cur) {
                break;
            }
        }
        for (int j = i +1; j < A.length-1; j++) {
            cur += A[j];
            if (cur == 2 * tem) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        int[] A = {0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1};
        Assertions.assertTrue(this.canThreePartsEqualSum(A)); // true

        A = new int[]{0, 2, 1, -6, 6, 7, 9, -1, 2, 0, 1};
        Assertions.assertFalse(this.canThreePartsEqualSum(A)); // false

        A = new int[]{3,3,6,5,-2,2,5,1,-9,4};
        Assertions.assertTrue(this.canThreePartsEqualSum(A));
    }

}
