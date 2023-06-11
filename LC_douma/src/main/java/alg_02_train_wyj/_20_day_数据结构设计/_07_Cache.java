package alg_02_train_wyj._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-29 23:17
 * @Version 1.0
 */
public interface _07_Cache<K, V> {
    V get(K key);

    void put(K key, V value);

}
