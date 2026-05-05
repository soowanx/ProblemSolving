class Solution {
    public int solution(int n) {
        int MOD = 1_000_000_007;
        long[] dp = new long[n + 1];
        
        dp[0] = 1;
        dp[1] = 1;
        if (n >= 2) dp[2] = 3;
        if (n >= 3) dp[3] = 10;
        
        long sumAll = 0;
        long[] extraSum = new long[3];
        
        for (int i = 4; i <= n; i++) {
            sumAll = (sumAll + dp[i - 4]) % MOD;
            
            if (i >= 6) {
                extraSum[i % 3] = (extraSum[i % 3] + dp[i - 6]) % MOD;
            }
            
            dp[i] = (dp[i - 1] + dp[i - 2] * 2 + dp[i - 3] * 5 + 2 * sumAll + 2 * extraSum[i % 3]) % MOD;
        }
        
        return (int) dp[n];
    }
}