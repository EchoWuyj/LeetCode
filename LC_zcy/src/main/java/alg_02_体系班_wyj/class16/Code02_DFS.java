package alg_02_体系班_wyj.class16;

import java.util.HashSet;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-10-04 11:40
 * @Version 1.0
 */
public class Code02_DFS {

    public static void dfs(Node start) {
        if (start == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();

        stack.add(start);
        set.add(start);
        System.out.println(start.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
