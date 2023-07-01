package alg_02_train_dm._19_day_DFS_BFS_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:26
 * @Version 1.0
 */

// N 叉树 Node 节点
public class Node {
    // 属性定义 public，可以通过 node.val 调用，否则还得 get 和 set
    public int val;
    public List<Node> children = new ArrayList<>();

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
