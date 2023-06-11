package alg_02_train_dm._20_day_数据结构设计;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-27 18:15
 * @Version 1.0
 */
public class _05_380_insert_delete_getrandom_o1 {
    /*

        380. O(1) 时间插入、删除和获取随机元素

        实现 RandomizedSet 类
        RandomizedSet() 初始化 RandomizedSet 对象
        bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
        bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
        int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。
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

    class RandomizedSet {
        // 用于判重
        // key -> 元素，value -> 索引
        Map<Integer, Integer> idxMap;
        // 随机访问 => 存储数据
        List<Integer> nums;
        Random random = new Random();

        public RandomizedSet() {
            this.nums = new ArrayList<>();
            this.idxMap = new HashMap<>();
        }

        // 当元素 val 不存在时，向集合中插入该项，并返回 true；否则，返回 false 。
        // 平均时间复杂度为 O(1)
        public boolean insert(int val) {
            if (idxMap.containsKey(val)) return false;
            // map 维护映射
            idxMap.put(val, nums.size());
            // 数组维护
            nums.add(val);
            return true;
        }

        // 当元素 val 存在时，从集合中移除该项，并返回 true；否则，返回 false
        // 平均时间复杂度为 O(1)
        public boolean remove(int val) {
            // 元素不存在，返回 false
            if (!idxMap.containsKey(val)) return false;

            // KeyPoint 元素存在，将其删除，还得保证平均时间复杂度为 O(1)
            // 拿到需要删除的元素在列表中的索引位置
            int index = idxMap.get(val);

            // 将列表中的最后一个元素覆盖掉需要删除的元素
            int lastNum = nums.get(nums.size() - 1);
            // 将待删除元素覆盖
            nums.set(index, lastNum);

            // 更新最后一个元素的索引
            idxMap.put(lastNum, index);

            // 删除数组列表中的最后一个元素
            nums.remove(nums.size() - 1);

            // 从 map 中删除指定的元素
            idxMap.remove(val);

            return true;
        }

        public int getRandom() {
            // HashSet / HashMap 只有在指定 key 条件下，能在时间复杂度 O(1) 返回结果，这种情况是不行的
            // 而动态数组通过索引，随机等概率访问数组中任意元素
            return nums.get(random.nextInt(nums.size()));
        }
    }
}
