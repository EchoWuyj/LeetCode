package alg_02_体系班_zcy.class11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2022-09-25 16:10
 * @Version 1.0
 */
public class Code05_TreeMaxWidth01 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 方法一(使用HashMap)
    // 通过HashMap的容器,将所有的孩子都标记好是那一层的
    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        // key节点,value在哪一层
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);

        // 当前你正在统计哪一层的宽度
        int curLevel = 1;
        // 当前层curLevel层,宽度目前是多少
        int curLevelNodes = 0;
        int max = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }

            // 当前层curLevel和当前节点所在层是否一致,从而判断当前层是否结束
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                // curLevelNodes重置为1,新的一层第一个节点
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    // 方法二(不使用HashMap)
    // 在宽度优先遍历的基础上,标记每层结束位置,通过有限几个变量实现
    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        // 当前层最右节点
        Node curEnd = head;
        // 下一层最右节点
        Node nextEnd = null;

        int max = 0;
        // 当前层的节点数
        int curLevelNodes = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                // 每遍历左孩子节点都及时更新nextEnd,为下层作准备
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                // 每遍历右孩子节点都及时更新nextEnd,为下层作准备
                nextEnd = cur.right;
            }
            // 节点出队才计数
            curLevelNodes++;
            // 判断队列弹出的节点是否为当前层的结尾,表示当前层已经结束了
            if (cur == curEnd) {
                // 更新max
                max = Math.max(max, curLevelNodes);
                // 即将新的一层开始,当前层的节点数归0,后面计算的是下层的节点
                curLevelNodes = 0;
                // 即将新的一层开始,下一层最右节点->当前层最右节点
                curEnd = nextEnd;
            }
        }
        return max;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
