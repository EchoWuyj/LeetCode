package alg_02_train_dm._20_day_数据结构设计;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-28 14:40
 * @Version 1.0
 */
public class _06_381_insert_delete_getrandom_o1_duplicates_allowed {

     /*
        381. O(1) 时间插入、删除和获取随机元素 - 允许重复
        RandomizedCollection 是一种包含数字集合(可能是重复的)的数据结构。
        它应该支持插入和删除特定元素，以及删除随机元素。

        实现 RandomizedCollection 类:
        RandomizedCollection() 初始化空的 RandomizedCollection 对象。
        bool insert(int val)   将一个 val 项插入到集合中，即使该项已经存在。如果该项不存在，则返回 true ，否则返回 false 。
        bool remove(int val)   如果存在，从集合中移除一个 val 项。如果该项存在，则返回 true ，否则返回 false 。
                               注意，如果 val 在集合中出现多次，我们只删除其中一个。
        int getRandom() 从当前的多个元素集合中返回一个随机元素。每个元素被返回的概率与集合中包含的相同值的数量 线性相关 。

        您必须实现类的函数，使每个函数的 平均 时间复杂度为 O(1) 。
        注意：生成测试用例时，只有在 RandomizedCollection 中 至少有一项 时，才会调用 getRandom 。

        示例 1:
        输入
        ["RandomizedCollection", "insert", "insert", "insert", "getRandom", "remove", "getRandom"]
        [[], [1], [1], [2], [], [1], []]
        输出
        [null, true, false, true, 2, true, 1]

        解释
        RandomizedCollection collection = new RandomizedCollection();// 初始化一个空的集合。
        collection.insert(1);   // 返回 true，因为集合不包含 1。
                                // 将 1 插入到集合中。
        collection.insert(1);   // 返回 false，因为集合包含 1。
                                // 将另一个 1 插入到集合中。集合现在包含 [1,1]。
        collection.insert(2);   // 返回 true，因为集合不包含 2。
                                // 将 2 插入到集合中。集合现在包含 [1,1,2]。
        collection.getRandom(); // getRandom 应当:
                                // 有 2/3 的概率返回 1,
                                // 1/3 的概率返回 2。
        collection.remove(1);   // 返回 true，因为集合包含 1。
                                // 从集合中移除 1。集合现在包含 [1,2]。
        collection.getRandom(); // getRandom 应该返回 1 或 2，两者的可能性相同。

        提示:
        -2^31 <= val <= 2^31 - 1
        insert, remove 和 getRandom 最多 总共 被调用 2 * 10^5 次
        当调用 getRandom 时，数据结构中 至少有一个 元素
     */

    class RandomizedCollection {
        // 相同元素可能存在多个位置，即元素存在多个索引，需要使用集合来装
        // 这里使用 Set 存储，方便 remove 某个指定的 index 操作，同时保证时间复杂度 O(1)
        Map<Integer, Set<Integer>> map;
        List<Integer> nums;
        Random rand = new Random();

        public RandomizedCollection() {
            this.nums = new ArrayList<>();
            this.map = new HashMap<>();
        }

        // 支持重复元素插入
        // 将一个 val 项插入到集合中，即使该项已经存在。如果该项不存在，则返回 true ，否则返回 false
        public boolean insert(int val) {
            Set<Integer> set = map.getOrDefault(val, new HashSet<>());
            // 此时 val 对应的 index = nums.size()，不是 index = nums.size()-1
            // 将 index 存入 set 中
            set.add(nums.size());
            // map 维护映射
            map.put(val, set);
            // 数据维护元素
            nums.add(val);
            // 若第一次插入，返回 true
            return set.size() == 1;
        }

        // 如果存在，从集合中移除一个 val 项。如果该项存在，则返回 true ，否则返回 false
        // 注意，如果 val 在集合中出现多次，我们只删除其中一个。
        public boolean remove(int val) {
            // 1.val 不存，返回 false
            if (!map.containsKey(val)) return false;

            // 2.使用 lastNum 对 val 进行覆盖
            // 获取待删除元素在 set 中索引位置，获取第一索引
            Iterator<Integer> iterator = map.get(val).iterator();
            // 获取 Iterator 中第一元素
            int index = iterator.next(); // Returns the next element in the iteration

            // 获取 lastNum
            int lastNum = nums.get(nums.size() - 1);
            // 将列表中的 lastNum 覆盖掉需要删除的元素，通过 index 获取其位置，在使用 lastNum 对其覆盖
            // 注意，不是直接 set(val,lastNum)，ArrayList 赋值是通过'索引'
            nums.set(index, lastNum);

            // 3.更新 val 和 lastNum 索引
            // 删除 val 对应的索引，set remove 操作，时间复杂度 O(1)
            map.get(val).remove(index);

            // set 删除最后一个元素索引
            map.get(lastNum).remove(nums.size() - 1);

            // 若 index 和 nums.size() - 1 相等，则移除的 val 是 lastNum，不需要更新其索引
            // 更新 lastNum 对应的索引，若 lastNum 对应是 nums.size() - 1，则 set 中不用添加
            if (index < nums.size() - 1) {
                map.get(lastNum).add(index);
            }

            // 4.删除 lastNum 元素
            nums.remove(nums.size() - 1);

            // 从 map 中删除指定的元素
            if (map.get(val).size() == 0) map.remove(val);

            return true;
        }

        public int getRandom() {
            // 传递 bound 进来的时候出现了 0
            // 抛出异常 java.lang.IllegalArgumentException: bound must be positive
            return nums.get(rand.nextInt(nums.size()));
        }
    }
}
