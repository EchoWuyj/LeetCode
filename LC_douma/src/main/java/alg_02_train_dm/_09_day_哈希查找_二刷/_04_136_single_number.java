package alg_02_train_dm._09_day_哈希查找_二刷;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 14:29
 * @Version 1.0
 */
public class _04_136_single_number {
    /*
        leetcode 136 号算法题：只出现一次的数字
        给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。
        找出那个只出现了一次的元素。

        输入: [2,2,1]
        输出: 1

        输入: [4,1,2,1,2]
        输出: 4

        你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     */

    // KeyPoint 思考过程
    // 1.先暴力
    // 2.排序优化
    // 3.哈希查找优化

    // KeyPoint 方法一 暴力
    // O(n^2)
    public int singleNumber1(int[] nums) {
        if (nums.length == 1) return nums[0];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            boolean isExist = true;
            for (int j = 0; j < n; j++) {
                // 保证 i 和 j 不相等，不一定是 i 在前，j 在后，存在重复计算
                if (i != j && nums[j] == nums[i]) {
                    isExist = false;
                    break;
                }
            }
            // 内层 for 循环遍历结束，若 isExist 还是 true，
            // 则 nums[i] 为只出现一次的数字，直接 return
            if (isExist) return nums[i];
        }
        return -1;
    }

    // KeyPoint 方法二 排序
    public int singleNumber2(int[] nums) {
        if (nums.length == 1) return nums[0];
        // 排序，相同元素放在一起，判断前后元素是否相同即可，遍历一遍数据即可
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 判断：第一个元素，和后面一个元素比较
            if (i == 0 && nums[i] != nums[i + 1]) return nums[i];
                // 判断：最后一个元素，和前面一个元素比较
            else if (i == nums.length - 1 && nums[i] != nums[i - 1]) return nums[i];
                // 判断：中间元素，和前后元素都要比较
                // KeyPoint 注意实现
                // 1.i 需要 > 0，否则 i - 1 就是负数了，因为最开始的 if 是有 2 个判断条件
                //   但不一定都成立，故不能保证一定有 i > 0
                //   => 凡是涉及数组元素索引，必定加以越界判断，即使判断条件可能存在冗余，但是保证代码不会存在 bug
                // 2.if else 判断是多分支中选择一个执行，类似于多个 if，前一个 if 对于后面的 if 具有排他性
            else if (i > 0 && nums[i] != nums[i - 1] && i + 1 < n && nums[i] != nums[i + 1])
                return nums[i];
        }
        return -1;
    }

    // KeyPoint 方法三 哈希查找
    public int singleNumber3(int[] nums) {
        if (nums.length == 1) return nums[0];

        // 1.HashSet 去重，只能判断是否存在，但是无法判断出现的次数
        // 2.HashMap 映射表 => 记录 num 的次数
        //   key => num
        //   value => count
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            // map.getOrDefault(num, 0) 获取 num 对应的次数，没有则使用默认的 0
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // KeyPoint 注意事项
        // 1.Map.Entry  Entry 要能手写出来，在线 OJ 是没有代码提示的
        // 2.需要加上泛型 <Integer, Integer>
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return -1;
    }

    // KeyPoint 方法四 位运算
    public int singleNumber(int[] nums) {
        /*
            1 交换律：a ^ b ^ c <=> a ^ c ^ b
            2 任何数与 0 异或为任何数 0 ^ n => n
            3 相同的数异或为 0: n ^ n => 0
         */

        int res = 0;
        for (int num : nums) {
            // ^= -> 这种形式要熟悉，不要将 = 遗漏
            res ^= num;
        }
        return res;
    }
}
