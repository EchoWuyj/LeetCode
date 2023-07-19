package alg_01_ds_dm._01_line._05_algo._01_twopoint;

/**
 * @Author Wuyj
 * @DateTime 2023-03-10 18:55
 * @Version 1.0
 */


public class _01_283_MoveZeroes {

    /*
        283. 移动零
        给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序

        请注意，必须在不复制数组的情况下原地对数组进行操作。
        尽量减少操作次数

        输入: nums = [0,1,0,3,12]
        输出: [1,3,12,0,0]

        输入: nums = [0]
        输出: [0]
     */

    // KeyPoint 方法一:朴素方法 => 额外空间
    // 一开始没有很好的思路的话，则是使用最朴素的方式来解决，从时间和空间角度来思考
    // 时间复杂度 O(n) => 无法优化，毕竟是要遍历一般数组
    // 空间复杂度 O(n) => 是否能在常量级别空间复杂度解决该问题
    public static void moveZeroes1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int[] tmp = new int[nums.length];
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                tmp[j] = nums[i];
                j++;
            }
        }

        // 再去遍历一遍数组进行赋值，保证输入数组和输出数组是同一个数组，即在原来输入数组上完成算法
        for (int i = 0; i < nums.length; i++) {
            nums[i] = tmp[i];
        }
    }

    // KeyPoint 方法二:双指针实现(快慢指针) => 节省空间
    // 时间复杂度 O(n)
    // 空间复杂度 O(1)
    public static void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        // 提高代码的可读性，修改 i 和 j 变成 fast 和 slow
        // slow 指向最后一个非零元素的下个元素
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                // 减少交换的次数
                if (slow != fast) {
                    // 交换 fast 和 slow 指向的元素
                    // 使用交换，每个元素都会被访问两次
                    // 交换两次会影响性能
                    int tmp = nums[fast];
                    nums[fast] = nums[slow];
                    nums[slow] = tmp;
                }
                // slow == fast 不用交换，但是 slow 得前移，指向最后一个非零元素的下个元素
                slow++;
            }
            // nums[fast] == 0，只要移动 fast 即可，不用移动 slow
        }
    }

    // KeyPoint 方法三:双指针实现 => 常数项优化
    // 时间复杂度 O(n)
    // 空间复杂度 O(1)
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                // fast 和 slow 指针在同一个位置没有必要交换
                // 减少赋值的次数
                if (slow != fast) {
                    // 直接赋值方式，不是使用元素交换
                    // 赋值每个元素只会被访问一次
                    nums[slow] = nums[fast];
                }
                slow++;
            }
        }
        // 将 slow 及其往后位置，都赋值为 0
        for (; slow < nums.length; slow++) {
            nums[slow] = 0;
        }
    }

    // while 循环形式
    public static void moveZeroes3(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int slow = 0, fast = 0;
        int n = nums.length;
        while (fast < n) {
            if (nums[fast] != 0) {
                if (fast != slow) {
                    nums[slow] = nums[fast];
                }
                slow++;
            }
            fast++;
        }

        while (slow < n) {
            nums[slow] = 0;
            slow++;
        }
    }
}
