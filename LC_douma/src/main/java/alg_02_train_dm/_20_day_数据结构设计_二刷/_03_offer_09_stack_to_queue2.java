package alg_02_train_dm._20_day_数据结构设计_二刷;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-08-04 13:20
 * @Version 1.0
 */
public class _03_offer_09_stack_to_queue2 {

    // KeyPoint 方法二 优化：提高性能

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

    // 有新元素 10，9 加入
    // 1.先将 helpStack 中元素装入 mainStack 中
    // 2.再去加入新元素 10

    //
    //     |   |           | 1 | ← 原来的栈底变成栈顶，故可以直接删除
    //     | 9 |           | 7 |
    //     |_10_|          |_2_|
    //   mainStack       helpStack

    class CQueue {
        Deque<Integer> mainStack;
        Deque<Integer> helpStack;

        public CQueue() {
            mainStack = new ArrayDeque<>();
            helpStack = new ArrayDeque<>();
        }

        // 时间复杂度 O(1)
        public void appendTail(int value) {
            mainStack.push(value);
        }

        // 时间复杂度 O(n)
        public int deleteHead() {
            // 1.helpStack 为空，则将 mainStack 的所有元素都是 push 到 helpStack 中
            if (helpStack.isEmpty()) {
                // KeyPoint 区别：while 和 if 循环
                // 1.while 循环判断，处理不知道有几次循环的场景
                // 2.if 只能处理单个判断，所以本质并不相同
                while (!mainStack.isEmpty()) {
                    helpStack.push(mainStack.pop());
                }
            }
            // 2.helpStack 不为空，则 helpStack 中 pop 元素
            if (helpStack.isEmpty()) {
                return -1;
            } else {
                return helpStack.pop();
            }
        }
    }
}
