package alg_02_train_dm._13_day_综合应用一_二刷;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-07-31 14:49
 * @Version 1.0
 */
public class _03_170_TwoSum1 {
       /*

        170. 两数之和 III - 数据结构设计

        设计一个接收整数流的数据结构，该数据结构支持检查是否存在两数之和等于特定值。

        实现 TwoSum 类：
        TwoSum() 使用空数组初始化 TwoSum 对象
        void add(int number) 向数据结构添加一个数 number
        boolean find(int value) 寻找数据结构中是否存在一对整数，使得两数之和与给定的值相等。
        如果存在，返回 true ；否则，返回 false 。

        输入：
        [“TwoSum”, “add”, “add”, “add”, “find”, “find”]
        [[], [1], [3], [5], [4], [7]]
        输出：
        [null, null, null, null, true, false]

        解释：
        TwoSum twoSum = new TwoSum();
        twoSum.add(1); // [] --> [1]
        twoSum.add(3); // [1] --> [1,3]
        twoSum.add(5); // [1,3] --> [1,3,5]
        twoSum.find(4); // 1 + 3 = 4，返回 true
        twoSum.find(7); // 没有两个整数加起来等于 7 ，返回 false


        提示：
        -105 <= number <= 105
        -231 <= value <= 231 - 1
        最多调用 10^4 次 add 和 find

     */

    // KeyPoint 方法一 对撞指针
    static class TwoSum {
        // 数据存储：
        // 1.静态数组
        // 2.集合：动态数组，链表，Map，Set，Tree
        // 核心：根据相应的场景，选择对应的数据结构
        private List<Integer> list;
        private boolean isSorted;

        public TwoSum() {
            // 构造方法中初始化
            list = new ArrayList<>();
            isSorted = false;
        }

        // 添加一个元素 -> O(1)
        public void add(int num) {
            list.add(num);
            // 每次 add 一个 num 之后都需要将 isSorted 设置为 false
            // 下次 find 操作之前，需要先 sort 一下，再去 find
            isSorted = false;
        }

        // 查找是否存在两个数，这两个数的和等于 value
        // O(nlogn)
        public boolean find(int value) {
            // 在使用对撞指针之前，需要对集合进行排序
            // 但是只有在添加元素之后，才需要排序，故需要一个 flag 标记是否有序
            if (!isSorted) {
                // O(nlogn)
                Collections.sort(list);
                isSorted = true;
            }
            int size = list.size();

            // 对撞指针
            int left = 0;
            int right = size- 1;
            while (left < right) { // O(n)
                int sum = list.get(left) + list.get(right);
                if (sum == value) {
                    return true;
                } else if (sum < value) {
                    left++;
                } else {
                    right--;
                }
            }
            return false;
        }
    }

    // for test
    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        twoSum.add(1);
        twoSum.add(3);
        twoSum.add(5);
        System.out.println(twoSum.find(4)); // true
        System.out.println(twoSum.find(7)); // false
    }
}
