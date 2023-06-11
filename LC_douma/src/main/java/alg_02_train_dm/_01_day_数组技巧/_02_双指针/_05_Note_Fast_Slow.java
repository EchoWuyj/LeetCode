package alg_02_train_dm._01_day_数组技巧._02_双指针;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 16:50
 * @Version 1.0
 */
public class _05_Note_Fast_Slow {
    /*
        快慢指针总结
                                      fast
                                       ↓
        nums   6  5  4  6  8  0  0  0  1  0  6  2  6
             已经处理的区域 |  ↑        | 未处理的区域
                 非零元素    slow
                 1        |     2     |     3

        快慢指针将数组划分 3 段
        1 已处理区域
        2 已处理区域，但是不符合题目要求
        3 未处理区域

        slow 指针：指向已经处理的区域的后面一个位置
        fast 指针：指向当前正在处理的元素

     */
}
