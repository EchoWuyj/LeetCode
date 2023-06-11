package alg_02_train_dm._02_day_一维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 12:36
 * @Version 1.0
 */
public class _02_189_rotate_array {

    /*
        189. 轮转数组
        给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。

        进阶
        1 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
        2 你可以使用空间复杂度为O（1）的原地算法解决这个问题吗？

        提示
        1 <= nums.length <= 10^5
        -2^31 <= nums[i] <= 2^31 - 1
        0 <= k <= 10^5 => O(k*n) 10^5 * 10^5 = 10^10 × 必定超时

        输入: nums = [1,2,3,4,5,6,7], k = 3
        输出: [5,6,7,1,2,3,4]  => 将后面 3 个元素 [5,6,7]，从后移动到数组前面 [1,2,3,4]
                               => [5,6,7,1,2,3,4]
        解释:
        向右轮转 1 步: [7,1,2,3,4,5,6] => 每个元素依次向右移动 1 位
        向右轮转 2 步: [6,7,1,2,3,4,5] => 每个元素依次向右移动 2 位
        向右轮转 3 步: [5,6,7,1,2,3,4] => 每个元素依次向右移动 3 位


        输入：nums = [-1,-100,3,99], k = 2
        输出：[3,99,-1,-100]
        解释:
        向右轮转 1 步: [99,-1,-100,3]
        向右轮转 2 步: [3,99,-1,-100]

     */

    // KeyPoint 方法一：使用额外数组
    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        // KeyPoint
        //  1 避免 k > n，避免后续 nums[k] 出现越界
        //  2 k % n ，将 k 多走的 m 轮，通过取余的方式消除
        k = k % n;
        // 临时存储旋转之后的数组
        int[] newArr = new int[n];
        for (int i = 0; i < n; i++) {
            // 抽象公式，通过具体例子来验证 => i + k，通过 i = 0，k = 3 验证其正确性
            // 取余数组长度，避免越界
            int index = (i + k) % n;
            newArr[index] = nums[i];
        }
        // newArr copy 到原数组 nums 中
        // 记住 System.arraycopy
        System.arraycopy(newArr, 0, nums, 0, n);
    }

    // KeyPoint 方法二：环状替换
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        // 标记已处理元素个数，当所有元素都处理过一遍，环状替换结束
        int count = 0;
        // start 标记每个环状的起始点
        // KeyPoint 注意 for 循环中存在重复操作，没有必要循环 n 次，后续可以优化
        for (int start = 0; count < n; start++) {
            // 当前处理元素，从 start 开始
            int curr = start;
            // 记录 start 位置的元素值
            int prev = nums[start];

            // KeyPoint 为什么使用 do while
            // 使用 do while 为了至少一次循环体，因为一开始 start == cur，若使用 while 循环
            // 则循环条件 while(start != curr)，则一开始 while 循环里代码一个都不会执行的

            // 使用 do while 来实现'循环替换'
            do {
                // start 的下个位置，即 next 索引
                int next = (curr + k) % n;
                // 交换代码
                int tmp = nums[next];
                nums[next] = prev;
                prev = tmp;

                // cur 后移到 next 指针
                curr = next;
                // 已处理元素个数 + 1
                count++;
                // 当 start != curr 则一直循环
            } while (start != curr);
        }
    }

    // KeyPoint 方法二：环状替换 => 优化
    public void rotate22(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        // KeyPoint 优化
        // 1 通过数学公式计算得到'循环次数'，而不是 for 循环 n 次
        // 2 计算循环的次数等于 n 和 k 的公约数
        int count = gcd(k, n);
        for (int start = 0; start < count; start++) {
            int curr = start;
            int prev = nums[start];
            // 循环替换
            do {
                int next = (curr + k) % n;
                int tmp = nums[next];
                nums[next] = prev;
                prev = tmp;
                curr = next;
            } while (start != curr);
        }
    }

    // KeyPoint 计算 a 和 b 的最大公约数
    // 比如：6 和 2 的最大公约数是 2；10 和 4 的最大公约数是 2
    public int gcd(int a, int b) {
        // 结论：如果 a 和 b 是两个正整数，且 a>b，则有 gcd(a,b) = gcd(b, a%b)
        // a = 6, b = 3 => gcd(6,3)  => gcd(3, 6%3) => 3
        // KeyPoint 'baba' 结构
        return b > 0 ? gcd(b, a % b) : a;
    }

    // KeyPoint 方法三：数组旋转
    public void rotate(int[] nums, int k) {

        // nums： 1 2 3 4 5 6 7
        // 目标：5 6 7 1 2 3 4
        // 反转数组： 7 6 5 4 3 2 1
        // 反转 [0,k-1]：5 6 7 | 4 3 2 1
        // 反转 [k,n-1]：5 6 7 | 1 2 3 4

        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        // 0 ~ k-1 保证 k 个
        reverse(nums, 0, k - 1);
        // 剩余的元素
        reverse(nums, k, n - 1);
    }

    // 数组反转
    private void reverse(int[] nums, int start, int end) {
        // start == end 跳出循环
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}
