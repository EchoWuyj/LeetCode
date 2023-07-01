package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 13:43
 * @Version 1.0
 */
public class _04_690_employee_importance3 {

    public class Employee {
        public int id;
        public int importance;
        List<Integer> subordinates;

    }

    private Map<Integer, Employee> map;

    public int getImportance(List<Employee> employees, int id) {
        map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return bfs(id);
    }

    public int bfs(int id) {
        Employee emp = map.get(id);
        if (emp == null) return 0;
        Queue<Employee> queue = new ArrayDeque<>();
        queue.offer(emp);
        int res = 0;
        while (!queue.isEmpty()) {
            Employee cur = queue.poll();
            res += cur.importance;
            for (int subId : cur.subordinates) {
                Employee subEmp = map.get(subId);
                if (subEmp != null) queue.offer(subEmp);
            }
        }
        return res;
    }
}
