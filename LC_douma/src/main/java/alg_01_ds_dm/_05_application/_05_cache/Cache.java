package alg_01_ds_dm._05_application._05_cache;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 16:56
 * @Version 1.0
 */

public interface Cache<K, V> {
    V get(K key);

    void put(K key, V value);
}
