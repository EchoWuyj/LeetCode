package alg_02_train_dm._05_day_数学_二刷;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 14:09
 * @Version 1.0
 */
public class _03_0xx_Template_AddTwoNum {

    // KeyPoint 模板代码：数组形式 => 两数相加 => 直接模拟
    public static List<Integer> addTwoNum(int[] nums1, int[] nums2) {

        // 数组形式 => 两数相加
        // num1  1 2 7 4
        // num2    1 8 1
        //----------------
        //             ↑
        //

        // 从右往左遍历数组
        // KeyPoint l1，l2 从最后一位索引开始，故需要 length - 1，不能直接使用 length
        int l1 = nums1.length - 1;
        int l2 = nums2.length - 1;

        // 进位
        int carry = 0;
        // 结果集
        List<Integer> res = new ArrayList<>();

        // KeyPoint while循环条件
        // 1.正向逻辑：只要两个数组 num1 和 num2，有一个数位没有处理完，则 while 执行
        //   => while 循环条件：l1 >= 0 || l2 >= 0
        // 2.反向逻辑：两个数组 num1 和 num2 都已经处理完每个数位，则 while 停止
        //   => while 循环结束条件 l1 < 0 && l2 < 0，即没有位数可以处理
        //   取反 => while 执行条件 l1 >= 0 || l2 >= 0
        while (l1 >= 0 || l2 >= 0) {
            // l1 和 l2 不一定是等长，取值前需要判断 l1 和 l2 是否越界
            int x = l1 < 0 ? 0 : nums1[l1];
            int y = l2 < 0 ? 0 : nums2[l2];
            // 计算 sum 需要加上进位 carry
            int sum = x + y + carry;
            // 先将个 sum 位数加入结果集，再去计算进位
            res.add(sum % 10);
            carry = sum / 10;
            // 移动指针
            l1--;
            l2--;
        }

        // 可能还有进位 carry，需要考虑
        // 注意：不是任何情况都执行的操作 => if 判断实现
        if (carry != 0) {
            // while 循环结束，还需要将最高位相加的进位 carry 加入 res 中
            res.add(carry);
        }

        // l1 和 l2 是从两个数组的 个位 往 高位 计算的，故 res 中存储数字：低位到高位
        // 故 res 存储的是反转结果，故需要反转下
        // num1 1 2 3
        // num2 1 2 3
        // -----------
        //      2 4 6
        // res 中数字顺序：6 4 2，实际结果应该是：246
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 4};
        int[] arr2 = {2, 1};
        System.out.println(addTwoNum(arr1, arr2)); // [1, 5, 5]

        int[] arr3 = {1, 3};
        int[] arr4 = {2, 1, 3};
        System.out.println(addTwoNum(arr3, arr4)); // [2, 2, 6]

        int[] arr5 = {1, 3};
        int[] arr6 = {2, 1};
        System.out.println(addTwoNum(arr5, arr6)); // [3, 4]
    }
}
