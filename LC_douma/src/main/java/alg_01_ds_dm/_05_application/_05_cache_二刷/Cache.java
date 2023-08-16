package alg_01_ds_dm._05_application._05_cache_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 16:56
 * @Version 1.0
 */

// Cache 自定义泛型
public interface Cache<K, V> {
    // 接口默认 public，故可以将 public 省略
    // 只是定义方法规范，具体实现，由接口的实现类去实现
    V get(K key);

    void put(K key, V value);
}
