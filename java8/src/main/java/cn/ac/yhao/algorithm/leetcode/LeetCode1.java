package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * 两数之和
 * <p>
 *给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
 * <p>
 * 你可以按任意顺序返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 * 提示：
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 *
 *
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 */
public class LeetCode1 {

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }

    public int[] twoSum1(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target-nums[i])){
                return new int[]{map.get(target-nums[i]),i};
            }
            map.put(nums[i], i);
        }
        return new int[]{0, 0};
    }

    public int[] twoSum2(int[] nums, int target) {
        int i=0,j=1;
        int step = 1;
        int[] a=new int[2];
        boolean flag = false;
        while(!flag ){
            if(nums[i]+nums[j] == target){
                flag = true;
                break;
            }else if (j!=nums.length-1){
                i++;
                j++;
            }else if (step!=nums.length-1){
                step++;
                i=0;
                j=step;
            }else{
                break;
            }
        }
        a[0]=i;
        a[1]=j;
        return a;
    }


    @Test
    public void test() {
        show(twoSum2(new int[]{1,2,3, 5, 11, 15}, 9));

        show(twoSum2(new int[]{3, 2, 4}, 6));

        show(twoSum2(new int[]{3,3}, 6));
    }

    public void show(int[] nums) {
        for (int i : nums) {
            System.out.print(i + ",");
        }
        System.out.println(" ");
    }

}
