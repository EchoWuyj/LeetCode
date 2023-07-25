package alg_02_train_dm._13_day_综合应用一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-08 18:32
 * @Version 1.0
 */
public class _00_01_Note {
    /*

        算法思维
        1.一下子无法想到最优解 => 直接模拟，即根据题目意思，模拟解题流程，用代码实现
                              => 直接模拟得到的算法 => 往往是暴力解法

        2.暴力解法 -> 时间复杂度很高，原因可能两点
            2.1 选择错了数据结构
                    去重场景 ArrayList O(n) -> HashSet O(1)
                    排序场景 ArrayList O(nlogn) -> Heap O(1ogn)
            2.2 存在大量的重复计算，消除重复计算，降低时间复杂度
                消除重复计算方式
                    1.预计算(预处理)：先一次性将数据预处理(计算，排序)，将预处理的结果暂存
                                     方便后面直接使用，避免后续每个元素都去重复计算
                        1.1 前缀和
                        1.2 排序：对输入数据先排序
                                1) 排序后，使用二分查找 O(logn)
                                2) 排序后，相同的元素会在一起，有的时候可以降低时间复杂度
                                   比如：排序去重复
                                注意：排序使用场景，有些情况不能使用排序
                                      => 元素位置和坐标索引绑定，则不能使用排序，否则排序后索引和排序前索引不一致
                                      => 实在想用，则使用 Map，将 nums 的 value 和 index(原始) 做映射
                        1.3 构建哈希表(映射场景) -> 哈希查找 O(1)
                    2.动态规划：利用上一步结果，推导下一步结果，从而实现复用中间结果，从而消除重复计算

        3.经典查找算法
            3.1 线性查找
            3.2 二分查找
            -------------------------------------------
            空间换时间 => 空间复杂度一般是 O(n)
            3.3 哈希查找
            3.4 堆查找 -> 查找堆顶 O(1)，维护堆 O(logn)
            3.5 辅助查找：栈(先进后出场景) -> 栈顶操作 O(1)
                                ↓
                          先遇到元素，暂时不知道怎么处理，临时存储(stack)
                          后面等后面元素处理完后，再处理前面的元素
            --------------------------------------------
            想要：既降低时间复杂度，又降低空间复杂度
                                         ↓
                                 空间复杂度一般是 O(1)
            3.6 双指针 -> 针对：线性数据结构，一般是数组
                1.快慢指针
                2.对撞指针
                3.滑动窗口

                总结：线性数据结构 + 空间复杂度限制 O(1) => 双指针

        KeyPoint 注意事项
        本章节题目，从暴力解法，一步一步优化，将之前所有知识进行串联
        从中学习解决问题的基本思路，遇到陌生的题目也能求解
     */
}
