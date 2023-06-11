package alg_02_train_dm._25_day_贪心算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 12:18
 * @Version 1.0
 */
public class _04_134_gas_station {
      /*
        134. 加油站
        在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
        你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
        你从其中的一个加油站出发，开始时油箱为空。
        如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。

        说明:
        如果题目有解，该答案即为唯一答案。
        输入数组均为非空数组，且长度相同。
        输入数组中的元素均为非负数。

        示例 1:
        输入:
        gas  = [1,2,3,4,5]
        cost = [3,4,5,1,2]
        输出: 3
        解释:
        从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
        开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
        开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
        开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
        开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
        开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
        因此 3 可为起始索引。

        示例 2:
        输入:
        gas  = [2,3,4]
        cost = [3,4,3]
        输出: -1
        解释:
        你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
        我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
        开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
        开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
        你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
        因此，无论怎样，你都不可能绕环路行驶一周。
     */

    // KeyPoint 方法一 模拟求解:每个加油站作为起始点，进行逐一尝试，判断加油站能否走一圈
    // KeyPoint 时间复杂度 O(n^2)，分析是否超出时间限制，看数据规模 n，而不是数值范围
    // 数据规模 => 1 <= n <= 10^5 => 超出时间限制  √
    // 数值范围 => 0 <= gas[i], cost[i] <= 104 ×
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        // 从每个加油站尝试
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] < cost[i]) continue;
            int index = i;
            int remainGas = gas[i] - cost[i];
            while (remainGas >= 0) {
                // 取余，避免越界
                // index % 数组的长度 => 循环数组
                index = (index + 1) % gas.length;
                // 更新 index，对 index 进行判断，绕了一圈，回到起点
                if (index == i) return i;
                remainGas = remainGas - cost[index] + gas[index];
            }
        }
        return -1;
    }

    // KeyPoint 方法二 优化:确定有些加油站不能走一圈，跳过这些加油站不去处理，从而降低时间复杂度
    // 贪心思想
    // 结论：如果 x 到不了 y+1（但能到 y），那么从 x 到 y 的任一点出发都不可能到达 y+1。
    // 解释：因为从其中任一点出发的话，相当于从 0 开始加油，而如果从 x 出发到该点则不一定是从 0 开始加油
    //      可能还有剩余的油。既然不从 0 开始都到不了y+1，那么从 0 开始就更不可能到达 y+1 了
    // 时间复杂度 O(n)
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // gas        4 4  3 4 5
        // cost       3 4  5 1 2
        // gas- cost  1 0 -2 3 2 => KeyPoint 两个数组相减是常用手段，需要掌握
        int n = gas.length;
        // 总油量
        int totalGas = 0;
        // 当前总油量
        int currGas = 0;
        // 起始加油站
        int startStation = 0;
        // O(n)，只需要遍历一遍数组
        for (int i = 0; i < n; i++) { // O(n)
            // 将所有站的 gas[i] - cost[i] 累和
            // KeyPoint 注意，+= 和 =，两者不要搞混淆了
            totalGas += gas[i] - cost[i];
            currGas += gas[i] - cost[i];
            if (currGas < 0) {
                // 更换起始加油站，直接从 i + 1 开始，因为 startStation ~ i 中所有的站，都是到达不了 i + 1 位置的
                startStation = i + 1;
                // 重置当前总油量
                currGas = 0;
            }
        }
        // totalGas >= 0，说明可以行驶一周，返回起始加油站
        return totalGas >= 0 ? startStation : -1;
    }
}
