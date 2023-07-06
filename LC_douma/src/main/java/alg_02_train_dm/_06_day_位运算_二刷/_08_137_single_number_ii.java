package alg_02_train_dm._06_day_位运算_二刷;

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

    // KeyPoint 方法一 Set (两个 Set)
    public int singleNumber1(int[] nums) {
        // 存储第一次出现的数字
        Set<Integer> once = new HashSet<>();
        // 存储第二次出现的数字
        Set<Integer> twice = new HashSet<>();
        // 第三次出现的数字不存储

        // KeyPoint 判断逻辑挺绕人
        // 循环遍历数组中每个元素
        for (int num : nums) {
            // set1 已经存在 num，说明该 num 第二次出现，将其移除
            if (once.contains(num)) {
                once.remove(num);
            } else if (!twice.contains(num)) {
                // set1 不存在 num，可能 set2 存在 num
                // 若 set1，set2 都没有 num，则 num 第一次出现，加入 set1 中
                once.add(num);
            }

            // set2 已经存在 num，说明该 num 第三次出现，将其移除
            if (twice.contains(num)) {
                twice.remove(num);
            } else if (!once.contains(num)) {
                // set2 不存在 num，且 set1 也存在，则是第二次出现，加入 set2 中
                twice.add(num);
            }
            // KeyPoint 两个 if else 是依次判断的，都是需要执行的
        }
        return once.iterator().next();
    }

    // KeyPoint 方法二 位运算 异或 => 难以想到
    // 将 哈希查找 使用 异或 替换
    public int singleNumber2(int[] nums) {
        int once = 0, twice = 0;
        for (int num : nums) {

            // 根据 Set 和 异或 转换 => 将上面 set 代码 转成 异或
            // 解释 (once ^ num) & ~twice
            // 1.once 已经存在 num，将其从 once 移除
            //    => num 第二次出现在 once 中
            // 2.once 没有 num，且 twice 中没有(否则，什么都不做)，将其加入 once
            //    => num 第一次出现在 once 中
            once = (once ^ num) & ~twice;
            twice = (twice ^ num) & ~once;

        /*
             if (once.contains(num)) {
                once.remove(num);
            } else if (!twice.contains(num)) {
                once.add(num);
            }

            if (twice.contains(num)) {
                twice.remove(num);
            } else if (!once.contains(num)) {
                twice.add(num);
            }
        */

            // KeyPoint 通过具体例子，加深对代码理解
            // [3,3,9,3,9,6,9]

            // once 0000 => 为了简单，只取 4 位
            // twice 0000

            // once = (once ^ num) & ~twice;
            // twice = (twice ^ num) & ~once;

            // KeyPoint 1.第一个 num = 3
            // once = (once ^ num) & ~twice;
            //    0000
            //  ^ 0011
            // ---------
            //    0011   ~twice 1111
            //  & 1111
            // ---------
            //    0011   once 0011 => 3 => 第一次出现 3，将其放入 once 中

            // twice = (twice ^ num) & ~once;
            //    0000
            //  ^ 0011
            // ---------
            //    0011   ~once 1100
            //  & 1100
            // ---------
            //    0000   twice  0000 => 0 => twice 还是 0，3 并没有放入到 twice 中

            // KeyPoint 2.第二个 num = 3
            // 此时 once 已经发生修改了，即 once = (once ^ num) & ~twice;
            // once = 0011

            //    0011
            //  ^ 0011
            // ---------
            //    0000   ~twice 1111
            //  & 1111
            // ---------
            //    0000   once 0000 => 0 => 第二次出现 3，将其从 once 中移除

            // twice = (twice ^ num) & ~once;
            //    0000
            //  ^ 0011
            // ---------
            //    0011   ~once 1111
            //  & 1111
            // ---------
            //    0011   twice  0011 => 3 => 第二次出现 3 => 将其加入 twice 中

        }
        return once;
    }

    // KeyPoint 方法三 二进制 => 需要掌握
    // 统计每个数字指定位上的 1 的个数，通过 1 的个数可以确定只出现一次数字
    public int singleNumber(int[] nums) {

        // 如：数字 3 3 6 3
        // 3  0 0 1 1
        // 3  0 0 1 1
        // 6  0 1 1 0
        // 3  0 0 1 1
        // ----------
        //    0 1 4 3 => 二进制位上数字，不是 3 的倍数，且不是 0
        //               则说明：只出现一次的数字的二进制位，在这一位是 1
        //    0 1 1 0 => 6

        int res = 0;
        // int 类型有 32 位，统计每一位 1 的个数
        for (int i = 0; i < 32; i++) {
            // 统计第 i 位，所有数字，中 1 的个数
            int oneCount = 0;
            for (int num : nums) {
                // 1.括号优先级更高，先执行 num 右移 i 位，再去和 1 相 &
                // 2.再判断 num 最后一位是否为 1
                oneCount += (num >> i) & 1;
            }
            // 如果 1 的个数不是 3 的倍数，且不是 0，
            // 则说明：那个只出现一次的数字的二进制位，在这一位是 1
            // => 使用 % 来表示
            if (oneCount % 3 == 1) {
                // 在 res 在对应 i 位设置 1
                // KeyPoint 记忆：置为 1，形状类于 |，故 | ( 1<<i )
                res |= (1 << i);

                // res 0000
                //   | 0010
                // ----------
                //     0010
            }
        }
        return res;
    }
}
