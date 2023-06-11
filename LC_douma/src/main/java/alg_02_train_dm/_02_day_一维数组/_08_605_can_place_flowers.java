package alg_02_train_dm._02_day_一维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-29 14:45
 * @Version 1.0
 */
public class _08_605_can_place_flowers {

    /*
        605. 种花问题
        假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。
        可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
        给你一个整数数组 flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。
        另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false 。

        示例 1：
        输入：flowerbed = [1,0,0,0,1], n = 1
        输出：true => [1,0,1,0,1]

        示例 2：
        输入：flowerbed = [1,0,0,0,1], n = 2
        输出：false

        提示：
        1 <= flowerbed.length <= 2 * 10^4
        flowerbed[i] 为 0 或 1
        flowerbed 中不存在相邻的两朵花
        0 <= n <= flowerbed.length
     */

    // 直接模拟
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        // 定义指针，遍历每个花坛
        int i = 0;
        int length = flowerbed.length;
        // 当所有花坛遍历完或者花种完了，则停止循环 => 相反条件，则继续循环
        while (i < length && n > 0) {
            // 直接模拟，i 有 3 种情况，依次判断
            if (flowerbed[i] == 1) {
                // 如果当前花坛已经种花，那么至少需要到 i + 2 位置，判断该位置是否能种花
                i += 2;
                // if 不成立 => flowerbed[i] == 0
            } else if (i == length - 1 || flowerbed[i + 1] == 0) {
                // i 没有种花，且是最后一个花坛 || i 和 i + 1 的位置都没有种花， 那么 i 处肯定可以种植一朵花
                // 注意：必须将 i == flowerbed.length - 1 放在前面先判断，若是直接判断 flowerbed[i + 1]，可能越界
                // KeyPoint || 语法
                // || 前面为 true，后面不再判断，不执行 flowerbed[i + 1] ，避免越界
                // || 前面为 false，再去判断 flowerbed[i + 1]，i != flowerbed.length - 1，也不存在越界
                n--;
                // 至此，至少需要到 i + 2 的地方才能种花
                i += 2;
            } else {
                // flowerbed[i] == 0 && i != flowerbed.length - 1 && flowerbed[i + 1] == 1
                // i 处没有种花，但是 i + 1 处种花了
                // 那么这个时候，至少需要到 i + 3 位置，判断该位置是否能种花
                i += 3;
            }
        }
        // length 太长，而 n 很小，导致有很多合适的位置，从而 n--，直到 n < 0
        return n <= 0;
    }
}
