package alg_02_体系班_zcy.class16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code03_01_TopologySort {

    // 图的拓扑排序算法
    // 排序要求:有向无环图
    // 1)在图中找到所有入度为0的点输出
    // 2)把所有入度为0的点在图中删掉,继续找入度为0的点输出,周而复始
    // 3)图的所有点都被删除后,依次输出的顺序就是拓扑
    // 应用:事件安排,译顺序

    // 返回节点类型的列表,从左往右是拓扑排序后的
    public static List<Node> sortedTopology(Graph graph) {
        // key某个节点,value剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 只有剩余入度为0的点,才进入这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        // 遍历图的点集
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        // 结果集合
        List<Node> result = new ArrayList<>();

        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            //  遍历cur所有的邻居点
            for (Node next : cur.nexts) {
                // 剩余入度减1
                inMap.put(next, inMap.get(next) - 1);
                // 入度为0的点,加入到zeroInQueue中
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
