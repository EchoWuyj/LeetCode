package alg_02_train_dm._23_day_回溯算法二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-26 15:47
 * @Version 1.0
 */
public class _03_842_split_array_into_fibonacci_sequence2 {

    // 经验：对于复杂的回溯题目，先将回溯基本框架搭建起来，之后再去完善代码细节
    public List<Integer> splitIntoFibonacci(String num) {
        List<Integer> res = new ArrayList<>();
        dfs(num, 0, 0, 0, res);
        return res;
    }

    // KeyPoint 注意事项
    // 1.本题只要找到一个斐波那契序列切割，即可返回，故将返回值设置成 boolean 类型，一旦找到了就提前退出
    // 2.额外信息，通过形参传入，如：prevTwoNumSum 前面两数和，prevNum 前面一个数
    // 核心优化：递归过程中，传入 prevTwoNumSum 和 prevNum，避免后续再 for 循环判断
    private boolean dfs(String num, int index, int prevTwoNumSum, int prevNum, List<Integer> res) {
        if (index == num.length()) {
            // 长度 >= 3，且存储都是斐波那契式序列，则返回 true
            return res.size() >= 3;
        }

        long curLongNum = 0;
        for (int i = index; i < num.length(); i++) {
            // i = index，则说明：当前字符是 '0'，这种情况可以
            // i > index，则说明：数字以 0 开头，剪枝
            // 注意：charAt()，形参是 index，不是 i，只是判断第一字符，index 表示起始位置
            if (i > index && num.charAt(index) == '0') continue;

            // 优化：避免通过字符串截取方式获取子串，即：num.substring(index,i + 1)
            // 通过数学公式计算得到 curLongNum，性能比字符串截取方式要好
            curLongNum = curLongNum * 10 + (num.charAt(i) - '0');

            // 通过 long 类型，来出来处理 int 越界
            // 当前节点值 > 最大值 => 不要往右以及下走，直接结束循环
            if (curLongNum > Integer.MAX_VALUE) break;
            int curIntNum = (int) curLongNum;

            // 1.判断 curIntNum 当前数 和 prevTwoNumSum 前面两数和，两者关系
            // 2.必须保证 res.size >=2，否则 prevTwoNumSum 初值为 0，curIntNum 直接 > prevTwoNumSum，
            //   则不进行后续循环了，故需要等 res.size 有了 2 个数之后，再去判断
            if (res.size() >= 2) {
                if (curIntNum < prevTwoNumSum) {
                    // 跳过本轮循环，判断后续更大数值的分割子串，两者是否相等
                    continue;
                } else if (curIntNum > prevTwoNumSum) {
                    // 结束循环，因为后续分割子串只会更大，更不会有 curIntNum == prevTwoNumSum
                    break;
                }
            }

            // 经过 if 判断，curIntNum 和 prevTwoNumSum 相等 或者 res.size() < 2，则继续执行后续代码
            res.add(curIntNum);
            if (dfs(num, i + 1, curIntNum + prevNum, curIntNum, res)) return true;
            res.remove(res.size() - 1);
        }

        // 没有找到，返回 false;
        return false;
    }
}
