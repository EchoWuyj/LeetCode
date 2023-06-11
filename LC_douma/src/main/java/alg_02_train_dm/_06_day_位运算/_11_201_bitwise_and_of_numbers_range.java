package alg_02_train_dm._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-23 12:33
 * @Version 1.0
 */
public class _11_201_bitwise_and_of_numbers_range {

    /*
        201. 数字范围按位与
        给你两个整数 left 和 right ，表示区间 [left, right] ，
        返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。

        示例 1：
        输入：left = 5, right = 7
        输出：4

        示例 2：
        输入：left = 0, right = 0
        输出：0

        提示：0 <= left <= right <= 2^31 - 1

     */

    // KeyPoint 方法一 暴力解法
    // 0 <= left <= right <= 2^31 - 1 => 10^10 => 超出时间限制
    public int rangeBitwiseAnd1(int left, int right) {
        int ans = left;
        for (int i = left + 1; i <= right; i++) {
            ans &= i;
        }
        return ans;
    }

    // KeyPoint 方法二 找规律，位运算，右移和左移
    // O(k) k 最大为 32
    public int rangeBitwiseAnd2(int left, int right) {

        // 优化：通过找规律来优化时间复杂度
        // 9-12
        // 9  0000 0000 0000 0000 0000 0000 0000 1001
        // 10 0000 0000 0000 0000 0000 0000 0000 1010
        // 11 0000 0000 0000 0000 0000 0000 0000 1011
        // 12 0000 0000 0000 0000 0000 0000 0000 1100
        // ------------------------------------------
        //    0000 0000 0000 0000 0000 0000 0000 1000
        // 因为 9 到 12 是连续的，除公共前缀部分之外，后面位的 0 和 1 都是交错出现的，相与的结果必然为 0

        // 通过 left 和 right 找到公共前缀
        //  9  0000 0000 0000 0000 0000 0000 0000 1001
        // 12  0000 0000 0000 0000 0000 0000 0000 1100
        // ------------------------------------------
        // left 和 right 不断右移，同时记录移动位数 shift ，直到 left = right
        // 即找到了二进制串的公共前缀，再将其左移 shift，则为最后的 res

        int shift = 0;
        while (left < right) {
            // left 和 right 都是正数，直接可以有符号右移动
            left >>= 1;
            right >>= 1;
            shift++;
        }
        return left << shift;
    }

    // KeyPoint 方法三 不断的抹掉 right 的最后一个 1，一直到right<left为止
    public int rangeBitwiseAnd(int left, int right) {
        while (left < right) {
            // 不断抹掉 right 最后一位 1，直到 left == right
            right = right & (right - 1);
        }
        return right;
    }
}
