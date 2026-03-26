/*
bfs. 값이 다른 노드가 1개만 있어야 함.
*/

import java.util.*;

class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];
        
        // 간선
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int node : nodes) graph.put(node, new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        // 방문 노드
        Set<Integer> visited = new HashSet<>();
        
        for (int start : nodes) {
            if (visited.contains(start)) continue;
            
            int sameCnt = 0;
            int diffCnt = 0;
            
            // bfs
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            visited.add(start);
            
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                int degree = graph.get(cur).size();
                
                if (cur % 2 == degree % 2) sameCnt++;
                else diffCnt++;
                
                for (int next : graph.get(cur)) {
                    if (visited.contains(next)) continue;
                    visited.add(next);
                    queue.add(next);
                }
            }
            
            // 판정이 다른 노드가 1개만 있어야 함. (루트 노드)
            if (sameCnt == 1) answer[0]++;
            if (diffCnt == 1) answer[1]++;
        }
        
        return answer;
    }
}