package alg_02_train_dm._20_day_数据结构设计_二刷;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-27 18:15
 * @Version 1.0
 */
public class _05_380_insert_delete_getrandom {
    /*

        380. O(1) 时间 插入、删除 和 获取 随机元素

        实现 RandomizedSet 类
        RandomizedSet() 初始化 RandomizedSet 对象

        bool insert(int val)
        当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
        => 不能存储重复元素

        bool remove(int val)
        当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。

        int getRandom()
        随机返回现有集合中的一项(测试用例保证调用此方法时集合中至少存在一个元素)。
        每个元素应该有 相同的概率 被返回。

        你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。

        示例：
        输入
        ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
        [[], [1], [2], [2], [], [1], [2], []]
        输出
        [null, true, false, true, 2, true, false, 2]

        解释
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
        randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
        randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
        randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
        randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
        randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
        randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。

        提示：
        -2^31 <= val <= 2^31 - 1
        最多调用 insert、remove 和 getRandom 函数 2 * 105 次
        在调用 getRandom 方法时，数据结构中 至少存在一个 元素。

    */

    // KeyPoint 数据结构设计
    // 1.首要问题：使用什么数据结构存储?
    // 2.同时需要满足题目需求：多个操作的平均时间复杂度为 O(1)

    // KeyPoint 解题思路
    // => Map 或者 Set，只有在指定 key 条件下，能在时间复杂度 O(1) 返回结果
    //    本题 getRandom 方法无法实现时间复杂度为 O(1)，因为无法确定 key
    //    只能先遍历 Map 或者 Set 之后，获取所有元素，再去基于所有元素再去随机
    // => 时间复杂度 O(1) => 使用动态数组(因为容量不确定)，通过索引实现随机访问实现
    //    随机等概率访问数组中任意元素

    // KeyPoint注意：insert 方法：当元素 val 不存在时，向集合中插入该项
    // => 不能存储重复元素，故需要使用  Map 用于判重
    // => 不能简单使用 Set 进行判重，还得保留 index，方便在 remove 方法中，根据 index 删除

    // KeyPoint 注意事项
    // 当一个数据结构，满足不了所有需求时，可以选择多个数据结构组合使用
    // => Map 和 ArrayList

    class RandomizedSet {
        // Map 映射关系
        // key -> 元素
        // value -> 数组索引位置
        Map<Integer, Integer> idxMap;

        // 随机访问 => list 实际存储数据
        // 1.list 解决 getRandom 时间复杂度为 O(1)
        // 2.list + idxMap 解决 insert 和 remove 时间复杂度为 O(1)
        List<Integer> list;
        Random random = new Random();

        public RandomizedSet() {
            this.list = new ArrayList<>();
            this.idxMap = new HashMap<>();
        }

        // 函数功能：当元素 val 不存在时，向集合中插入该项，并返回 true；否则，返回 false
        // => 不能存储重复元素，故需要判重，通过 Map 实现
        // 平均时间复杂度为 O(1)
        public boolean insert(int val) {
            if (idxMap.containsKey(val)) return false;
            // 维护 map 映射
            idxMap.put(val, list.size());
            // 维护 num
            list.add(val);
            return true;
        }

        // 函数功能：当元素 val 存在时，从集合中移除该项，并返回 true；否则，返回 false
        // 平均时间复杂度为 O(1)
        public boolean remove(int val) {
            // 元素不存在，没法删除，直接返回 false
            if (!idxMap.containsKey(val)) return false;

            // 元素存在，将其删除，还得保证平均时间复杂度为 O(1)
            // 拿到需要删除的元素在列表中的索引位置
            int index = idxMap.get(val);

            // 获取 lastNum
            int lastNum = list.get(list.size() - 1);
            // 使用 lastNum 覆盖掉待删除元素覆盖
            list.set(index, lastNum);

            // lastNum 位置发生变化
            // 更新 Map (lastNum)：更新 lastNum 的对应位置索引
            idxMap.put(lastNum, index);

            // 更新 nums(lastNum)：删除数组列表中的最后一个元素
            list.remove(list.size() - 1);

            // 更新 map (val)：从 map 中删除指定的元素 val
            idxMap.remove(val);

            return true;
        }

        // O(1)
        public int getRandom() {
            // 使用动态数组(容量不确定)，通过索引实现随机访问实现
            return list.get(random.nextInt(list.size()));
        }
    }
}
