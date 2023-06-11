package algorithm._07_stack_and_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2022-03-19 16:20
 * @Version 1.0
 */

//使用两个队列实现自定义栈
public class LeetCode_225_MyStack {
    //定义两个队列
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public LeetCode_225_MyStack() {
        //Queue只是一个接口,队列是线性数据结构,具体的实现直接使用LinkedList
        //LinkedList是双向链表,同时实现类双端队列Deque,这里将其当做基本的队列来使用
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    //入栈方法
    public void push(int x) {
        //1.把x保存到queue2中
        //add失败会抛异常,offer不会抛异常会返回一个null值
        //offer更加符合代码完整性的要求,所以使用offer
        queue2.offer(x);

        //2.将queue1中所有元素依次出队,然后放入queue2
        while (!queue1.isEmpty()) {
            //queue1.poll()表示queue1出队的元素,是有返回值的
            queue2.offer(queue1.poll());
        }

        //3.交换两个队列,需要临时的指针
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    //出栈操作,出栈是有返回值的
    public int pop() {
        //queue1出队就是出栈
        return queue1.poll();
    }

    //获取栈顶元素
    public int top() {
        return queue1.peek();
    }

    //判断为空
    //KeyPoint 定义方法时,一定得明确形参和返回值
    // 不能想当然的使用void和没有输入参数
    public boolean empty() {
        return queue1.isEmpty();
    }
}
