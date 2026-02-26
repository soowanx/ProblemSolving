/*
bfs. 순서기록 -> 역순처리.
1. 그래프 생성
2. int[] -> long[] 변환
3. 각 노드의 부모 배열, 방문 순서 배열 생성
4. bfs
5. 역순 처리
*/

import java.util.*;

class Solution {
    public long solution(int[] a, int[][] edges) {
        // 그래프 생성
        int n = a.length;
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) list.add(new ArrayList<>());
        for (int[] edge : edges) {
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }
        
        // long 배열로 변환
        long[] val = new long[n];
        for (int i = 0; i < n; i++) val[i] = a[i];
        
        // 각 노드의 부모
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        
        // 방문 순서
        int[] order = new int[n];
        
        // bfs
        int idx = 0;
        boolean[] visited = new boolean[n];
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            order[idx++] = cur;
            
            for (int next : list.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    parent[next] = cur;
                    queue.add(next);
                }
            }
        }
        
        // 역순 처리
        long cost = 0;
        for (int i = idx - 1; i >= 1; i--) {
            int cur = order[i];
            int pa = parent[cur];
            long value = val[cur];
            
            cost += Math.abs(value);
            val[pa] += value;
        }
        
        return val[0] != 0 ? -1 : cost;
    }        
}