package alg_02_体系班_zcy.class10;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-09-25 16:08
 * @Version 1.0
 */
public class Code03_UnRecursiveTraversalBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 先序非递归(栈)
    // 1)判空,先将head压栈
    // 2)从栈弹出节点,记作head,输出打印节点值
    // 3)有右压入右,有左压入左,一定先右再左(栈的缘故)
    // 4)周而复始,直到栈变空,停止程序
    public static void pre(Node head) {
        System.out.print("pre-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            // add方法返回值为boolean
            stack.add(head);
            // KeyPoint 注意事项:区别if和while,if只是判断一次,while是循环判断
            //      尤其是在只有单个点输出时,考虑是否是因为使用if,只判断了一次
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.value + " ");
                if (head.right != null) {
                    // push方法返回值为自身数据类型(推荐)
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    // 中序非递归(栈实现)
    // 1)当前节点head,head为头的树,所有的左边界都压栈,直到遇到null
    // 2)从栈中弹出tmp节点打印,同时让tmp节点的右孩子成为head,执行1)
    // 3)周而复始,直到栈变空,停止程序
    // KeyPoint 本质:
    //   1)整棵树的所有节点是可以被子树左边界分解掉的
    //   2)整体操作的顺序:先搞完左树,再搞root节点,再搞右树,所以是中序遍历
    public static void in(Node head) {
        System.out.print("in-order: ");
        if (head != null) {
            // 不用先将root加入,while循环中统一判断,区别于队列的层次遍历
            Stack<Node> stack = new Stack<Node>();
            // 这里while循环条件是2个,不能仅仅是一个head!=null,因为head指针走到最底部到null
            // 其叶子节点的right,可能是null,这样while单是一个判断条件,则循环结束了,但还有其他节点没有遍历到!
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    // 左边界遇到null,则左边界到底,此时栈弹出的是连接null的叶子节点
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    // 后序非递归(两个栈),在先序的基础上修改而得
    public static void pos1(Node head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
            s1.push(head);
            while (!s1.isEmpty()) {
                // 遍历:头,右,左
                head = s1.pop();
                // 再压栈,为了后面逆序,进行出栈
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            // 通过另外一个栈实现逆序,即左,右,头
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " ");
            }
        }
        System.out.println();
    }

    // 后序非递归(一个栈实现)
    // 比较难懂,可以不用掌握
    public static void pos2(Node h) {
        System.out.print("pos-order: ");
        if (h != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(h);
            Node c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && h != c.left && h != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && h != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    h = c;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head); // 1 2 4 5 3 6 7
        System.out.println("========");
        in(head); // 4 2 5 1 6 3 7
        System.out.println("========");
        pos1(head); // 4 5 2 6 7 3 1
        System.out.println("========");
        pos2(head); // 4 5 2 6 7 3 1
        System.out.println("========");
    }
}
