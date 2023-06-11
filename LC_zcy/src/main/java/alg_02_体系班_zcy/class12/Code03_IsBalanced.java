package alg_02_体系班_zcy.class12;

/**
 * @Author Wuyj
 * @DateTime 2022-09-25 16:11
 * @Version 1.0
 */
public class Code03_IsBalanced {

    // 给定一个二叉树,判断它是否是高度平衡的二叉树
    // 本题中一棵高度平衡二叉树定义为
    // 一个二叉树每个节点的左右两个子树的高度差的绝对值不超过1

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 方式一(上课没有提及)
    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // 方式二(递归实现)
    // 思路:想让x树时平衡树
    //         x
    //    左树  右树
    //  1)左树是平衡的
    //  2)左树是平衡的
    //  3)同时左树和右树的高度差的绝对值<=1
    public static boolean isBalanced2(Node head) {
        return process(head).isBalanced;
    }

    // 在以某个节点为头时,计算结果需要返回两个信息
    //  1)正颗树是否平衡
    //  2)正颗树的高度
    // 因此需要做出这样的Info结构
    public static class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static Info process(Node x) {
        if (x == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        boolean isBalanced = true;

        // 可以整合到一个if中
        if (!leftInfo.isBalanced) {
            isBalanced = false;
        }
        if (!rightInfo.isBalanced) {
            isBalanced = false;
        }
        if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalanced = false;
        }

        // KeyPoint 另外一种简单写法
//        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced
//                && Math.abs(leftInfo.height - rightInfo.height) < 2;

        return new Info(isBalanced, height);
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

    // for test
    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
