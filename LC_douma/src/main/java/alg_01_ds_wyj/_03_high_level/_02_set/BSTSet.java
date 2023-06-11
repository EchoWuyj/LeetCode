package alg_01_ds_wyj._03_high_level._02_set;

import alg_01_ds_wyj._02_tree._02_bst.BST;
import com.sun.webkit.graphics.ScrollBarTheme;

import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 11:59
 * @Version 1.0
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
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
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

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
        System.out.println(set.getAllElements()); // [1, 2, 5, 9]  有序的 set 集合
    }
}
