package alg_02_体系班_zcy.class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Code01_BFS {

    // 宽度优先遍历
    // 1)利用队列实现
    // 2)从源节点开始依次按照宽度进队列,然后弹出
    // 3)每弹出一个点,把该节点所有没有进过队列的邻接点放入队列
    //   通过set避免一个点重复进入队列,从而形成环路
    // 4)直到队列变空

    // 从node出发,进行宽度优先遍历
    public static void bfs(Node start) {
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();

        // 加入源节点
        queue.add(start);
        set.add(start);

        while (!queue.isEmpty()) {
            // 先将队列中节点出队,再去判断邻居节点
            Node cur = queue.poll();
            System.out.println(cur.value);
            // 遍历每一个直接邻居
            for (Node next : cur.nexts) {
                // 没有注册的节点(即没有进过队列的邻接点)注册且加入队列
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
