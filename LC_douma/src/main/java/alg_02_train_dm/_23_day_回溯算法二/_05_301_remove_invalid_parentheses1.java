package alg_02_train_dm._23_day_回溯算法二;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 14:18
 * @Version 1.0
 */
public class _05_301_remove_invalid_parentheses1 {

     /*
        301. 删除无效的括号
        给你一个由 若干括号 和 字母 组成的字符串 s，删除 最小数量的 无效括号，使得输入的字符串有效。
        (说明：若括号是匹配，则字符串有效) 返回所有可能的结果。答案可以按 任意顺序 返回。

        示例 1：
        输入：s = "()())()"
        输出：["()()()", "(())()"]
        解释：

        ()())()  => ()()()
            ↑
           删除

        ()())()  => (())()
         ↑
        删除

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

    // 思路(最朴素解法)
    // 1.从左往右，一个一个尝试，每删除一个括号，就去判断剩余的部分是否是有效的括号
    // 2.逐层遍历，每层相对于上层减少了一个括号 => 从而抽象成树形结构
    // 3.一旦某层发现存在有效括号，该括号就是想要的结果，此时为删除最小数量的无效括号
    // BFS：每层地去遍历，每层相对上层减少一个括号，一旦某层发现存在有效括号，即为最终的结果
    // => 通过 BFS 可以找到最少删除的括号数(最小路径)

    // KeyPoint 方法一 BFS
    public List<String> removeInvalidParentheses1(String s) {
        List<String> res = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(s);

        // 上一层删除一个字符后，下面一层可能存在相同的元素，
        // 而相同的元素没有必要处理，故使用 set 去重，即 set 存储已经访问过的字符串
        Set<String> set = new HashSet<>();
        set.add(s);

        // found 标记是否找到
        boolean found = false;

        // nextLevel 标记是否去下一层
        boolean nextLevel = true;

        while (!queue.isEmpty()) {
            // 每层元素个数，同时最优解一定在同一层
            int size = queue.size();
            // 对每层所有元素进行逐一判断
            for (int i = 0; i < size; i++) {
                String currStr = queue.poll();
                // 1.找到了
                if (isValid(currStr)) {
                    // 加入 res 中
                    res.add(currStr);
                    found = true;
                    nextLevel = false;
                    // 1.isValid 验证通过，则说明在该层找到了，将该元素加入 res 之后
                    //   continue 跳过后续代码，判断下一个元素
                    // 2.注意：本题找到的是所有可能的结果，所以即使在某层到一个元素，剩余元素依旧需要判断
                    continue;
                }

                // 2.没有找到
                // 若当层没有找到有效括号对，则需要将其子节点加入队列中
                if (nextLevel) {
                    // 子节点：对 currStr，从第一个字符到最后一个字符，挨个位置删除一个字符的结果
                    int currStrLen = currStr.length();
                    for (int j = 0; j < currStrLen; j++) {
                        // 字母字符，跳过
                        // 注意：连接条件 && ，两个都不能是，不能使用 ||
                        if (currStr.charAt(j) != '(' && currStr.charAt(j) != ')') continue;

                        // [0,j-1]
                        String leftStr = currStr.substring(0, j);

                        // [j-1,currStrLen-1]
                        // 避免 j 到 currStrLen - 1 位置，j + 1 则是会越界
                        String rightStr = (j == currStrLen - 1) ?
                                "" : currStr.substring(j + 1, currStrLen);

                        // 删除 j 位置的字符
                        String subStr = leftStr + rightStr;

                        // 上一层删除一个字符后，下面一层可能存在相同的元素
                        // 而相同的元素没有必要处理，只有 set 中不存在，再将其放进入队列
                        if (!set.contains(subStr)) {
                            queue.add(subStr);
                            set.add(subStr);
                        }
                    }
                }
            }

            // 在 for 循环结束之后，即遍历完该层所有元素，若找到，则 break
            // 此时已经是删除最小数量的无效括号，故不需要对队列剩余元素再去判断
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

    public boolean isValid1(String s) {
        // 通过 leftBraceCnt 记录括号个数
        // 1.遇到 '('，leftBraceCnt++
        // 2.遇到 ')'，leftBraceCnt--
        int leftBraceCnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftBraceCnt++;
                // 对应修改，因为输入数据中，还有字母字符，如："(a)())()"
            } else if (c == ')') {
                if (leftBraceCnt == 0) return false;
                leftBraceCnt--;
            }
        }
        return leftBraceCnt == 0;
    }
}
