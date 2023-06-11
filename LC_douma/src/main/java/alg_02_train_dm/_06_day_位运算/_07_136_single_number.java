package alg_02_train_dm._06_day_位运算;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-04-22 16:24
 * @Version 1.0
 */
public class _07_136_single_number {

    /*
        136. 只出现一次的数字
        给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。
        找出那个只出现了一次的元素。
        你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
        示例 1 ：

        输入：nums = [2,2,1]
        输出：1
        示例 2 ：

        输入：nums = [4,1,2,1,2]
        输出：4
        示例 3 ：

        输入：nums = [1]
        输出：1

        提示：

        1 <= nums.length <= 3 * 10^4
        -3 * 10^4 <= nums[i] <= 3 * 10^4
        除了某个元素只出现一次以外，其余每个元素均出现两次。

     */

    // KeyPoint 方法一 Map
    // 最简单的方法就是使用 Map 记录每个数字出现的次数，然后遍历 Map 找出只出现一次的数字
    // 空间复杂度为O（n）
    public int singleNumber1(int[] nums) {
        Set<Integer> single = new HashSet<>();
        for (int num : nums) {
            if (single.contains(num)) {
                single.remove(num);
            } else {
                single.add(num);
            }
        }

        // 返回迭达器中第一元素
        return single.iterator().next();
    }

    // KeyPoint 方法二 位运算异或
    // 异或性质 => 记忆：在于'异'
    // 1 a ^ 0 = a
    // 2 a ^ a = 0
    // 3 a ^ b ^ c= a ^ c ^ b 交换律
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            // Set 和 异或 转换
            // num 第一次出现在 single 中，将其加入 single
            // num 第二次出现在 single 中，将其从 single 移除
            single ^= num;
        }
        return single;
    }
}
