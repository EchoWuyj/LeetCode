package algorithm._02_binary_search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author Wuyj
 * @DateTime 2022-03-02 13:49
 * @Version 1.0
 */
public class LeetCode_287_FindDuplicatedNumber {
    // 方法一：使用HashMap保存每个数出现的次数
    public int findDuplicate01(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        // 遍历所有元素,统计count值
        // Integer和map中的key数据类型保持一致
        for (int num : nums) {
            if (countMap.containsKey(num)) {
                // 如果出现过,num就是重复数
                return num;
            } else {
                countMap.put(num, 1);
            }
        }
        // 没有重复数的情况
        return -1;
    }

    // 方法二：使用HashSet保存数据,判断是否出现过
    // HashSet的去重性,所有的重复元素只是存一份,不会存储重复元素,若元素出现过,直接return即可
    public int findDuplicate02(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        // 遍历所有元素,将其加入到set中
        for (int num : nums) {
            // 判断当前num是否在map中出现过
            if (set.contains(num)) {
                // 如果出现过,num就是重复数
                return num;
            } else {
                set.add(num);
            }
        }
        return -1;
    }

    // 方法三：先排序,然后找相邻的相同元素
    public int findDuplicate03(int[] nums) {
        // 想要减少额外的存储空间,就得使得原来的数组变得有规律,所以需要对其进行排序
        Arrays.sort(nums);
        // 遍历数组元素,遇到跟前一个相同的,就是重复元素
        for (int i = 1; i < nums.length; i++) {
            // 若是使用nums[i]==nums[i+1]判断可能出现索引越界的异常
            // 最好使用这种方式 nums[i] == nums[i - 1])
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    // 方法四：二分查找只是为了判断1~N中所有的数中那个是target
    // 原始数组只是为了计算count值,二分查找没有用在原始的数组上
    public int findDuplicate04(int[] nums) {
        // 定义左右指针
        // 这里的左右指针,指向的不是数组索引,而是1-n个数
        // 通过左右指针的移动,从而找到target值
        int left = 1;
        int right = nums.length - 1;

        // KeyPoint 二分查找是在最外侧的,内层的for循环不是在外面的
        // 从假象的原始的[1,N]自然数序列开始使用二分查找
        while (left <= right) {
            // 计算中间值,这里的中间值就是数组中元素的中间位置的值
            // 但是mid不是target
            int mid = (left + right) / 2;

            // 对于当前的mid值计算count值
            int count = 0;
            // 遍历数组
            for (int i = 0; i < nums.length; i++) {
                // 当时题目中定义为i,现在为mid
                if (nums[i] <= mid) {
                    count++;
                }
            }

            // 判断count和mid本身的大小关系
            if (count <= mid) {
                // count小于等于mid自身,说明mid比target小,左指针右移
                left = mid + 1;
            } else {
                // target可能为mid,不能直接为mid-1;
                right = mid;
            }

            // 左右指针重合时，找到target
            if (left == right) {
                return left;
            }
        }
        return -1;
    }

    // 方法五：快慢指针
    public int findDuplicate05(int[] nums) {
        // 定义快慢指针
        int fast = 0, slow = 0;
        // 1.寻找环内的相遇点

        // 快指针一次走两步，慢指针一次走一步
        // 因为一开始fast=slow相等,所以使用do...while...
        do {
            // 快指针一次走两步，慢指针一次走一步
            // 将数组的value值当做慢指针
            // point = nums[point]等同于 next = next->next;
            slow = nums[slow];
            // 将下一个value值当做快指针
            fast = nums[nums[fast]];
        } while (fast != slow);

        // 循环结束，slow和fast相等，都是相遇点

        // 2. 寻找环的入口点
        // 另外定义两个指针，固定间距
        int before = 0;
        int after = slow;
        while (before != after) {
            // 通过数组的value值模拟成索引
            before = nums[before];
            after = nums[after];
        }
        // 循环结束，相遇点就是环的入口点，也就是重复元素
        return before;
    }

    public static void main(String[] args) {
        int[] num01 = {1, 3, 4, 2, 2};
        int[] num02 = {1, 1};
        // int[] num03 = {1, 2, 3, 4, 5};
        LeetCode_287_FindDuplicatedNumber findDuplicatedNumber = new LeetCode_287_FindDuplicatedNumber();
        System.out.println(findDuplicatedNumber.findDuplicate03(num01));
    }
}
