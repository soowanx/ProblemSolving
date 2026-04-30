import java.util.*;

class Solution {
    int t1, t2;
    int[] onboard;
    int[][] dp;
    int offset;
    
    void update(int t, int next, int cost) {
        if (next < -10 || next > 40) return;
        if (onboard[t] == 1 && (next < t1 || next > t2)) return;
        
        int nextIdx = next + offset;
        dp[t][nextIdx] = Math.min(dp[t][nextIdx], cost);
    }
    
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        this.t1 = t1;
        this.t2 = t2;
        this.onboard = onboard;
        
        int len = onboard.length;
        
        offset = 10;
        dp = new int[len][51];
        
        // 초기 상태
        for (int i = 0; i < len; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        dp[0][temperature + offset] = 0;
        
        // dp
        for (int t = 1; t < len; t++) {
            for (int temp = -10; temp <= 40; temp++) {
                int prevIdx = temp + offset;
                if (dp[t - 1][prevIdx] == Integer.MAX_VALUE) continue;
                
                int prevCost = dp[t - 1][prevIdx];
                
                // 1. off
                int next = temp;
                
                // 외부온도 방향으로 1도 이동
                if (temp > temperature) next--;
                else if (temp < temperature) next++;
                
                update(t, next, prevCost);
                
                // on + 유지
                update(t, temp, prevCost + b);
                
                // on + 변화 (temp - 1, temp + 1)
                update(t, temp - 1, prevCost + a);
                update(t, temp + 1, prevCost + a);
            }
        }
        
        
        int answer = Integer.MAX_VALUE;
        for (int t = -10; t <= 40; t++) answer = Math.min(answer, dp[len - 1][t + offset]);
        return answer;
    }
}