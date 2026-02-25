/*
dp. dp[start][end] = min(dp[start][mid] + dp[mid+1][end] + m[start][0] * m[mid][1] * m[end][1])
*/

import java.util.*;

class Solution {
    public int solution(int[][] matrix_sizes) {
        int count = 0;
        
        // 배열 생성
        int n = matrix_sizes.length;
        int[][] dp = new int[n][n];
        
        // 길이 len
        for (int len = 2; len <= n; len++) {
            // 시작 구간
            for (int start = 0; start <= n - len; start++) {
                int end = start + len - 1;
                dp[start][end] = Integer.MAX_VALUE;
                
                // 나누는 구간
                for (int mid = start; mid < end; mid++) {
                    int cost = dp[start][mid] + 
                        dp[mid + 1][end] + 
                        matrix_sizes[start][0] * matrix_sizes[mid][1] * matrix_sizes[end][1];
                
                    dp[start][end] = Math.min(dp[start][end], cost);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}