package alg_02_train_dm._25_day_贪心算法二;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 14:55
 * @Version 1.0
 */
public class _09_1029_two_city_scheduling {
     /*
        1029. 两地调度
        公司计划面试 2n 人。给你一个数组 costs(二维数组)，其中 costs[i] = [aCosti, bCosti] 。
        第 i 人飞往 a 市的费用为 aCosti ，飞往 b 市的费用为 bCosti 。
        返回将每个人都飞到 a 、b 中某座城市的最低费用，要求每个城市都有 n 人抵达。

        示例 1：
        输入：costs = [[10,20],[30,200],[400,50],[30,20]]
        输出：110
        解释：
        第一个人去 a 市，费用为 10。
        第二个人去 a 市，费用为 30。
        第三个人去 b 市，费用为 50。
        第四个人去 b 市，费用为 20。
        最低总费用为 10 + 30 + 50 + 20 = 110，每个城市都有一半的人在面试。

        示例 2：
        输入：costs = [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
        输出：1859


        提示：
        2 * n == costs.length
        2 <= costs.length <= 100
        costs.length 为偶数
        1 <= aCosti, bCosti <= 1000


        KeyPoint 分析:
        我们这样来看这个问题，公司首先将这 2N 个人全都安排飞往 B 市，再选出 N 个人改变它们的行程，让他们飞往 A 市。
        如果选择改变一个人的行程，那么公司将会额外付出 price_A - price_B 的费用，这个费用可正可负。
        因此最优的方案是，选出 price_A - price_B 最小的 N 个人，让他们飞往 A 市，其余人飞往 B 市。

        示例 1
            -10      -170      350      10
        [[10,20],[30,200],[400,50],[30,20]]

              350      10
        b: [400,50],[30,20]

             -170     -10
        a: [30,200], [10,20]

        示例 2
            -511     394        259       45       722       108
        [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]

             394       259       722
        b [[448,54],[926,667],[840,118]]

             -511        45        108
        a [259,770], [184,139], [577,469]

         */

    public int twoCitySchedCost(int[][] costs) {
        // KeyPoint 二维数组中，同一个元素，不同分量作差，作为衡量好坏的标准
        // 按照 price_A - price_B 从小到大排序
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 升序排列
                // bug 修复：是 o1[0] - o1[1] 而不是 o1[0] - o2[1]
                return (o1[0] - o1[1]) - (o2[0] - o2[1]);
            }
        });

        int n = costs.length / 2;
        int total = 0;
        // 遍历前一半数组即可，不要全部遍历，后一半数组，通过索引坐标变换获取值
        for (int i = 0; i < n; i++) {
            // i 索引，取 costs[i][0] => 去 A 地
            // i + n 索引，取 costs[i + n][1] => 去 B 地
            total += costs[i][0] + costs[i + n][1];
        }

        return total;
    }
}
