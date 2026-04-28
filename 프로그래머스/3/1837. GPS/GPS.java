import java.util.*;

class Solution {
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        // 그래프 생성
        List<List<Integer>> graph = new ArrayList<>();
        
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edge_list) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[0]).add(edge[0]);
            graph.get(edge[1]).add(edge[0]);
            graph.get(edge[1]).add(edge[1]);
        }
        
        // dp (time, node)
        int[][] dp = new int[k + 1][n + 1];
        
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], 100);
        }
        
        int start = gps_log[0];
        int end = gps_log[k - 1];
        
        dp[1][start] = 0;
        
        for (int t = 1; t < k + 1; t++) {
            for (int node = 1; node < n + 1; node++) {
                for (int next : graph.get(node)) {
                    dp[t][next] = Math.min(dp[t][next], dp[t - 1][node] + (next == gps_log[t - 1] ? 0 : 1));
                }
            }
        }
        
        return dp[k][end] == 100 ? -1 : dp[k][end];
    }
}

/*
자주 탑승하는 위치 추천
승차 위치, 하차 위치 오류 없음
수집한 이동 경로의 오류를 최소한으로 수정 -> 더 정확한 이동 경로 구하기
택시 이동
    거점
    도로
    대기 or 백트래킹
    왕복도로
return 이동 가능한 경로로 만드는 최소 오류 수정 횟수 (불가능하면 -1)

거점 개수 n
    2 <= n <= 200
도로 개수 m
    1 <= m <= 10,000
도로 정보 edge_list
    mx2 2차원 배열
    거점 번호: 1 ~ n
시간대별 거점 정보 총 개수 k
    2 <= k <= 100
머물렀던 거점 정보 gps_log
*/