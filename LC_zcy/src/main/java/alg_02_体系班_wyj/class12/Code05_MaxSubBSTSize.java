package alg_02_体系班_wyj.class12;

import java.util.ArrayList;

/**
 * @Author Wuyj
 * @DateTime 2022-09-29 15:15
 * @Version 1.0
 */
public class Code05_MaxSubBSTSize {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 方法一
    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    // 方法二
    public static int maxSubBSTSize2(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxBSTSubTreeSize;
    }

    public static class Info {
        public int maxBSTSubTreeSize;
        public int allSize;
        public int max;
        public int min;

        public Info(int maxBSTSubTreeSize, int allSize, int max, int min) {
            this.maxBSTSubTreeSize = maxBSTSubTreeSize;
            this.allSize = allSize;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int max = head.value;
        int min = head.value;
        int allSize = 1;

        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
            allSize += leftInfo.allSize;
        }

        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
            allSize += rightInfo.allSize;
        }

        int p1 = -1;
        if (leftInfo != null) {
            p1 = leftInfo.maxBSTSubTreeSize;
        }

        int p2 = -1;
        if (rightInfo != null) {
            p2 = rightInfo.maxBSTSubTreeSize;
        }

        int p3 = -1;
        boolean leftBST = (leftInfo == null) ? true : (leftInfo.allSize == leftInfo.maxBSTSubTreeSize);
        boolean rightBST = (rightInfo == null) ? true : (rightInfo.allSize == rightInfo.maxBSTSubTreeSize);
        if (leftBST && rightBST) {
            boolean leftMaxLessHead = (leftInfo == null) ? true : (leftInfo.max < head.value);
            boolean rightMinMoreHead = (rightInfo == null) ? true : (rightInfo.min > head.value);
            if (leftMaxLessHead && rightMinMoreHead) {
                int leftSize = (leftInfo == null) ? 0 : leftInfo.allSize;
                int rightSize = (rightInfo == null) ? 0 : rightInfo.allSize;
                p3 = leftSize + rightSize + 1;
            }
        }
        int maxBSTSubTreeSize = Math.max(Math.max(p1, p2), p3);
        return new Info(maxBSTSubTreeSize, allSize, max, min);
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
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
