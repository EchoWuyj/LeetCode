package alg_01_ds_wyj._02_tree._02_bst;

import sun.applet.Main;

/**
 * @Author Wuyj
 * @DateTime 2023-03-14 10:54
 * @Version 1.0
 */
public class BSTTest {
    public static void main(String[] args) {

        System.out.println("非递归版本");
        tes1();
        System.out.println("===========");
        test2();
        System.out.println("递归版本");
    }

    private static void test2() {
        BSTR<Integer> bst = new BSTR<>();
        bst.add(33);
        bst.add(22);
        bst.add(66);
        bst.add(12);
        bst.add(35);
        bst.add(70);
        bst.add(34);
        bst.add(50);
        bst.add(68);
        bst.add(99);

        System.out.println(bst.preOrder()); // [33, 22, 12, 66, 35, 34, 50, 70, 68, 99]
        System.out.println(bst.inOrder()); // [12, 22, 33, 34, 35, 50, 66, 68, 70, 99] => 递增序列
        System.out.println(bst.postOrder()); // [12, 22, 34, 50, 35, 68, 99, 70, 66, 33]

        System.out.println(bst.contains(34)); // true
        System.out.println(bst.minValue()); // 12
        System.out.println(bst.maxValue()); // 99

        System.out.println(bst.removeMin()); // 12
        System.out.println(bst.inOrder()); // [22, 33, 34, 35, 50, 66, 68, 70, 99]

        System.out.println(bst.removeMax()); // 99
        System.out.println(bst.inOrder()); // [22, 33, 34, 35, 50, 66, 68, 70]

        bst.remove(66);
        bst.remove(12);
        System.out.println(bst.inOrder()); // [22, 33, 34, 35, 50, 68, 70]
    }

    private static void tes1() {
        BST<Integer> bst = new BST<>();
        bst.add(33);
        bst.add(22);
        bst.add(66);
        bst.add(12);
        bst.add(35);
        bst.add(70);
        bst.add(34);
        bst.add(50);
        bst.add(68);
        bst.add(99);

        System.out.println(bst.preOrder()); // [33, 22, 12, 66, 35, 34, 50, 70, 68, 99]
        System.out.println(bst.inOrder()); // [12, 22, 33, 34, 35, 50, 66, 68, 70, 99] => 递增序列
        System.out.println(bst.postOrder()); // [12, 22, 34, 50, 35, 68, 99, 70, 66, 33]
        System.out.println(bst.levelOrder()); // [[33], [22, 66], [12, 35, 70], [34, 50, 68, 99]]

        System.out.println(bst.contains(34)); // true
        System.out.println(bst.minValue()); // 12
        System.out.println(bst.maxValue()); // 99

        System.out.println(bst.removeMin()); // 12
        System.out.println(bst.inOrder()); // [22, 33, 34, 35, 50, 66, 68, 70, 99]

        System.out.println(bst.removeMax()); // 99
        System.out.println(bst.inOrder()); // [22, 33, 34, 35, 50, 66, 68, 70]

        bst.remove(66);
        bst.remove(12);
        System.out.println(bst.inOrder()); // [22, 33, 34, 35, 50, 68, 70]

        bst.remove1(35);
        bst.remove(12);
        System.out.println(bst.inOrder()); // [22, 33, 34, 50, 68, 70]

        bst.set(50, 100); // 破坏了二叉查找树的性质
        System.out.println(bst.inOrder()); // [22, 33, 34, 35, 100, 68, 70]
    }
}
