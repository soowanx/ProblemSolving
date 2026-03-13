/*
dp

1. 2차원 배열 생성
2. 순회하면서 임시 2차원 배열 생성
3. dp
4. 대각선 -> 직선
*/

import java.util.*;

class Solution {
    public int solution(String numbers) {
        int[][] dp = new int[10][10];
        
        for (int i = 0; i < 10; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        
        dp[4][6] = 0;
        
        for (char ch : numbers.toCharArray()) {
            int num = ch - '0';
            
            int[][] next = new int[10][10];
            for (int i = 0; i < 10; i++) Arrays.fill(next[i], Integer.MAX_VALUE);
            
            for (int l = 0; l < 10; l++) {
                for (int r = 0; r < 10; r++) {
                    if (dp[l][r] == Integer.MAX_VALUE) continue;
                    
                    // 왼손
                    if (num != r) next[num][r] = Math.min(next[num][r], dp[l][r] + getDist(l, num));
                    
                    // 오른손
                    if (num != l) next[l][num] = Math.min(next[l][num], dp[l][r] + getDist(r, num));
                }
            }
            
            dp = next;
        }
        
        int answer = Integer.MAX_VALUE;
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) answer = Math.min(answer, dp[i][j]);
        }
        
        return answer;
    }
    
    int getDist(int cur, int num) {
        if (cur == num) return 1;
        
        int r1 = cur == 0 ? 3 : (cur - 1) / 3, c1 = cur == 0 ? 1 : (cur - 1) % 3;
        int r2 = num == 0 ? 3 : (num - 1) / 3, c2 = num == 0 ? 1 : (num - 1) % 3;
        
        int dr = Math.abs(r2 - r1), dc = Math.abs(c2 - c1);
        
        return Math.min(dr, dc) * 3 + Math.abs(dr - dc) * 2;
    }
}