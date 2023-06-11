package alg_02_体系班_zcy.class12;

import java.util.ArrayList;

/**
 * @Author Wuyj
 * @DateTime 2022-09-25 16:11
 * @Version 1.0
 */
public class Code02_IsBST {

    // 验证二叉搜索树
    // 给你一个二叉树的根节点root,判断其是否是一个有效的二叉搜索树

    // 有效二叉搜索树定义如下
    //  1)节点的左子树只包含 小于 当前节点的数
    //  2)节点的右子树只包含 大于 当前节点的数
    //  3)经典的二叉搜索树中是没有重复值的!
    // 所有左子树和右子树自身必须也是二叉搜索树

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 方法一(中序遍历方法)
    // 通过中序遍历,将节点加入ArrayList中,通过判断里面的节点值是否递增即可
    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            // <=的反面就是严格大于,序列化中后一个节点严格大于前一个节点
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    // 中序遍历
    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    // 方法二(递归方法)
    // 判断搜索二叉树的原则
    //  1)左,右子树都是搜索二叉树
    //  2)同时左树上所有节点都比X要小(X>leftMax)
    //  3)并且右数上所有节点都比X要大(X<rightMin)
    public static boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    // 定义递归返回节点信息
    // 节点信息需要定义全,需要对每个节点都要适用
    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    // 递归函数
    public static Info process(Node x) {
        if (x == null) {
            // 空树不能将min和max设置为0,如果节点为负值,则是比0还小的,没法判断是搜索二叉树
            // 所以这里是单纯返回一个null即可,判断是否为null信息交给上游处理
            return null;
        }

        // 直接向左右子树要3个信息
        // 注意这里leftInfo和rightInfo可能为null,后面在使用过程需腰进行判空,否则空指针异常
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        // 需要将计算出来本颗树的3个信息isBST,max,min,将其封装成Info信息,这样递归程序才能执行
        int max = x.value;
        int min = x.value;

        // 这里一开始将其当做一般的树来判断,左右子树的最大值有可能比x.value要大
        // 注意这里不能默认该树是搜索二叉树,leftInfo.max < x.value,这个是需要后面判断的
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        // 1)先假设认为整颗树是搜索二叉树,同时将所有的违规条件都将其列全
        //   只要下面有一个不是搜索二叉树的条件满足,则不是搜索二叉树
        // 2)因为一开始设置就是true情况,节点为null情况直接跳过,所以还是搜索二叉树
        boolean isBST = true;
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        } else if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        } else if (leftInfo != null && leftInfo.max >= x.value) {
            isBST = false;
        } else if (rightInfo != null && rightInfo.min <= x.value) {
            isBST = false;
        }
        return new Info(isBST, max, min);
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
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
