package alg_02_train_dm._21_day_综合应用二;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 20:37
 * @Version 1.0
 */
public class _11_440_k_th_smallest_in_lexicographical_order {

     /*
        440. 字典序的第 K 小数字
        给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。

        输入:
        n: 13   k: 2
        输出: 10

        解释：
        自然排序：1,2,3,4,5,6,7,8,9,10,11,12,13
        字典序：1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9

        字典序比较
        10 < 2
        12 < 3
        110 < 2
        110 < 201
        110 < 120

        注意：1 ≤ k ≤ n ≤ 10^9。
     */

    // KeyPoint 方法一 排序 => 空间复杂度 O(n) => 超出内存限制
    // 字典序和字符串排序一样 "10" < "2"，将每个数字转成字符串，对字符串进行排序，从而获取第 k 小
    public int findKthNumber1(int n, int k) {
        // 1. 将每个数字转成字符串
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(String.valueOf(i));
        }
        // 2. 对字符串进行排序
        Collections.sort(list);
        // 获取第 k 小的元素
        return Integer.parseInt(list.get(k - 1));
    }

    // KeyPoint 方法二 前缀树
    // 以 1 为前缀，字典序：1 < 10 < 11 < 12，...，100 < 101 < 102 ...
    // 将其抽象成每层都是 10 叉树
    // 同理：以 2，3，...，9 为前缀的 10 叉树
    // 若直接前序遍历，"根左右"，因为数据规模太大，从而导致性能很慢，故需要确定 k 属于那颗子树
    // KeyPoint 总体流程
    // 1.先判断数字 k 属于哪个子树，然后在当前子树往下一层级移动
    // 2.然后重复找子树和往下层移动的过程，直到找到节点（也就是数字）
    public int findKthNumber(int n, int k) {
        // curr 从以 1 为前缀树开始
        int curr = 1;
        //
        k = k - 1;
        // k>0，则没有找到 k 小，
        while (k > 0) {
            // 若 cur = 1，则 next = cur+1 = 2
            // 若 cur = 10，则 next = cur+1 = 11
            int nodes = calNodes(n, curr, curr + 1);
            // 根据 nodes 和 k 大小关系，判断 k 是在当前的前缀树中，还是在下一颗前缀树中
            // 1.不在当前的前缀中 => 到下个前缀树上找
            if (nodes <= k) {
                // 减掉该前缀树上的节点数
                k -= nodes;
                // curr 移动到下颗前缀树上
                curr += 1;
            } else {
                // 2.nodes > k，在当前的前缀树中，移动到下层位置上找
                // 减去上层一个元素，在下层找 k-1 小的数字
                k -= 1;
                // curr 移动到下层
                curr *= 10;
            }
        }
        // 若 k=0，直接返回 curr
        return curr;
    }

    // 计算当前 curr 前缀树中小于 n 的节点数
    // curr => 当前前缀树
    // next => 下个前缀树
    public int calNodes(int n, long curr, long next) {
        // 记录节点个数
        int nodes = 0;
        // 若 curr > n，则存在大于 n 的节点数，故结束循环
        while (curr <= n) {

            // 解释：为什么是 n+1？
            // 若上界 n 为 12，算出以 1 为前缀的子节点数，首先 1 本身是一个节点
            // 接下来要算下面 10，11，12，一共有 4 个子节点。
            // cur = 10，next = n = 12，计算所有节点的个数，两端都是需要包括的
            // 所以 12-10+1 => n+1 - cur

            // next 可能超过 n，导致小于 n 的节点数算多了，所以使用 Math.min 取较小值
            nodes += (Math.min(n + 1, next) - curr);
            // cur 和 next 计算一轮之后，两指针移到下一层
            // cur 和 next 可能会越界，所以使用 long 类型存储
            curr *= 10;
            next *= 10;
        }
        return nodes;
    }
}
