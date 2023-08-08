package alg_02_train_dm._10_day_栈和队列_二刷;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-25 21:02
 * @Version 1.0
 */
public class _06_946_validate_stack_sequences {
    /*
        946 号算法题：验证栈序列
        给定 pushed 和 popped 两个序列，每个序列中的值都不重复，
        只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；
        否则，返回 false 。

        输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
        输出： true

        解释：
        push 1,2,3,4 => stack：1 2 3 4
        pop 4 => stack：1 2 3
        push 5 => stack：1 2 3 5
        pop 5 3 2 1 stack：空

        push(1), push(2), push(3), push(4), pop() -> 4,
        push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1

        最后 pop 的顺序就是 [4,5,3,2,1]，所以返回 true

        输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
        输出：false
        解释：1 不能在 2 之前弹出。

        push 1,2,3,4 => stack：1 2 3 4
        pop 4,3 =>  stack：1 2
        push 5  => stack：1 2 5
        pop 5 1 2 => 出现错误，2 在 1 之前，无法 pop

        提示
        0 <= pushed.length == popped.length <= 1000
        0 <= pushed[i], popped[i] < 1000
        pushed 是 popped 的排列
     */

    // 直接模拟，按照模拟流程，翻译成代码即可
    public boolean validateStackSequences(int[] pushed, int[] popped) {

        // pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
        //
        //    |   |
        //    |   |
        //    |   |
        //    |___|
        //    stack
        // 通过 stack 来验证一下两个序列的顺序即可

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // 遍历 popped 序列的指针
        int i = 0;
        // for 循环遍历 pushed 序列pushed
        for (int num : pushed) {
            // pushed 序列元素先压栈
            stack.push(num);
            // KeyPoint 单次 if 和 多次 while
            // 1.入栈或者出栈，连续符合条件，不是一次判断就行的，需要连续多次判断，使用 while 循环
            // 2.健壮性判断，加强 while 循环条件限制，避免 while 循环中的代码出错
            while (!stack.isEmpty() &&
                    i < popped.length
                    && popped[i] == stack.peek()) {
                stack.pop();
                i++;
            }
        }
        // 遍历到 popped 结尾，说明验证栈序列通过，返回 true
        return i == popped.length;
    }
}
