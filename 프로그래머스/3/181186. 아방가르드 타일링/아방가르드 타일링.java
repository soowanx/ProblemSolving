import java.util.*;

class Solution {
    public int solution(int n) {
        if (n == 1) return 1;
        else if (n == 2) return 3;
        else if (n == 3) return 10;
        
        int MOD = 1_000_000_007;
        long[] dp = new long[n + 1];
        
        // dp[0]부터 dp[i - 4]까지 누적
        long sumAll = 0;
        long[] sumMod = new long[3];
        
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        
        for (int i = 4; i <= n; i++) {
            sumAll = (sumAll + dp[i - 4]) % MOD;
            
            if (i >= 6) {
                sumMod[(i - 6) % 3] = (sumMod[(i - 6) % 3] + dp[i - 6]) % MOD;
            }
            
            // 세로 타일 1개
            // 세로 타일로 모두 채우는 경우를 제외하고, 나머지 2칸을 채우는 경우의 수: 2
            // 가로로 눕혀진 5개
            dp[i] = (dp[i] + dp[i - 1] + 2 * dp[i - 2] + 5 * dp[i - 3]) % MOD;
            
            // 특수패턴
            dp[i] = (dp[i] + 2 * sumAll + 2 * sumMod[i % 3]) % MOD;
        }
        
        return (int) dp[n];
    }
}
