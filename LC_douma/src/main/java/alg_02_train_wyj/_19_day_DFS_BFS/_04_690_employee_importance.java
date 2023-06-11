package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 15:02
 * @Version 1.0
 */
public class _04_690_employee_importance {

    public class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    Map<Integer, Employee> map = new HashMap<>();
    int res = 0;

    public int getImportance1(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        dfs(id);
        return res;
    }

    public void dfs(int id) {
        Employee employee = map.get(id);
        if (employee == null) return;
        res += employee.importance;
        for (int subordinateId : employee.subordinates) {
            dfs(subordinateId);
        }
    }

    public int getImportance2(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return dfs1(id);
    }

    public int dfs1(int id) {
        Employee employee = map.get(id);
        if (employee == null) return 0;
        int total = 0;
        for (int subordinateId : employee.subordinates) {
            total += dfs1(subordinateId);
        }
        return employee.importance + total;
    }

    public int getImportance(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return bfs(id);
    }

    private int bfs(int id) {
        Employee employee = map.get(id);
        if (employee == null) return 0;
        int res = 0;
        Queue<Employee> queue = new ArrayDeque<>();
        queue.offer(employee);
        while (!queue.isEmpty()) {
            Employee cur = queue.poll();
            res += cur.importance;
            for (int subordinateId : cur.subordinates) {
                queue.offer(map.get(subordinateId));
            }
        }
        return res;
    }
}
