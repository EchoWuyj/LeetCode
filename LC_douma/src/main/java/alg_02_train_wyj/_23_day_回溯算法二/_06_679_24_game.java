package alg_02_train_wyj._23_day_回溯算法二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 19:54
 * @Version 1.0
 */
public class _06_679_24_game {

    private final int TARGET = 24;
    private final double EPSILON = 1e-6;
    private final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;

    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double) num);
        }
        return dfs(list);
    }

    public boolean dfs(List<Double> list) {
        if (list.size() == 0) return false;
        if (list.size() == 1) {
            return Math.abs(list.get(0) - TARGET) < EPSILON;
        }
        int size = list.size();
        for (int num1 = 0; num1 < size; num1++) {
            for (int num2 = 0; num2 < size; num2++) {
                if (num1 != num2) {
                    List<Double> subList = new ArrayList<>();
                    for (int k = 0; k < size; k++) {
                        if (k != num1 && k != num2) {
                            subList.add(list.get(k));
                        }
                    }

                    for (int k = 0; k < 4; k++) {
                        if (k < 2 && num1 > num2) continue;
                        if (k == ADD) {
                            subList.add(list.get(num1) + list.get(num2));
                        } else if (k == MULTIPLY) {
                            subList.add(list.get(num1) * list.get(num2));
                        } else if (k == SUBTRACT) {
                            subList.add(list.get(num1) - list.get(num2));
                        } else if (k == DIVIDE) {
                            if (Math.abs(list.get(num2)) < EPSILON) {
                                continue;
                            } else {
                                subList.add(list.get(num1) / list.get(num2));
                            }
                        }
                        if (dfs(subList)) return true;
                        subList.remove(subList.size() - 1);
                    }
                }
            }
        }
        return false;
    }
}
