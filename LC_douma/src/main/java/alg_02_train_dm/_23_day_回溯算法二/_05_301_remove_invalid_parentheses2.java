package alg_02_train_dm._23_day_回溯算法二;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-06-26 21:30
 * @Version 1.0
 */
public class _05_301_remove_invalid_parentheses2 {

    // 思路：
    // 若当前遍历到 左括号个数 '严格小于' 右括号个数，则表达式无效，如：(()))
    // => 一个左括号，需要一个右括号，与之对应
    // => 即右括号在左括号之前已经出现，表达式无效
    // => 基于以上结论，事先计算出来，一个表达式至少需要删除左，右括号个数，使得表达式有效

    // KeyPoint 方法二 回溯 (DFS)
    // DFS 比 BFS 要好点，DFS 没有挨个判断每个字符串是否有效，而是一个字符一个字符去判断
    // DFS 执行用时：118 ms, 在所有 Java 提交中击败了 19.98% 的用户
    // BFS 执行用时：50 ms, 在所有 Java 提交中击败了 37.86% 的用户
    public List<String> removeInvalidParentheses(String s) {

        // 答案中存在相同结果，为了去重使用 set
        Set<String> res = new HashSet<>();

        // 第 1 步：遍历一次，计算多余的 左括号 或 右括号
        int[] removeArr = countRemoveNums(s);
        int leftRemove = removeArr[0];
        int rightRemove = removeArr[1];

        // 第 2 步：回溯算法，尝试每一种可能的删除操作
        // 从前往后遍历每个字符，每个字符都有 2 种操作，删 和 不删 => 类似二叉树
        dfs(s, 0, leftRemove, rightRemove, 0, 0, new StringBuilder(), res);

        // 修改数据类型
        // => 将 HashSet 转 ArrayList，满足返回值要求
        // => 集合中数据值没有变化，只是数据类型改变了
        return new ArrayList<>(res);
    }

    // 至少需要删除 左括号 和 右括号 个数
    private int[] countRemoveNums(String s) {
        // s = "(a)())()(" => leftRemove = 1，rightRemove = 1
        int[] res = new int[2];
        int leftRemove = 0;
        int rightRemove = 0;
        // 遍历一遍表达式
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftRemove++;
            } else if (s.charAt(i) == ')') {
                // 遇到右括号的时候，须要根据已经存在的左括号数量决定
                if (leftRemove == 0) {
                    rightRemove++;
                }
                if (leftRemove > 0) {
                    // 关键：一个右括号出现可以抵销之前遇到的左括号
                    leftRemove--;
                }
            }
        }
        res[0] = leftRemove;
        res[1] = rightRemove;
        return res;
    }

    // 递归过程，不断变化参数，通过形参进行传递
    // index 记录字符在字符串中索引
    // leftCount 路径中保留左括号个数
    // path 路径中添加字符
    // KeyPoint 定义变量，最好不要名字差不多，idea 自动提示容易混淆，rightCount 和 rightRemove
    //          自己输入时，遇到这种情况，也需要更加小心
    private void dfs(String s, int index,
                     int leftRemove, int rightRemove,
                     int leftCount, int rightCount,
                     StringBuilder path, Set<String> res) {

        // 递归边界
        if (index == s.length()) {
            // debug，发现没有输入值，则在其周边相关代码检查，看看是否有代码误写
            if (leftRemove == 0 && rightRemove == 0) {
                // 找到有效字符串，res 是 set，保证去重
                res.add(path.toString());
            }
            // KeyPoint 注意：return 位置
            // 不能在 if 判断里面，否则 if 条件不成立，没有 return 返回上一层，则 s.charAt(index) 索引越界
            // 不管是什么返回值，return 一定不能丢，void 返回值，在递归调用中，也是必须得有的
            // => 在递归中，无论其返回类型是 void 还是其他类型，递归边界都需要有 return 语句来结束递归的执行
            return;
        }



        // 整体逻辑
        // 1.先获取当前字符 cur
        // 2.dfs 处理左子树 => if 判断，剪枝
        // 3.path 加入 cur
        // 4.dfs 处理右子树 => if 判断，剪枝
        // 5.回溯，移除 cur

        // 本质：通过 if 判断，控制 dfs 是否遍历后续节点 => 剪枝

        // 先获取当前的字符
        char cur = s.charAt(index);

        // KeyPoint 1.删除 cur (左子树) => cur 分左右括号讨论
        // 1.1 左括号
        // 删除 '('，则 leftRemove--，当 leftRemove == 0，就不能再删除 '('
        if (cur == '(' && leftRemove > 0) {
            dfs(s, index + 1, leftRemove - 1, rightRemove, leftCount, rightCount, path, res);
            // 1.2 右括号
            // 删除' )'，则 rightRemove--，当 leftRemove == 0，就不能再删除' )'
            // rightRemove 和 rightCount 相混淆了，导致代码出现异常的 bug
        } else if (cur == ')' && rightRemove > 0) {
            dfs(s, index + 1, leftRemove, rightRemove - 1, leftCount, rightCount, path, res);
        }

        // 若上面两个 if 都不满足，即不满足删除条件，故不删除该字符，将其加入到 path 中
        path.append(cur);

        // KeyPoint 2.不删 cur (右子树)
        // 字母字符，'(' 直接保留，加入 path
        // 递归调用需要 判断'左括号个数'和'右括号个数'，'右括号个数' > '左括号个数' ，直接回溯

        // 2.1 字母字符，
        if (cur != '(' && cur != ')') {
            dfs(s, index + 1, leftRemove, rightRemove, leftCount, rightCount, path, res);
            // 2.2 左括号，不删
        } else if (cur == '(') {
            dfs(s, index + 1, leftRemove, rightRemove, leftCount + 1, rightCount, path, res);
            // 2.3 左括号，不删 => 两种情况
            // 1) rightCount < leftCount，继续执行 dfs 遍历后续节点
        } else if (rightCount < leftCount) {
            dfs(s, index + 1, leftRemove, rightRemove, leftCount, rightCount + 1, path, res);
        }   // 2) rightCount >= leftCount 不用执行 dfs 遍历后续节点，直接回溯

        // 回溯，删除 path 最后一个字符
        // path 是 StringBuilder，不是集合，使用 deleteCharAt
        path.deleteCharAt(path.length() - 1);
    }
}
