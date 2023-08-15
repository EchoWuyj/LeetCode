package alg_02_train_wyj._25_day_贪心算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-14 14:14
 * @Version 1.0
 */
public class _04_134_gas_station {
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            if (gas[i] < cost[i]) continue;
            int index = i;
            int restGas = gas[i] - cost[i];
            while (restGas >= 0) {
                index = (index + 1) % n;
                if (index == i) return i;
                restGas = restGas + gas[index] - cost[index];
            }
        }
        return -1;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int sumGas = 0;
        int curGas = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            sumGas += gas[i] - cost[i];
            curGas += gas[i] - cost[i];
            if (curGas < 0) {
                start = i + 1;
                curGas = 0;
            }
        }
        return sumGas >= 0 ? start : -1;
    }
}
