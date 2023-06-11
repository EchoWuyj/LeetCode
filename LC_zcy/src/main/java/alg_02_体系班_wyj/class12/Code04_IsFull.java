package alg_02_体系班_wyj.class12;

/**
 * @Author Wuyj
 * @DateTime 2022-09-29 14:39
 * @Version 1.0
 */
public class Code04_IsFull {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 方法一
    public static boolean isFull1(Node head) {
        if (head == null) {
            return true;
        }
        return node(head) == (1 << height(head)) - 1;
    }

    public static int height(Node head) {
        if (head == null) {
            return 0;
        }
        return Math.max(height(head.left), height(head.right)) + 1;
    }

    public static int node(Node head) {
        if (head == null) {
            return 0;
        }
        return (node(head.left) + node(head.right)) + 1;
    }

    public static boolean isFull2(Node head) {
        if (head == null) {
            return true;
        }
        Info info = process(head);
        return info.nodes == (1 << info.height) - 1;
    }

    public static class Info {
        public int height;
        public int nodes;

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info(height, nodes);
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isFull1(head) != isFull2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
