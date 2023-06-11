package alg_02_体系班_wyj.class16;

import java.util.ArrayList;

/**
 * @Author Wuyj
 * @DateTime 2022-10-03 12:10
 * @Version 1.0
 */

// 节点描述
public class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
