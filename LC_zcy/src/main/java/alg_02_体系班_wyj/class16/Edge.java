package alg_02_体系班_wyj.class16;

/**
 * @Author Wuyj
 * @DateTime 2022-10-03 12:20
 * @Version 1.0
 */

// 边的定义
public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
