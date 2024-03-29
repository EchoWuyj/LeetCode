package alg_02_train_dm._12_day_滑动窗口_二刷.train_todo;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 14:20
 * @Version 1.0
 */
public class _10_904_fruit_into_baskets {

     /*
        leetcode 904. 水果成篮
        在一排树中，第 i 棵树产生 tree[i] 型的水果。
        你可以从你选择的任何树开始，然后重复执行以下步骤：

        把这棵树上的水果放进你的篮子里。如果你做不到，就停下来。
        移动到当前树右侧的下一棵树。如果右边没有树，就停下来。
        请注意，在选择一颗树后，你没有任何选择：你必须执行步骤 1，然后执行步骤 2，然后返回步骤 1，然后执行步骤 2，依此类推，直至停止。

        你有两个篮子，每个篮子可以携带任何数量的水果，但你希望每个篮子只携带一种类型的水果。

        用这个程序你能收集的水果树的最大总量是多少？

        示例 1：
        输入：[1,2,1]
        输出：3
        解释：我们可以收集 [1,2,1]。

        示例 2：
        输入：[0,1,2,2]
        输出：3
        解释：我们可以收集 [1,2,2]
        如果我们从第一棵树开始，我们将只能收集到 [0, 1]。

        示例 3：
        输入：[1,2,3,2,2]
        输出：4
        解释：我们可以收集 [2,3,2,2]
        如果我们从第一棵树开始，我们将只能收集到 [1, 2]。

        示例 4：
        输入：[3,3,3,1,2,1,1,2,3,3,4]
        输出：5
        解释：我们可以收集 [1,2,1,1,2]
        如果我们从第一棵树或第八棵树开始，我们将只能收集到 4 棵水果树。

        提示：
        1 <= tree.length <= 40000
        0 <= tree[i] < tree.length
     */

    public int totalFruit(int[] tree) {
        // 收集的水果总量最大值
        int res = 0;

        // 定义统计窗口中每种水果类型对应的水果的个数
        // key 是水果种类
        // value 是收集到的水果数
        Map<Integer, Integer> counter = new HashMap<>();

        // 维护滑动窗口
        int left = 0;
        int right = 0;
        while (right < tree.length) {
            // 统计当前 right 指向水果类型收集到的水果数
            counter.put(tree[right], counter.getOrDefault(tree[right], 0) + 1);

            // left 指针移动
            // 移动时机：当前窗口水果种类超过了 2 种
            // 移动策略：
            //      1. 当前窗口中 left 指向的水果种类对应的水果数减 1
            //      2. 如果这个 left 指向的水果种类对应的水果数为 0，那么从当前窗口中移除掉这个水果种类
            while (counter.size() > 2) {
                counter.put(tree[left], counter.get(tree[left]) - 1);
                if (counter.get(tree[left]) == 0) counter.remove(tree[left]);
                left++;
            }

            // 到了这里，当前窗口中最多只有两种水果种类
            // 计算收集的水果总量最大值
            res = Math.max(res, right - left + 1);

            // 右指针移动
            right++;
        }
        return res;
    }
}
