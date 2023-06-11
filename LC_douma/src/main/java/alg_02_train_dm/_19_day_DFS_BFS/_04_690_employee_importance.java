package alg_02_train_dm._19_day_DFS_BFS;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:02
 * @Version 1.0
 */
public class _04_690_employee_importance {
     /*
         690. 员工的重要性
         给定一个保存员工信息的数据结构，它包含了员工 唯一的 id ，重要度 和 直系下属的 id 。

         比如，员工 1 是员工 2 的领导，员工 2 是员工 3 的领导。他们相应的重要度为 15 , 10 , 5 。
         那么员工 1 的数据结构是 [1, 15, [2]] ，
         员工 2的 数据结构是 [2, 10, [3]] ，
         员工 3 的数据结构是 [3, 5, []] 。
         员工 4 的数据结构是 [4, 2, []] 。
         注意虽然员工 3 也是员工 1 的一个下属，但是由于 并不是直系 下属，因此没有体现在员工 1 的数据结构中。

         现在输入一个公司的所有员工信息，以及单个员工 id ，返回这个员工和他所有下属的重要度之和。

         示例：
         输入：[[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
         输出：5 + 3 + 3 = 11

         员工 1 自身的重要度是 5 ，他有两个直系下属 2 和 3 ，而且 2 和 3 的重要度均为 3 。
         因此员工 1 的总重要度是 5 + 3 + 3 = 11 。

         提示：
         一个员工最多有一个 直系 领导，但是可以有多个 直系 下属
         员工数量不超过 2000 。

         KeyPoint 思路
         分析业务场景 => 抽象：一种或多种数据结构，数据结构相关问题 => 基于数据结构，使用相应的算法解决
         本题 => 抽象：N 叉树，N 叉树遍历 => DFS 或 BFS
     */

    public class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    // key id
    // value Employee
    private Map<Integer, Employee> map = new HashMap<>();
    private int res = 0;

    // KeyPoint 方法一 前序遍历
    public int getImportance1(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        dfs1(id);
        return res;
    }

    // 前序遍历
    private void dfs1(int id) {
        Employee emp = map.get(id);
        if (emp == null) return;

        // 前序遍历：遍历子节点之前，将父节点 emp 重要性值累加到 res，再去遍历子节点，累加重要性值
        res += emp.importance;
        for (int subordinateId : emp.subordinates) {
            dfs1(subordinateId);
        }
    }

    // KeyPoint 方法二 后序遍历
    public int getImportance2(List<Employee> employees, int id) {
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        return dfs(id);
    }

    // 后序遍历
    // => 从子节点返回上层节点，需要携带着统计信息，给上层使用
    // => 故递归函数有返回值
    private int dfs(int id) {
        Employee emp = map.get(id);
        if (emp == null) return 0;

        // 区别
        // 1.前序遍历：遍历子节点之前，将父节点 emp 重要性值累加到 res；再去遍历子节点，累加重要性值
        // 2.后序遍历：在遍历父节点 emp 之前，先遍历子节点，累加重要性值到 res；之后再加上父节点 emp 重要性值

        // 记录子节点重要性值
        int total = 0;
        for (int subordinateId : emp.subordinates) {
            // 将所有子节点重要性累和
            total += dfs(subordinateId);
        }

        // 自身重要性 + 子树重要性
        return emp.importance + total;
    }

    // KeyPoint 方法三 BFS
    public int getImportance(List<Employee> employees, int id) {
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        return bfs(id);
    }

    private int bfs(int id) {
        Employee emp = map.get(id);
        if (emp == null) return 0;

        int res = 0;
        Queue<Employee> queue = new ArrayDeque<>();
        queue.offer(emp);
        while (!queue.isEmpty()) {
            Employee cur = queue.poll();
            res += cur.importance;
            // 若当个测试用例，都出现超出内存限制，必然是代码有问题，一般都是 for 循环中问题
            // for 循环，使用 cur.subordinates，不是 emp.subordinates
            for (int subordinateId : cur.subordinates) {
                queue.offer(map.get(subordinateId));
            }
        }
        return res;
    }
}
