/*
dp
*/

import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        // 최댓값 구하기
        int maxAlp = -1;
        int maxCop = -1;
        
        for (int i = 0; i < problems.length; i++) {
            if (maxAlp < problems[i][0]) maxAlp = problems[i][0];
            if (maxCop < problems[i][1]) maxCop = problems[i][1];
        }
        
        // dp 배열 생성
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
            
        // dp 초기화
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) dp[i][j] = Integer.MAX_VALUE;
        }
        
        dp[alp][cop] = 0;
        
        // dp
        for (int a = alp; a <= maxAlp; a++) {
            for (int c = cop; c <= maxCop; c++) {
                // 알고력
                if (a < maxAlp) dp[a + 1][c] = Math.min(dp[a + 1][c], dp[a][c] + 1);
                
                // 코딩력
                if (c < maxCop) dp[a][c + 1] = Math.min(dp[a][c + 1], dp[a][c] + 1);
                
                // 문제 풀이
                for (int p = 0; p < problems.length; p++) {
                    if (problems[p][0] <= a && problems[p][1] <= c) {
                        int newA = Math.min(a + problems[p][2], maxAlp);
                        int newC = Math.min(c + problems[p][3], maxCop);
                        dp[newA][newC] = Math.min(dp[newA][newC], dp[a][c] + problems[p][4]);
                    }
                }
            }
        }
        
        return dp[maxAlp][maxCop];
    }
}