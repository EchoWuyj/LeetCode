package alg_02_体系班_zcy.class28;

import java.util.ArrayList;

public class Code02_TreeEqual {

    /*
        二叉树子结构:给定两个二叉树T1和T2,返回T1的某个子树结构是否与T2的结构相等
	        3
	     1    4         4
	      2  5 1       5
	       T1          T2

        子树结构:从一个头节点出发(确定某个头节点),所有节点都得要,不能只要其中一部分
                 T1中以4为头节点的子树是451,和T2的45是不一样的
	    思路:
	      1)首相将两棵树进行序列化字符串(先序方式序列化)
	        一个树的序列化结果需要将该树的所有结构带出来,故该树的结构其是完整的
	        时间复杂度O(N)
	        注意:切记加上分割符和空节点标记
	        ["3","1",null,"2"...] 使用字符串存每个节点值,整体是字符串类型数组,
	        将整个数组理解成一个字符串,即原KMP中是char[],现在这里是String[]
　　      2)然后使用KMP算法进行两个字符串匹配,判断子树串是否属于原树串即可
            KMP时间复杂度O(N)
     */

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 对数器
    public static boolean containsTree1(Node big, Node small) {
        if (small == null) {
            // small为空树是符合要求的
            return true;
        }
        if (big == null) {
            return false;
        }
        if (isSameValueStructure(big, small)) {
            return true;
        }
        return containsTree1(big.left, small) || containsTree1(big.right, small);
    }

    // 判断是否为相同结构
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

        // 只要T1和T2只要有值不相等节点则结构不同
        // KeyPoint 注意不能因为有一个节点相同就判断其结构相同
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left)
                && isSameValueStructure(head1.right, head2.right);
    }

    // 利用KMP判断子树结构是否包含
    public static boolean containsTree2(Node big, Node small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        ArrayList<String> b = preSerial(big);
        ArrayList<String> s = preSerial(small);
        String[] str = new String[b.size()];

        for (int i = 0; i < str.length; i++) {
            str[i] = b.get(i);
        }

        String[] match = new String[s.size()];
        for (int i = 0; i < match.length; i++) {
            match[i] = s.get(i);
        }
        return getIndexOf(str, match) != -1;
    }

    // 先序方式序列化
    public static ArrayList<String> preSerial(Node head) {
        ArrayList<String> ans = new ArrayList<>();
        pres(head, ans);
        return ans;
    }

    // 递归先序遍历序列化树
    public static void pres(Node head, ArrayList<String> ans) {
        // 递归边界
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }

    // KMP
    public static int getIndexOf(String[] str1, String[] str2) {
        if (str1 == null || str2 == null || str1.length < 1 || str1.length < str2.length) {
            return -1;
        }
        int x = 0;
        int y = 0;
        int[] next = getNextArray(str2);
        while (x < str1.length && y < str2.length) {
            // KeyPoint 关键不同点
            //  标准KMP比较的数据类型为char[],str1[x],str2[y]进行比较
            //  但是本题数据类型为String[],需要自己定义比较的方法
            if (isEqual(str1[x], str2[y])) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }

    public static int[] getNextArray(String[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length) {
            if (isEqual(ms[i - 1], ms[cn])) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static boolean isEqual(String a, String b) {

        if (a == null && b == null) {
            return true;
        } else {
            // 在a和b不全为null情况下
            // 1) a==null,b!=null
            // 2) a!=null,b==null
            // 3) a!=null,b!=null
            if (a == null || b == null) {
                return false;
            } else {
                // 比较字符串是否相等
                return a.equals(b);
            }
        }
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
        int bigTreeLevel = 7;
        int smallTreeLevel = 4;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node big = generateRandomBST(bigTreeLevel, nodeMaxValue);
            Node small = generateRandomBST(smallTreeLevel, nodeMaxValue);
            boolean ans1 = containsTree1(big, small);
            boolean ans2 = containsTree2(big, small);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");
    }
}
