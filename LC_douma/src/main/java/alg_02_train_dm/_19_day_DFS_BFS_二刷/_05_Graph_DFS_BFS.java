package alg_02_train_dm._19_day_DFS_BFS_二刷;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 15:58
 * @Version 1.0
 */
public class _05_Graph_DFS_BFS {

    // set 标记已经遍历的节点
    private final Set<Node> visited = new HashSet<>();

    // KeyPoint 1.DFS => 适用'有向图'和'无向图'
    public List<Integer> preorder(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, res);
        return res;
    }

    private void dfs(Node node, List<Integer> res) {
        if (node == null) return;
        // 处理当前遍历的节点
        res.add(node.val);
        // 已经遍历节点加入 set
        visited.add(node);
        for (Node child : node.children) {
            // 该节点没有遍历，才去遍历
            if (!visited.contains(child)) dfs(child, res);
        }
    }

    // KeyPoint 2.有向图 BFS => 适用'有向图'和'无向图'
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        // 已经加入队列节点，将其加入 set 表示已经被访问
        visited.add(root);
        while (!queue.isEmpty()) {
            // 每轮循环遍历处理一层的节点
            int size = queue.size();
            List<Integer> levelNodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                levelNodes.add(curr.val);
                for (Node child : curr.children) {
                    if (!visited.contains(child)) queue.offer(child);
                    // 加入队列节点，将其加入 set 表示已经被访问
                    visited.add(child);
                }
            }
            res.add(levelNodes);
        }

        return res;
    }

    public static void main(String[] args) {
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        List<Node> children2 = Arrays.asList(node5, node6);
        Node node2 = new Node(2);
        node2.children = children2;

        Node node4 = new Node(4);
        Node node3 = new Node(3);

        List<Node> children1 = Arrays.asList(node2, node3, node4);
        Node root = new Node(1);
        root.children = children1;

        // 加了一条边，形成图结构
        node5.children = Arrays.asList(root);

        List<List<Integer>> res = new _05_Graph_DFS_BFS().levelOrder(root);
        System.out.println(res);
    }
}
