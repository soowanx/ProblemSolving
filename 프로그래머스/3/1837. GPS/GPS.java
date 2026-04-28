import java.util.*;

class Solution {
    int INF = 100;
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        // 그래프 생성
        List<List<Integer>> graph = new ArrayList<>();
        
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edge_list) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        for (int i = 1; i <= n; i++) {
            graph.get(i).add(i);
        }
        
        // dp (time, node)
        int[][] dp = new int[k][n + 1];
        
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        int start = gps_log[0];
        int end = gps_log[k - 1];
        
        dp[0][start] = 0;
        
        for (int t = 1; t < k; t++) {
            for (int node = 1; node < n + 1; node++) {
                for (int next : graph.get(node)) {
                    if (dp[t - 1][node] == INF) {
                        continue;
                    }
                    
                    dp[t][next] = Math.min(dp[t][next], dp[t - 1][node] + (next == gps_log[t] ? 0 : 1));
                }
            }
        }
        
        return dp[k - 1][end] == 100 ? -1 : dp[k - 1][end];
    }
}
