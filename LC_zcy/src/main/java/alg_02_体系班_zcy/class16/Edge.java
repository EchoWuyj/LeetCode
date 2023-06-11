package alg_02_体系班_zcy.class16;

// 边的定义
public class Edge {
    // 边的权重
    public int weight;
    // 起始点
    public Node from;
    // 终点
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
