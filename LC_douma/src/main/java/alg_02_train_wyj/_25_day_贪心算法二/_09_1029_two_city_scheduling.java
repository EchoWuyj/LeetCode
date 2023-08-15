package alg_02_train_wyj._25_day_贪心算法二;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-14 20:10
 * @Version 1.0
 */
public class _09_1029_two_city_scheduling {
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (o1, o2) -> (o1[0] - o1[1]) - (o2[0] - o2[1]));
        int n = costs.length / 2;
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += costs[i][0] + costs[i + n][1];
        }
        return total;
    }
}
