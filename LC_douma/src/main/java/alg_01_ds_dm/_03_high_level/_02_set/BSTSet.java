package alg_01_ds_dm._03_high_level._02_set;

import alg_01_ds_dm._02_tree._02_bst.BST;

import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 23:53
 * @Version 1.0
 */

// E 保证每个元素能进行比较，所以需要实现 Comparable 接口
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    // BST 实现有序的 Set
    private BST<E> bst;

    // 无参构造需要创建 BST 对象
    public BSTSet() {
        this.bst = new BST<>();
    }

    @Override
    public int size() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public void add(E e) { // O(logn)
        // 直接添加即可，不需要再 contains 判断， BST 的 add 方法本身就不能存储重复元素
        bst.add(e);
    }

    @Override
    public void remove(E e) { // O(logn)
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) { // O(logn)
        return bst.contains(e);
    }

    // 注意方法 Element 加上 s
    public List<E> getAllElements() {
        return bst.inOrder();
    }

    public static void main(String[] args) {
        BSTSet<Integer> set = new BSTSet<>();
        set.add(2);
        set.add(1);
        set.add(9);
        set.add(5);
        set.add(2);
        System.out.println(set.getAllElements()); // [1, 2, 5, 9] 有序的 set 集合
    }
}
