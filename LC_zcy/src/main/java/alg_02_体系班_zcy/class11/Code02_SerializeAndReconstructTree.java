package alg_02_体系班_zcy.class11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-09-25 16:09
 * @Version 1.0
 */
public class Code02_SerializeAndReconstructTree {
    /*
     * 1)二叉树可以通过先序,后序或者按层遍历的方式序列化和反序列化(以下代码全部实现了)
     * 2)二叉树无法通过中序遍历的方式实现序列化和反序列化
     *   因为不同的两棵树,可能得到同样的中序序列,即便补了空位置也可能一样,比如如下两棵树
     *
     *          2    1
     *        ↙  和  ↘
     *       1          2
     *
     * 补足空位置的中序遍历结果都是{null,1,null,2,null}
     *
     * */

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //-----------------先序-序列化-----------------

    // 1)先序序列化(递归实现)
    //  a.思路:递归实现,在递归的过程中,将整颗树序列化的结果填到该队列中
    //  b.Queue<String>是String类型的,不是Node类型
    public static Queue<String> preSerial(Node head) {
        // KeyPoint 注意事项
        //  1)仔细分析递归函数中判空代码书写的位置
        //  2)判空写在递归函数中,不能直接返回
        //  3)即使head为null,返回也是[],而不是直接为null
        Queue<String> ans = new LinkedList<>();
        // 递归函数
        pres(head, ans);
        return ans;
    }

    // 递归函数
    // KeyPoint 注意事项
    //  1)递归函数单独定义,因为形参和返回值类型和主函数不一样
    //  2)同时需要根据递归过程中调用关系,确定递归函数的形参和返回值类型
    public static void pres(Node head, Queue<String> ans) {
        if (head == null) {
            // Queue中可以存null,同时Queue<String>类型也是可以存null值
            // KeyPoint 注意:这里存储的是不是"null",需要区分
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            // KeyPoint 本质:在先序遍历的递归版本上修改出来的
            // 先序列化左树
            pres(head.left, ans);
            // 再序列化右树
            pres(head.right, ans);
        }
    }

    //-----------------先序-反序列化-----------------

    // 2)先序反序列化
    // 思路:给定先序序列化队列preList,将整颗树建立起来,返回头节点
    public static Node buildByPreQueue(Queue<String> preList) {
        if (preList == null || preList.size() == 0) {
            // 返回空树
            return null;
        }
        return preb(preList);
    }

    // 递归函数
    public static Node preb(Queue<String> preList) {
        String value = preList.poll();
        // KeyPoint 返回上一层,到递归函数的入口,继续执行后面的代码
        if (value == null) {
            return null;
        }
        // 区别null和"null"
        Node head = new Node(Integer.parseInt(value));
        // preList集合经过poll()之后,preList集合里面的元素越来越少
        head.left = preb(preList);
        head.right = preb(preList);
        return head;
    }

    //-----------------中序-序列化-----------------

    // 中序序列化
    public static Queue<String> inSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        ins(head, ans);
        return ans;
    }

    public static void ins(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ins(head.left, ans);
            ans.add(String.valueOf(head.value));
            ins(head.right, ans);
        }
    }

    //-----------------后序-序列化-----------------

    // 后序序列化(递归实现)
    public static Queue<String> posSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        poss(head, ans);
        return ans;
    }

    // 序列化的递归函数
    public static void poss(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            poss(head.left, ans);
            poss(head.right, ans);
            ans.add(String.valueOf(head.value));
        }
    }

    //-----------------后序-反序列化-----------------

    // 后序反序列化
    public static Node buildByPosQueue(Queue<String> posList) {
        if (posList == null || posList.size() == 0) {
            return null;
        }
        // 左右中 -> stack(中右左)
        // KeyPoint 这里必须使用Stack,因为在反序列化的过程中,必须先确定树的头节点
        //      这样才能通过树的头节点来构建整颗树
        Stack<String> stack = new Stack<>();
        while (!posList.isEmpty()) {
            stack.push(posList.poll());
        }
        return posb(stack);
    }

    public static Node posb(Stack<String> posStack) {
        String value = posStack.pop();
        if (value == null) {
            return null;
        }
        // 这里没有使用队列,而是使用栈,所以需要根据,中右左来调整递归代码的顺序
        Node head = new Node(Integer.parseInt(value));
        head.right = posb(posStack);
        head.left = posb(posStack);
        return head;
    }

    //-----------------层次-序列化-----------------

    // 1)按层序列化
    public static Queue<String> levelSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            // 该队列辅助按层遍历的
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(head);
            // KeyPoint 整体还是按层遍历的逻辑,只是在基础上加上一些转换逻辑
            while (!queue.isEmpty()) {
                head = queue.poll();
                // 每个父序列化子节点,在子节点进队时进行序列化
                if (head.left != null) {
                    ans.add(String.valueOf(head.left.value));
                    queue.add(head.left);
                } else {
                    // 即使为孩子为空,也是需要使用null占个位置,但是不往队列中放
                    ans.add(null);
                }
                if (head.right != null) {
                    ans.add(String.valueOf(head.right.value));
                    queue.add(head.right);
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    //-----------------层次-反序列化-----------------

    // 2)按层反序列化(队列)
    public static Node buildByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        // 先确定头节点
        Node head = generateNode(levelList.poll());
        // 队列
        Queue<Node> queue = new LinkedList<Node>();
        // head=null,是空树情况,跳过中间的代码,最后直接return head;
        if (head != null) {
            queue.add(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            // 因为层次遍历的顺序是从左往右的,所以是先生成left,再生成right
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());

            // 反序列化之后,还要将其加入队列中,为了后续的节点出队,维持二叉树结构
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }

    // 生成节点方法
    // KeyPoint 单独抽取出来,因为后面多次需要重复使用
    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.parseInt(val));
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
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    // for test
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = preSerial(head);
            Queue<String> pos = posSerial(head);
            Queue<String> level = levelSerial(head);
            Node preBuild = buildByPreQueue(pre);
            Node posBuild = buildByPosQueue(pos);
            Node levelBuild = buildByLevelQueue(level);
            if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");
    }
}
