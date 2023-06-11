package alg_02_体系班_zcy.class16;

import java.util.HashSet;
import java.util.Stack;

public class Code02_DFS {

    // 深度优先遍历
    // 本质:一条路没走完就走到死,走完则往上个节点找寻其他的路没有走
    //      走过的点不能再走,不能走出环路,这两点都可以通过set实现
    // 1)利用栈实现
    // 2)从源节点开始把节点按照深度放入栈,然后弹出
    // 3)每弹出一个点,把该节点下一个没有进过栈的邻接点放入栈
    // 4)直到栈变空

    public static void dfs(Node node) {
        if (node == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();

        stack.add(node);
        set.add(node);
        // 入栈就打印,打印就是深度优先遍历处理时机,可以将打印行为替换成想要的其他操作
        System.out.println(node.value);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            // 没有邻居,已经注册,for循环,if都不执行,stack继续弹出
            for (Node next : cur.nexts) {
                // 没有注册的邻居节点
                if (!set.contains(next)) {
                    // 将上个节点压入栈中
                    stack.push(cur);

                    // 上个节点的邻居节点操作
                    // 压入栈中
                    // 栈中存着当前的整条路径
                    stack.push(next);
                    // 注册
                    set.add(next);
                    // 打印
                    System.out.println(next.value);
                    // 跳出循环
                    break;
                }
            }
        }
    }

}
