package alg_02_体系班_zcy.class11;

/**
 * @Author Wuyj
 * @DateTime 2022-09-25 16:10
 * @Version 1.0
 */
public class Code06_SuccessorNode01 {

    // 给你二叉树中的某个节点,返回该节点的后继节点
    // 后继节点:在中序遍历中,节点的后一个节点,叫该节点的后继节点
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        // 多个一个parent指针
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    // 时间复杂度O(k),k为查找节点到后继节点的真实距离(k个节点)
    // 因为后继节点出现的晚,所以一定在右树或者没有右树的位置,不会出现在左树上
    // 分两种情况:
    //   1)X有右树,则后继节点是右树上的最左孩子
    //   2)X没有右树,则往上看
    //      a.若是父亲的有孩子,则继续往上看,直到该节点为父节点的左孩子,则后继为该父节点
    //       本质:X是那个节点上左树上的最右
    //      b.若往上找的过程中,没有出现拐点,则X是整颗树的最右节点,则没有后继
    public static Node getSuccessorNode(Node node) { // node不一定是整棵树的头,可以是任何一个节点
        if (node == null) {
            return node;
        }
        // 一个节点如果有右子树,它的后继节点就是该节点右子树上最左的节点
        if (node.right != null) {
            // 找最左
            return getLeftMost(node.right);
            // 无右子树的情况
        } else {
            // 找父节点
            Node parent = node.parent;
            // 父亲节点不为空,且当前节点是其父亲节点右孩子
            // while循环停止情况:
            //   1)当前节点是父亲节点左孩子,while停止,返回父节点
            //   2)父节点为null,当前节点为整棵树的最右节点,则while停止
            while (parent != null && parent.right == node) {
                // node和parent指针整体上移
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    // 找右子树上最左的节点
    public static Node getLeftMost(Node node) {
        if (node == null) {
            return node;
        }
        // 最左位置
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }
}
