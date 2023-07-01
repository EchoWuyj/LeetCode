package alg_02_train_dm._23_day_回溯算法二_2刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 14:16
 * @Version 1.0
 */
public class _03_842_split_array_into_fibonacci_sequence1 {
    /*
       842. 将数组拆分成斐波那契序列
       给定一个数字字符串 S，比如 S = "123456579"，
       我们可以将它分成斐波那契式的序列 [123, 456, 579]。

       形式上，斐波那契式序列是一个非负整数列表 F，且满足：
       0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
       F.length >= 3；

       对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
       另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
       "0123" ->
        => "01", "23"  错误拆分
        => "0", "123"  正确拆分

       返回从 S 拆分出来的 任意一组 斐波那契式的序列块，如果不能拆分则返回 []。

       示例 1：
       输入："12345657"
       输出：[123,456,579]

       示例 2：
       输入: "11235813"
       输出: [1,1,2,3,5,8,13]

       示例 3：
       输入: "112358130"
       输出: []
       解释: 这项任务无法完成。

       示例 4：
       输入："0123"
       输出：[]
       解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。

       示例 5：
       输入: "1101111"
       输出: [110, 1, 111]
       解释: 输出 [11,0,11,11] 也同样被接受。

       提示：
       1 <= S.length  <= 200
       字符串 S 中只含有数字。

       KeyPoint 注意事项
       1.S.length 最大为 200 => 一般 dfs 必然超时，需要剪枝操作
       2.本质:求子集 => 将数字字符串切分，不同长度的子串
                        在抽象的树形结构中，每条完整的路径都是一种切割方案
                        再判读是否符合斐波那契式序列的特征
    */

    // 自己解法，dfs 超时
    // 若 dfs 出现超出时间限制 => 需要剪枝
    public List<Integer> splitIntoFibonacci(String num) {
        List<Integer> res = new ArrayList<>();
        if (num == null || num.length() == 0) return res;
        dfs(num, 0, new ArrayList<>(), res);
        return res;
    }

    public void dfs(String num, int index, List<Integer> path, List<Integer> res) {
        if (index == num.length()) {
            if (path.size() <= 2) return;
            for (int i = 2; i < path.size(); i++) {

                // 判断是否满足：斐波那契序列
                if (path.get(i) != path.get(i - 1) + path.get(i - 2)) return;

                // if 条件不满足，不能返回 break，而是应该返回 return
                // 否则，只是结束 for 循环，后续 res.addAll(path) 代码还是会执行的
                // 没有起到最初的目的

            }
            // res 为空才添加，否则不添加，保证只有一组解
            if (res.isEmpty()) {
                // 集合添加集合
                res.addAll(path);
            }
            return;

            // 通过测试用例，debug 代码 ，不要使用太复杂的数据
            // 针对输出数据，再去对代码进行修改

            // 输入
            // "0123"
            // 输出
            // [0,1,2,3
            //  0,1,23,
            //  0,12,3,
            //  0,123]
            // 预期结果
            // []

            // 输入
            // "1101111"
            // 输出
            // [11,0,11,11,110,1,111]
            // 预期结果
            // [11,0,11,11]
        }

        for (int i = index; i < num.length(); i++) {
            String subStr = num.substring(index, i + 1);
            if (subStr.charAt(0) == '0' && subStr.length() != 1) continue;

            // java.lang.NumberFormatException: For input string: "5511816597"
            // Integer.MAX_VALUE 2147483647
            try {
                path.add(Integer.parseInt(subStr));
            } catch (Exception e) {
                // 不使用 continue，而是 break，直接结束 for 循环
                break;
            }
            dfs(num, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

    // 将返回值类型修改成 boolean 类型，依旧超时
    public boolean dfs1(String num, int index, List<Integer> path, List<Integer> res) {
        if (index == num.length()) {
            if (path.size() <= 2) return false;
            for (int i = 2; i < path.size(); i++) {
                if (path.get(i) != path.get(i - 1) + path.get(i - 2)) return false;
            }
            if (res.isEmpty()) {
                res.addAll(path);
                return true;
            }
        }

        for (int i = index; i < num.length(); i++) {
            String subStr = num.substring(index, i + 1);
            if (subStr.charAt(0) == '0' && subStr.length() != 1) continue;
            // 通过 long 类型，来出来处理 int 越界，而不是通过 try .. catch ..来处理异常
            if (Long.parseLong(subStr) > Integer.MAX_VALUE) break;
            if (dfs1(num, i + 1, path, res)) return true;
            path.remove(path.size() - 1);
        }
        return false;
    }
}
