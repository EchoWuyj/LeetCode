package alg_02_体系班_zcy.class12;

import java.util.ArrayList;

/**
 * @Author Wuyj
 * @DateTime 2022-09-25 16:12
 * @Version 1.0
 */
public class Code05_MaxSubBSTSize {

    // 给定一棵二叉树的头节点head,返回这颗二叉树中最大的二叉搜索子树的大小(节点数最多即为最大)
    // 子树定义:从头节点往下的节点都要,而不能只是选择一些节点,这才是子树

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

    // 目标:X为头节点的二叉树中最大的二叉搜索子树的大小
    // 1)若当前不以X为头(X本身不是二叉搜索树)
    //   a.X的左树中最大二叉搜索子树的大小
    //   b.X的右树中最大二叉搜索子树的大小
    // 2)若当前节点为X为头(X整体全是二叉搜索树)
    //   a.X的左子树是二叉搜索树
    //   b.X的右子树是二叉搜索树
    //   c.左树的max < X
    //   d.右树的min > X
    //   e.左树的大小+右树的大小+1,就是当前子树的大小
    // =>需要将相同的信息进行整合,化简

    // 方式二(递归实现)
    public static int maxSubBSTSize2(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxBSTSubtreeSize;
    }

    public static class Info {
        // 最大二叉搜索子树的大小
        public int maxBSTSubtreeSize;
        // 当前子树的大小
        public int allSize;
        public int max;
        public int min;
        // 注意:maxBSTSubtreeSize==size,则说明该树是搜索二叉树,可以作为二叉搜索树的判断;

        public Info(int maxBSTSubtreeSize, int allSize, int max, int min) {
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
            this.allSize = allSize;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(Node x) {
        if (x == null) {
            // max和min不知道该怎么设置,直接返回null
            return null;
        }
        // 收集信息
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int max = x.value;
        int min = x.value;
        // 假设只有X自己一个,故allSize=1
        // 若左右子树为null,满足递归边界,返回上一层,正好该树只有一个节点
        int allSize = 1;

        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
            // allSize = allSize + allSize 在原来基础上累加
            // 而不是 allSize = 1 + allSize;
            allSize += leftInfo.allSize;
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
            allSize += rightInfo.allSize;
        }

        // 关于maxBSTSubtreeSize计算,分不同的可能性,将p1,p2,p3分别设置为-1;
        // 因为最后p1,p2,p3是需要比较,maxBSTSubtreeSize>0,设置为-1自然不会胜出

        // 可能性1:左树中最大二叉搜索子树的大小
        int p1 = -1;
        if (leftInfo != null) {
            p1 = leftInfo.maxBSTSubtreeSize;
        }

        // 可能性2:右树中最大二叉搜索子树的大小
        int p2 = -1;
        if (rightInfo != null) {
            p2 = rightInfo.maxBSTSubtreeSize;
        }

        // 可能性3:若当前节点为X为头(X整体全是二叉搜索树)
        int p3 = -1;

        // 左,右子树是否二叉搜索树
        boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
        boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
        // 只有在左,右子树都是二叉搜索树的前提下,X的整颗子树才是二叉搜索树
        if (leftBST && rightBST) {
            boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.value);
            boolean rightMinMoreX = rightInfo == null ? true : (x.value < rightInfo.min);
            // KeyPoint 局部变量只能在里面判断
            if (leftMaxLessX && rightMinMoreX) {
                // KeyPoint 三元运算符是要进行赋值操作的
                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                p3 = leftSize + rightSize + 1;
                // 因为leftInfo和rightInfo都为null情况下,if判断的结果都是true的,所以这里还是要进行判空,否则空指针异常
//                p3 = leftInfo.allSize + rightInfo.allSize + 1;
            }
        }
        return new Info(Math.max(p1, Math.max(p2, p3)), allSize, max, min);
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
