package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:40
 * @Version 1.0
 */
public class Node {
    int val;
    List<Node> children = new ArrayList<>();

    public Node() {

    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}
