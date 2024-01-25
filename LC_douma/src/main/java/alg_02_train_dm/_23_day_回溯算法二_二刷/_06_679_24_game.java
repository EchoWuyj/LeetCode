package alg_02_train_dm._23_day_回溯算法二_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 19:03
 * @Version 1.0
 */
public class _06_679_24_game {
     /*
        679. 24 点游戏
        你有 4 张写有 1 到 9 数字的牌。
        你需要判断是否能通过  * /，+，-， 括号 ()  的运算得到 24。

        示例 1:
        输入: [4, 1, 8, 7]
        输出: True
        解释: (8-4) * (7-1) = 24

        示例 2:
        输入: [1, 2, 1, 2]
        输出: False

        注意:
        1. 除法运算符 / 表示 实数除法，而不是 整数除法。例如 4 / (1 - 2/3) = 12 。
        2. 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。
           例如，[1, 1, 1, 1]  作为输入时，表达式  -1 - 1 - 1 - 1  是不允许的。
        3. 你不能将数字连接在一起。例如，输入为  [1, 2, 1, 2]  时，不能写成 12 + 12 。

        提示:
        cards.length == 4
        1 <= cards[i] <= 9

     */

    // 回溯关键=> 抽象树形结构 => 列举所有可能
    // [4,1,8,7] 4 个数字
    // => 4 分别和 1,8,7 进行加减乘除 4 种运算，作为树的第一层
    // => 集合变成 3 个数字，重复上面操作，作为树的第二层
    // => ...
    // => 最终在叶子节点中，找是否有值为 24 即可

    final int TARGET = 24;
    // 实数相除存在误差，1e-6 表示 1 乘以 10 的负 6 次方
    // double 数据类型，不是 int
    final double EPSILON = 1e-6;
    // ctrl + shift + u 大小写转换
    // 将加乘，放在前面，具有交换律运算，方便后续进行剪枝
    final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;

    public boolean judgePoint24(int[] nums) {
        // 实数进行加减乘除，需要将 int 转 double
        List<Double> list = new ArrayList<Double>();
        for (int num : nums) {
            // 强制类型转换，可以使用 double 来转换
            // 需要在外面加 (double)，不能直接 add(num)
            // 只能使用，基本数据类型 double，而不能使用 Double
            list.add((double) num);
        }
        return dfs(list);
    }

    // 树形结构中，每个节点存储每次数字计算后结果列表
    // dfs 目的找到是否有数值等于 24 叶子节点，返回值设置为 boolean
    private boolean dfs(List<Double> list) {
        if (list.size() == 0) return false;
        // list 只剩一个元素，对其判断
        if (list.size() == 1) {
            // 实数运算存在误差(相除存在小数)，若在误差范围内，则返回 true
            // 加上 Math.abs，表示在正负误差之内，都是可以接受的
            // 在 TARGET = 24 上下误差范围之内，都是可以接受的
            return Math.abs(list.get(0) - TARGET) < EPSILON;
        }

        int size = list.size();

        // num1,num2 表示两个数字
        // 注意：涉及运算 num1，num2 和 num2，num1，顺序不同，加减乘除的结果是不同的
        // 因此，for 循环中 num1 和 num2 是无序的，只要保证 num1 != num2 即可

        // 双层 for 循环，一共的结果
        // [0,1]，[0,2]，[0,3]
        // [1,0]，[1,2]，[1,3]
        // [2,0]，[2,1]，[2,3]
        // [3,0]，[3,1]，[3,2]

        for (int num1 = 0; num1 < size; num1++) {
            for (int num2 = 0; num2 < size; num2++) {
                // num1，num2 不能是同一个位置索引，即同一个数字不能使用两次
                if (num1 != num2) {
                    // 集合中存储 Double，而不是 double
                    List<Double> subList = new ArrayList<Double>();
                    // 子集合 subList 先存储除 num1，num2 之外的数字
                    // num1 和 num2 运算之后，再去将其加入 subList 中
                    for (int k = 0; k < size; k++) {
                        if (k != num1 && k != num2) {
                            subList.add(list.get(k));
                        }
                    }

                    // 加减乘除，故循环 4 次
                    // 0,1,2,3 分别对应：加减乘除
                    for (int k = 0; k < 4; k++) {
                        // +，* 满足交换律，0 + 1 = 1 + 0，=> k < 2，
                        // num1 > num2 时，必然有 num1 < num2 已经计算过了 +，*
                        // 相当于'剪枝'
                        if (k < 2 && num1 > num2) continue;

                        if (k == ADD) {
                            subList.add(list.get(num1) + list.get(num2));
                        } else if (k == MULTIPLY) {
                            subList.add(list.get(num1) * list.get(num2));
                        } else if (k == SUBTRACT) {
                            subList.add(list.get(num1) - list.get(num2));
                        } else if (k == DIVIDE) {
                            // 被除数 ÷ 除数 = 商 => '在前被除' => 记忆方式：汉字长度：3，2，1
                            // 除法需要判断 '除数' 是否为 0，即对误差有判断
                            // 若 num2 对应绝对值小于误差，将其看做 0，跳过
                            if (Math.abs(list.get(num2)) < EPSILON) {
                                continue;
                            } else {
                                subList.add(list.get(num1) / list.get(num2));
                            }
                        }

                        // if else 一个分支执行完，对应子集合 subList 进行 dfs 遍历
                        // 在 for 循环里面，而不是等到整个 for 循环结束之后，再去 dfs
                        if (dfs(subList)) return true;
                        // '还原现场' => 删除最后添加元素
                        subList.remove(subList.size() - 1);
                    }
                }
            }
        }
        // 所有的 for 循环执行结束，都没有找到 24，则返回 false
        return false;
    }
}
