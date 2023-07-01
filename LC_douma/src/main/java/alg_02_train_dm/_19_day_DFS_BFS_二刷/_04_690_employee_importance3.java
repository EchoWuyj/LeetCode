package alg_02_train_dm._19_day_DFS_BFS_二刷;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 12:49
 * @Version 1.0
 */
public class _04_690_employee_importance3 {

    public class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    // key id
    // value Employee
    private Map<Integer, Employee> map;

    // KeyPoint 方法三 BFS
    public int getImportance(List<Employee> employees, int id) {
        map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
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
            // 若单个测试用例，都出现超出内存限制，必然是代码有问题
            // 出现了死循环，一般都是 for 循环中问题
            // 注意：用 cur.subordinates，而不是 emp.subordinates
            for (int subId : cur.subordinates) {
                Employee subEmp = map.get(subId);
                if (subEmp != null) queue.offer(subEmp);
            }
        }
        return res;
    }
}
