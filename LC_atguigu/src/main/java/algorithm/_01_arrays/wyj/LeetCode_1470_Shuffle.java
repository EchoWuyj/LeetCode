package algorithm._01_arrays.wyj;

/**
 * @Author Wuyj
 * @DateTime 2022-08-29 9:39
 * @Version 1.0
 */
public class LeetCode_1470_Shuffle {
    public int[] shuffle(int[] nums, int n) {
        // 一次遍历

        // 使用res表示结果数组
        // KeyPoint 创建数组不需要括号
        int[] res = new int[n * 2];

        // 数组长度为2n,但是遍历只需n次,一次遍历2次赋值操作
        for (int i = 0; i < n; i++) {

            // 结果数组的奇数元素(数组下标为偶数)
            res[i * 2] = nums[i];
            // 结果数组的偶数元素(数组下标为奇数),间隔n元素,从n+i开始
            res[i * 2 + 1] = nums[n + i];
        }
        return res;
    }
}
