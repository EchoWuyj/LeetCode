package alg_02_train_dm._20_day_数据结构设计_二刷;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-05-27 18:13
 * @Version 1.0
 */

public class _03_offer_09_stack_to_queue1 {

     /*
        剑指 Offer 09. 用两个栈实现队列
        请用两个栈来实现一个队列，这个队列的实现类中含有两个方法：
        1. appendTail(int value) ：在队列的尾部插入一个整数
        2. deleteHead() ：删除队列头部的元素，并返回删除的元素，如果队列中没有元素的话，则返回 -1

        示例 1：
        输入：
        ["CQueue","appendTail","deleteHead","deleteHead","deleteHead"]
        [[],[3],[],[],[]]
        输出：[null,null,3,-1,-1]

        提示：
        1 <= values <= 10000
        最多会对 appendTail、deleteHead 进行 10000 次调用

     */

    // KeyPoint 方法一 两个栈
    class CQueue1 {

        //     | 2 |
        //     | 7 |
        //     | 1 |
        //     |_3_| ← 删除 3
        //   mainStack

        //     |   |             | 3 | ← 删除 3
        //     |   |             | 1 |
        //     |   |             | 7 |
        //     |___|             |_2_|
        //   mainStack         helpStack

        // 有新元素 10 加入
        // 1.先将 helpStack 中元素装入 mainStack 中
        // 2.再去加入新元素 10

        //
        //     | 10 |
        //     | 2 |           |   |
        //     | 7 |           |   |
        //     |_1_|           |___|
        //   mainStack       helpStack

        // 主栈
        Deque<Integer> mainStack;
        // 辅助栈
        Deque<Integer> helpStack;

        public CQueue1() {
            this.mainStack = new ArrayDeque<>();
            this.helpStack = new ArrayDeque<>();
        }

        // 时间复杂度 O(n)
        public void appendTail(int value) {
            // 将 helpStack 转移 mainStack
            while (!helpStack.isEmpty()) {
                mainStack.push(helpStack.pop());
            }
            // stack1 压入元素
            mainStack.push(value);
        }

        // 时间复杂度 O(n)
        public int deleteHead() {
            // 将 mainStack 转移 helpStack
            while (!mainStack.isEmpty()) {
                helpStack.push(mainStack.pop());
            }

            // stack2 不为空，则 stack2 pop 元素
            if (helpStack.isEmpty()) {
                return -1;
            } else {
                return helpStack.pop();
            }
        }
    }
}
