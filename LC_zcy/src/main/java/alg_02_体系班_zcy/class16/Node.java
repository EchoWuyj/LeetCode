package alg_02_体系班_zcy.class16;

import java.util.ArrayList;

// 点结构的描述
public class Node {

    // 点上的value
    public int value;
    // 点的入度,多少边指向该点
    public int in;
    // 点的出度,从该点出发多少边指向别人
    public int out;

    // 从该节点出发能找到的邻居节点
    public ArrayList<Node> nexts;
    // 从该节点出发,直接指向邻居节点的边
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
