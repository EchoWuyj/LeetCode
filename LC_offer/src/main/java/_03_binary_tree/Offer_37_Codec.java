package _03_binary_tree;

import sun.applet.Main;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2022-09-27 15:52
 * @Version 1.0
 */
public class Offer_37_Codec {

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        Queue<String> queue = new LinkedList<>();
        pres(root, queue);
        return queue.toString();
    }

    public static void pres(TreeNode head, Queue<String> queue) {
        if (head == null) {
            queue.add(null);
        } else {
            queue.add(String.valueOf(head.val));
            pres(head.left, queue);
            pres(head.right, queue);
        }
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        /*
         * (1)String str1 = null;
         *   表示str1的引用为空,他没有地址,他是一个没有被实例化的对象
         * (2)String str2 = " ";
         *   表示str2引用为空字符串,有地址,他是被实例化的对象,仅仅值为空而已
         * (3)如果是string对象是null ,用 == 来判断,否则会抛出异常
         *    "=="操作在对String这种引用数据类型来说,比较的是地址
         * (4)如果是空字符串"" 如果是空字符串"",用来equals() 判断,
         *    str.equals(""),equals()"判断的是内容
         */

        // KeyPoint 判空条件的书写
        if (" ".equals(data)) {
            return null;
        }

        // 包装类型是Character,不是Char类型
        // 若使用集合来存储,[1, 3, 4, 5]中间存在空格
        Queue<String> queue = new LinkedList<>();
        String[] datas = data.split(",");
        for (int i = 0; i < datas.length; i++) {
            // replace方法:支持字符和字符串的替换
            // replaceAll方法:基于正则表达式的字符串替换
            datas[i] = datas[i].replaceAll("[\\[\\]]", "").replaceAll("\\s", "");
            // 这里null是字符串"null",不是null
            if (!datas[i].equals("null")) {
                queue.add(datas[i]);
            } else {
                // 将字符串"null"替换成null;
                queue.add(null);
            }
        }

//        KeyPoint 错误方法,因为null会被切分成n,u,l,l
//             还是使用推荐使用split()进行切分
//        for (int i = 1; i < chars.length - 1; i++) {
//            if (',' == chars[i] || ' ' == chars[i]) {
//                continue;
//            }
//            queue.add(chars[i] + "");

        // System.out.println(queue);
        return pred(queue);
    }

    public static TreeNode pred(Queue<String> queue) {
        String value = queue.poll();
        if (value == null) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(value));
        head.left = pred(queue);
        head.right = pred(queue);
        return head;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.right.left = new TreeNode(5);
        head.right.right = new TreeNode(6);
        head.left.left.right = new TreeNode(7);

        String data = serialize(head);
        System.out.println(data);
        TreeNode newHead = deserialize(data);
        String newData = serialize(newHead);
        System.out.println(newData);

        //----------------------------------------------

        //替换成空字符串
        String ss1 = "[a12,da,das]";
        String replaceAll1 = ss1.replaceAll("[\\[\\]]", "");
        System.out.println(replaceAll1);// a12,da,das

        //替换小括号同样原理
        String ss2 = "(a12,da,das)";
        String replaceAll2 = ss2.replaceAll("[\\(\\)]", "");
        System.out.println(replaceAll2);// a12,da,das
    }
}
