package alg_02_体系班_zcy.class11;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2022-09-28 11:53
 * @Version 1.0
 */
public class LeetCode_429_NAryTreeLevelOrderTraversal {
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        // 结果集合
        List<List<Integer>> res = new LinkedList<>();
        // 判空
        if (root == null) {
            return res;
        }
        // root入队
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        // while循环
        while (!queue.isEmpty()) {
            // size
            int size = queue.size();
            // temp集合
            List<Integer> tmp = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                tmp.add(cur.val);
                // KeyPoint 注意事项
                //  使用增强for循环,避免通过索引下标进行判断
                for (Node child : cur.children) {
                    if (child != null) {
                        queue.add(child);
                    }
                }
            }
            res.add(tmp);
        }
        return res;
    }
}
