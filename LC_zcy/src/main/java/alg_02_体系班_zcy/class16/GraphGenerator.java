package alg_02_体系班_zcy.class16;

public class GraphGenerator {

    // matrix 所有的边
    // N*3 的矩阵
    // [weight,from节点上面的值,to节点上面的值]
    // [5,0,7]
    // [3,0,1]
    // ...

    // 转换接口
    // 关键:根据具体的题目进行修改,添加
    public static Graph createGraph(int[][] matrix) {
        // 刚开始定义,点集和边集都没有
        Graph graph = new Graph();
        // 拿到每一条边matrix[i]
        for (int i = 0; i < matrix.length; i++) {
            // 获取matrix中edge信息
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];

            // 1)封装graph的notes信息(点集)
            // 判断图的点集中是否有from节点
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            // 判断图的点集中是否有to节点
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }

            // 2)封装graph的edges信息(边集)
            // 获取 from和to节点,封装edge信息
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);

            // 封装节点信息
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
