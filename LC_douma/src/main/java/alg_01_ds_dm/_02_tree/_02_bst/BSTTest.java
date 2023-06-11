package alg_01_ds_dm._02_tree._02_bst;

/**
 * @Author Wuyj
 * @DateTime 2023-03-13 14:23
 * @Version 1.0
 */
public class BSTTest {
    public static void main(String[] args) {
        System.out.println("非递归版本");
        test1();
        System.out.println("======================");
        System.out.println("递归版本");
        test2();
    }

    private static void test2() {
        BSTR<Integer> bstr = new BSTR<>();
        bstr.add(33);
        bstr.add(22);
        bstr.add(66);
        bstr.add(12);
        bstr.add(35);
        bstr.add(70);
        bstr.add(34);
        bstr.add(50);
        bstr.add(68);
        bstr.add(99);

        System.out.println(bstr.preOrder()); // [33, 22, 12, 66, 35, 34, 50, 70, 68, 99]
        System.out.println(bstr.inOrder()); // [12, 22, 33, 34, 35, 50, 66, 68, 70, 99] => 递增序列
        System.out.println(bstr.postOrder()); // [12, 22, 34, 50, 35, 68, 99, 70, 66, 33]

        System.out.println(bstr.contains(34)); // true
        System.out.println(bstr.minValue()); // 12
        System.out.println(bstr.maxValue()); // 99

        System.out.println(bstr.removeMin()); // 12
        System.out.println(bstr.inOrder()); // [22, 33, 34, 35, 50, 66, 68, 70, 99]

        System.out.println(bstr.removeMax()); // 99
        System.out.println(bstr.inOrder()); // [22, 33, 34, 35, 50, 66, 68, 70]

        bstr.remove(66);
        bstr.remove(12);
        System.out.println(bstr.inOrder()); // [22, 33, 34, 35, 50, 68, 70]
    }

    private static void test1() {
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

        /*
                        33
                      /     \
                    22       66
                   /        /  \
                  12       35   70
                         /  \   /  \
                       34   50  68  99

         */

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
