package alg_02_train_dm._06_day_位运算;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-04-22 19:44
 * @Version 1.0
 */
public class _08_137_single_number_ii {

    /*
        137. 只出现一次的数字 II
        给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。
        请你找出并返回那个只出现了一次的元素。
        你必须设计并实现线性时间复杂度的算法且不使用额外空间来解决此问题。

        示例 1：
        输入：nums = [2,2,3,2]
        输出：3

        示例 2：
        输入：nums = [0,1,0,1,0,1,99]
        输出：99

        提示：
        1 <= nums.length <= 3 * 10^4
        -2^31 <= nums[i] <= 2^31 - 1
        nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次

     */

    // KeyPoint 方法一 Set
    public int singleNumber1(int[] nums) {
        // 存储第一次出现的数字
        Set<Integer> once = new HashSet<>();
        // 存储第二次出现的数字
        Set<Integer> twice = new HashSet<>();

        for (int n : nums) {

            // set1 已经存在，说明该 num 第二次出现，将其移除
            if (once.contains(n)) {
                once.remove(n);
            } else if (!twice.contains(n)) {
                // set1，set2 都没有，第一次出现，加入 set1 中
                once.add(n);
            }

            // set2 已经存在，说明该 num 第三次出现，将其移除
            if (twice.contains(n)) {
                twice.remove(n);
            } else if (!once.contains(n)) {
                // 不在 set1 中，则是第二次出现，加入 set2 中
                twice.add(n);
            }
        }
        return once.iterator().next();
    }

    // KeyPoint 方法二 位运算 异或
    public int singleNumber2(int[] nums) {
        int once = 0, twice = 0;
        for (int num : nums) {
            // Set 和 异或 转换
            // 1 num 第一次出现在 once 中，且 twice 中没有，将其加入 once
            // 2 once 已经存在 num (num 第二次出现在 once 中)，将其从 once 移除
            once = (once ^ num) & ~twice;
            twice = (twice ^ num) & ~once;

            // 加深对代码理解
            // [3,3,9,3,9,6,9]

            // once 0000
            // twice 0000

            // once = (once ^ num) & ~twice;
            // twice = (twice ^ num) & ~once;

            // 第一个 num = 3
            // once = (once ^ num) & ~twice;
            //    0000
            //  ^ 0011
            // ---------
            //    0011   ~twice 1111
            //  & 1111
            // ---------
            //    0011   once 0011 => 3 => 第一此出现 3

            // twice = (twice ^ num) & ~once;
            //    0000
            //  ^ 0011
            // ---------
            //    0011   ~once 1100
            //  & 1100
            // ---------
            //    0000   twice  0000 => 0

            // 第二个 num = 3
            // once = (once ^ num) & ~twice;
            //    0011
            //  ^ 0011
            // ---------
            //    0000   ~twice 1111
            //  & 1111
            // ---------
            //    0000   once 0000 => 0

            // twice = (twice ^ num) & ~once;
            //    0000
            //  ^ 0011
            // ---------
            //    0011   ~once 1111
            //  & 1111
            // ---------
            //    0011   twice  0011 => 3 => 第二次出现 3

        }
        return once;
    }

    // KeyPoint 方法三 二进制
    // 统计每个数字指定位上的 1 的个数，通过 1 的个数可以确定只出现一次数字
    public int singleNumber(int[] nums) {

        // 3  0011
        // 3  0011
        // 6  0110
        // 3  0011
        // ----------
        //    0143 => 0110 => 6
        //    二进制位上数字，不是 3 的倍数，且不是 0
        //    则说明只出现一次的数字的二进制位在这一位是 1

        int res = 0;
        // int 类型有 32 位，统计每一位 1 的个数
        for (int i = 0; i < 32; i++) {
            // 统计第 i 位中 1 的个数
            int oneCount = 0;
            for (int num : nums) {
                // 判断 num 最后一位是否为 1 => (num >> i) & 1
                oneCount += (num >> i) & 1;
            }
            // 如果 1 的个数不是 3 的倍数，说明那个只出现一次的数字的二进制位中在这一位是 1
            if (oneCount % 3 == 1) {
                // 在 res 在对应 i 位设置 1
                // res 0000
                //   | 0010
                // ----------
                //     0010
                res |= (1 << i);
            }
        }
        return res;
    }
}
