package alg_02_train_dm._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-28 15:10
 * @Version 1.0
 */
public class _06_Cache_Note {
    /*
        缓存数据库中的数据结构
            1.缓存失效策略
                1.1 FIFO(First In First Out)
                1.2 leetcode 146号算法题：LRU（Least Recently Used）
                1.3 leetcode 460号算法题：LFU（Least Frequently Used）
            2.Redis 中的数据结构
                2.1 字符串
                2.2 列表
                2.3 集合
                2.4 有序集合 -> 跳表

        详细内容，看 PPT

        缓存概念：
        为了提升数据的访问性能，我们选择将数据放置在内存中，存放数据的内存我们称为缓存
        一个是存放数据 put 操作，将（key，value）键值对放入缓存
        一个是获取数据 get 操作，根据 key 从缓存中获取 value

        缓存数据淘汰机制：
        缓存(内存)有限的，当缓存达到上限，应该淘汰一些缓存中的数据

        1.淘汰最先放入缓存的数据，即 FIFO (First In First Out) 缓存
        2.淘汰最久未使用的数据，即 LRU(Least Recently Used) 缓存
        3.淘汰最不频繁使用的数据，即 LFU(Least Frequently Used) 缓存

        详细内容见：_05_cache
     */
}
