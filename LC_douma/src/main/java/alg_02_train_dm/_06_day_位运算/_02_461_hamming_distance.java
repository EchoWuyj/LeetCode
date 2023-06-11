package alg_02_train_dm._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-21 20:46
 * @Version 1.0
 */
public class _02_461_hamming_distance {
    /*
        461. 汉明距离
        两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
        给你两个整数 x 和 y，计算并返回它们之间的汉明距离。

        示例 1：
        输入：x = 1, y = 4
        输出：2
        解释：
        1   (0 0 0 1)
        4   (0 1 0 0)
               ↑   ↑
        上面的箭头指出了对应二进制位不同的位置。

        示例 2：
        输入：x = 3, y = 1
        输出：1

        提示：0 <= x, y <= 2^31 - 1

     */

    public int hammingDistance(int x, int y) {
        // 二进制位不同 => 联想'异或'运算 => 使用异或计算 x 和 y 的不同位，结果中位为 1 ，说明这位不同
        int diff = x ^ y;

        // 计算 diff 中位 1 个数
        int res = 0;
        while (diff != 0) {
            // bug 修复：这里是 &，去掉最后一个 1
            diff = diff & (diff - 1);
            res++;
        }

        return res;
    }
}
