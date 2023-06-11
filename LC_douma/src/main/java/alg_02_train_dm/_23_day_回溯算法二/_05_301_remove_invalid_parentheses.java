package alg_02_train_dm._23_day_回溯算法二;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 14:18
 * @Version 1.0
 */
public class _05_301_remove_invalid_parentheses {

     /* 301. 删除无效的括号
        给你一个由若干括号和字母组成的字符串 s，删除最小数量的无效括号，使得输入的字符串有效。
        (括号是匹配，则字符串有效) 返回所有可能的结果。答案可以按 任意顺序 返回。

        示例 1：
        输入：s = "()())()"
        输出：["()()()", "(())()"]

        示例 2：
        输入：s = "(a)())()"
        输出：["(a)()()", "(a())()"] => 字母不影响，只要左右括号是匹配的

        示例 3：
        输入：s = ")("
        输出：[""]

        提示：
        1 <= s.length <= 25
        s 由小写英文字母以及括号 '(' 和 ')' 组成
        s 中至多含 20 个括号


     */

    // KeyPoint 方法一 BFS
    // 最朴素解法，一个一个尝试，每删除一个括号，就去判断剩余的部分是否是有效的括号
    // 逐层遍历，每层相对于上层减少了一个括号，一旦某层发现存在有效括号，该括号就是想要的结果
    // 通过 BFS 可以找到最少删除的括号数(最小路径)
    public List<String> removeInvalidParentheses1(String s) {
        List<String> res = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        // 上一层删除一个字符后，下面一层可能存在相同的元素，而相同的元素没有必要处理，故使用 set 去重
        // 即 visited 存储已经访问过的字符串
        Set<String> visited = new HashSet<>();
        visited.add(s);
        // 标记是否找到
        boolean found = false;
        // 标记是否去下一层
        boolean nextLevel = true;
        while (!queue.isEmpty()) {
            // 每层元素个数，同时最优解一定在同一层
            int size = queue.size();
            // 对每层所有元素进行逐一判断
            for (int i = 0; i < size; i++) {
                String currStr = queue.remove();
                if (isValid(currStr)) {
                    res.add(currStr);
                    found = true;
                    nextLevel = false;
                    // 在该层找到了，将该元素加入 res 之后，直接跳过，判断下一个元素
                    // 本题找到的是所有可能的结果，所以即使在某层到一个元素，剩余元素依旧需要判断
                    continue;
                }

                // 如果当层没有找到有效括号对，需要将其子节点加入队列中
                // 而一旦当层找到一个有效字符串，后续代码不需要执行了
                if (nextLevel) {
                    // 子节点:对 currStr，从第一个字符到最后一个字符，挨个位置删除一个字符的结果
                    int currStrLen = currStr.length();
                    for (int j = 0; j < currStrLen; j++) {
                        // 字母字符，跳过，注意 && 条件，两个都不能是，不能使用或，
                        if (currStr.charAt(j) != '(' && currStr.charAt(j) != ')') continue;
                        String leftStr = currStr.substring(0, j);
                        // 避免 j 到 currStrLen - 1 位置，j + 1 则是会越界
                        String rightStr = (j == currStrLen - 1) ?
                                "" : currStr.substring(j + 1, currStrLen);
                        // 删除 j 位置的字符
                        String next = leftStr + rightStr;
                        // 上一层删除一个字符后，下面一层可能存在相同的元素，而相同的元素没有必要处理
                        // 只有 set 中不存在，再将其放进入队列
                        if (!visited.contains(next)) {
                            queue.add(next);
                            visited.add(next);
                        }
                    }
                }
            }
            // 在 for 循环结束之后，即遍历完该层所有元素
            // 若找到，则 break，已经是删除最小数量的无效括号，故不需要对队列剩余元素再去判断
            if (found) break;
        }
        return res;
    }

    // 判断括号对是否有效，针对只含有'('和')'的情况
    private boolean isValid(String s) {
        // 记录左括号'('数量
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            // 遍历过程，一旦 count < 0，左括号个数 < 右括号个数，括号对无效
            if (count < 0) return false;
        }

        // 只有最后 count == 0，才说明左括号数量和右括号数量一致
        // 若 count > 0，左括号个数 < 右括号个数，括号对无效，故不是直接返回 true
        return count == 0;
    }

    // 成员变量形式定义 s 和 res
    private String s;
    // 答案中存在相同结果，为了去重使用 set
    private Set<String> res;

    // KeyPoint 方法二 回溯 (DFS)
    // DFS 比 BFS 要好点，DFS 没有挨个判断每个字符串是否有效，而是一个字符一个字符去判断
    public List<String> removeInvalidParentheses(String s) {
        this.s = s;
        this.res = new HashSet<>();

        // 若当前遍历到左括号个数'严格小于'右括号个数，则表达式无效
        // => 右括号在左括号之前已经出现，表达式无效
        // => 基于以上结论，事先计算出来，一个表达式至少需要删除左，右括号个数，使得表达式有效

        // 第 1 步：遍历一次，计算多余的左右括号
        // 至少需要删除左，右括号个数
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

        // 第 2 步：回溯算法，尝试每一种可能的删除操作
        // 从前往后遍历每个字符，每个字符都有删除和不删 2 种可能(本质:删与不删，类似二叉树)
        // 删除 '('，则 leftRemove--，当 leftRemove == 0，就不能再删除 '('
        // 删除' )'，则 rightRemove--，当 leftRemove == 0，就不能再删除' )'
        dfs(0, leftRemove, rightRemove, 0, 0, new StringBuilder());
        // 修改数据类型，将 HashSet => ArrayList，满足返回值要求
        // 集合中数据值没有变化，只是数据类型改变了
        return new ArrayList<>(res);
    }

    // 递归过程，不断变化参数，通过形参进行传递
    // index 记录字符在字符串中索引
    // leftCount 路径中保留左括号个数
    private void dfs(int index,
                     int leftRemove, int rightRemove,
                     int leftCount, int rightCount,
                     StringBuilder path) {
        if (index == s.length()) {
            // 找到有效字符串
            if (leftRemove == 0 && rightRemove == 0) {
                // res 是 set，保证去重
                res.add(path.toString());
            }
            return;
        }

        // 先获取当前的字符
        char c = s.charAt(index);

        // KeyPoint 本质:通过 if 判断，控制是否进行后续 dfs (遍历下个子节点) => 剪枝
        // KeyPoint 可能的操作 1：删除当前遍历到的字符 => 删
        // 具体删除 '(' 或者 ')' 分情况讨论
        if (c == '(' && leftRemove > 0) {
            dfs(index + 1, leftRemove - 1, rightRemove, leftCount, rightCount, path);
        } else if (c == ')' && rightRemove > 0) {
            dfs(index + 1, leftRemove, rightRemove - 1, leftCount, rightCount, path);
        }

        // 上面两个 if 都不满足，即不满足删除条件，故不删除该字符，并将其加入到 path中
        // 删除当前的字符不存在，相当于没有左子树，不需要进行后续 dfs
        path.append(c);

        // KeyPoint 可能的操作 2：保留当前遍历到的字符 => 不删(保留)
        // 字母字符，'(' 直接保留，加入 path
        // 递归调用需要判断'左括号个数'和'右括号个数'，'右括号个数' > '左括号个数' ，直接回溯
        if (c != '(' && c != ')') {
            // 字母字符
            dfs(index + 1, leftRemove, rightRemove, leftCount, rightCount, path);
        } else if (c == '(') {
            dfs(index + 1, leftRemove, rightRemove, leftCount + 1, rightCount, path);
            // c == ')' 两种情况
            // 1.rightCount < leftCount，继续执行 dfs 遍历后续节点
        } else if (rightCount < leftCount) {
            dfs(index + 1, leftRemove, rightRemove, leftCount, rightCount + 1, path);
        } // 2.rightCount >= leftCount 不用执行 dfs 遍历后续节点，直接回溯

        // 回溯，删除 path 最后一个字符
        path.deleteCharAt(path.length() - 1);
    }
}
