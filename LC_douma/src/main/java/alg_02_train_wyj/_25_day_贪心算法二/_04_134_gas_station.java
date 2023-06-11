package alg_02_train_wyj._25_day_贪心算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-14 14:14
 * @Version 1.0
 */
public class _04_134_gas_station {
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] < cost[i]) continue;
            int index = i;
            int remainGas = gas[i] - cost[i];
            while (remainGas >= 0) {
                index = (index + 1) % gas.length;
                if (index == i) return i;
                remainGas = remainGas + gas[index] - cost[index];
            }
        }
        return -1;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int curGas = 0;
        int startStation = 0;
        int totalGas = 0;
        for (int i = 0; i < n; i++) {
            totalGas += gas[i] - cost[i];
            curGas += gas[i] - cost[i];
            if (curGas < 0) {
                startStation = i + 1;
                curGas = 0;
            }
        }
        return totalGas >= 0 ? startStation : -1;
    }
}
