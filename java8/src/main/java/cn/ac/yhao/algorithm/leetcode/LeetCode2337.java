package cn.ac.yhao.algorithm.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @description: 2337. 移动片段得到字符串<br>
 * 给你两个字符串 start 和 target ，长度均为 n 。每个字符串 仅 由字符 'L'、'R' 和 '_' 组成，其中：<br>
 *
 * 字符 'L' 和 'R' 表示片段，其中片段 'L' 只有在其左侧直接存在一个 空位 时才能向 左 移动，而片段 'R' 只有在其右侧直接存在一个 空位 时才能向 右 移动。<br>
 * 字符 '_' 表示可以被 任意 'L' 或 'R' 片段占据的空位。<br>
 * 如果在移动字符串 start 中的片段任意次之后可以得到字符串 target ，返回 true ；否则，返回 false 。<br>
 *
 *
 *<br>
 * 示例 1：<br>
 *
 * 输入：start = "_L__R__R_", target = "L______RR" <br>
 * 输出：true <br>
 * 解释：可以从字符串 start 获得 target ，需要进行下面的移动： <br>
 * - 将第一个片段向左移动一步，字符串现在变为 "L___R__R_" 。 <br>
 * - 将最后一个片段向右移动一步，字符串现在变为 "L___R___R" 。 <br>
 * - 将第二个片段向右移动三步，字符串现在变为 "L______RR" 。 <br>
 * 可以从字符串 start 得到 target ，所以返回 true 。<br>
 * <br>
 * 示例 2：<br>
 *
 * 输入：start = "R_L_", target = "__LR"<br>
 * 输出：false <br>
 * 解释：字符串 start 中的 'R' 片段可以向右移动一步得到 "_RL_" 。 <br>
 * 但是，在这一步之后，不存在可以移动的片段，所以无法从字符串 start 得到 target 。 <br>
 *  <br>
 * 示例 3： <br>
 *
 * 输入：start = "_R", target = "R_" <br>
 * 输出：false <br>
 * 解释：字符串 start 中的片段只能向右移动，所以无法从字符串 start 得到 target 。 <br>
 *
 * <br>
 * 提示： <br>
 *
 * n == start.length == target.length <br>
 * 1 <= n <= 105 <br>
 * start 和 target 由字符 'L'、'R' 和 '_' 组成 <br>
 */
public class LeetCode2337 {

    public boolean canChange(String start, String target) {
        //return String.join("",start.split("_")).equals(String.join("",target.split("_")));
        return false;
    }

    @Test
    public void test() {
        Assertions.assertEquals(true, this.canChange("_L__R__R_","L______RR"));
        Assertions.assertEquals(false, this.canChange("R_L_","__LR"));
        Assertions.assertEquals(false, this.canChange("_R","R_"));

    }

    @Test
    public void test1() {
        System.out.println(String.join("", "_L__R__R_".split("_")));


    }
}
