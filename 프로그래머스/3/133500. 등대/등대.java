/*
트리 최소 정점 커버 문제: dp + dfs

*/

import java.util.*;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        // 그래프 생성
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) list.add(new ArrayList<>());
        for (int[] light : lighthouse) {
            list.get(light[0]).add(light[1]);
            list.get(light[1]).add(light[0]);
        }
        
        // dfs
        int[] result = dfs(1, 0, list);
        
        return Math.min(result[0], result[1]);
    }
    
    int[] dfs(int cur, int parent, List<List<Integer>> list) {
        // 끄면 0, 켜면 1
        int off = 0;
        int on = 1;
        
        for (int next : list.get(cur)) {
            if (next == parent) continue;
            
            int[] child = dfs(next, cur, list);
            
            off += child[1]; // 내가 꺼져 있으면? 자식은 켜져야 함. 
            on += Math.min(child[0], child[1]); // 내가 켜져 있으면? 자식은 자유 (더 작은 값 선택)
        }
        
        return new int[] {off, on};
    }
}