package alg_02_train_dm._23_day_回溯算法二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 19:03
 * @Version 1.0
 */
public class _06_679_24_game {
     /* 679. 24 点游戏
        你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过  *，/，+，-，(，)  的运算得到 24。

        示例 1:
        输入: [4, 1, 8, 7]
        输出: True
        解释: (8-4) * (7-1) = 24

        示例 2:
        输入: [1, 2, 1, 2]
        输出: False

        注意:
        1. 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
        2. 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。
           例如，[1, 1, 1, 1]  作为输入时，表达式  -1 - 1 - 1 - 1  是不允许的。
        3. 你不能将数字连接在一起。例如，输入为  [1, 2, 1, 2]  时，不能写成 12 + 12 。
     */

    static final int TARGET = 24;
    static final double EPSILON = 1e-6; // 实数相除存在误差，1e-6 表示 1 乘以 10 的负 6 次方
    static final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;

    // 回溯
    public boolean judgePoint24(int[] nums) {
        // 实数进行加减乘除，需要将 int 转 double
        List<Double> list = new ArrayList<Double>();
        for (int num : nums) {
            // 强制类型转换，可以使用 double 来转换
            list.add((double) num);
        }
        return dfs(list);
    }

    // 节点存储每次数字计算后结果列表
    // dfs 目的找到是否有数值等于 24 叶子节点，返回值设置为 boolean
    private boolean dfs(List<Double> list) {
        if (list.size() == 0) return false;
        // list 只剩一个元素，对其判断
        if (list.size() == 1) {
            // 实数运算存在误差，若在误差范围内，则返回 true
            return Math.abs(list.get(0) - TARGET) < EPSILON;
        }

        int size = list.size();
        // i,j 指针表示两个数字
        // 注意:涉及运算，a b 和 b a 顺序结果是不同的
        // 双层 for 循环，一共的结果
        // [0,1]，[0,2]，[0,3] | [1,0]，[1,2]，[1,3]
        // [2,0]，[2,1]，[2,3] | [3,0]，[3,1]，[3,2]
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // i,j 不能是同一个数字
                if (i != j) {
                    // 集合中存储 Double，而不是 double
                    List<Double> childList = new ArrayList<Double>();
                    // 子节点存储除 i,j 之外的数字
                    for (int k = 0; k < size; k++) {
                        if (k != i && k != j) {
                            childList.add(list.get(k));
                        }
                    }
                    // 加减乘除，故循环 4 次
                    for (int k = 0; k < 4; k++) {
                        // +，* 满足交换律，0 + 1 = 1 + 0，=> k < 2，
                        // i > j 时，必然有 i < j 已经计算过了 +，*，相当于'剪枝'
                        if (k < 2 && i > j) continue;
                        if (k == ADD) {
                            childList.add(list.get(i) + list.get(j));
                        } else if (k == MULTIPLY) {
                            childList.add(list.get(i) * list.get(j));
                        } else if (k == SUBTRACT) {
                            childList.add(list.get(i) - list.get(j));
                        } else if (k == DIVIDE) {
                            // 被除数 ÷ 除数 = 商 => '在前被除'
                            // 除法需要判断'除数'是否为 0，即对误差有判断
                            // 若 j 对应值小于误差，将其看做 0，跳过
                            if (Math.abs(list.get(j)) < EPSILON) {
                                continue;
                            } else {
                                childList.add(list.get(i) / list.get(j));
                            }
                        }
                        // 对应子节点集合递归遍历
                        if (dfs(childList)) return true;
                        // '还原现场' => 删除最后添加元素
                        childList.remove(childList.size() - 1);
                    }
                }
            }
        }
        // 所有的 for 循环执行结束，都没有找到 24 ，则返回 false
        return false;
    }
}
