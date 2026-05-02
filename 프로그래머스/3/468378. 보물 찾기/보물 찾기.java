import java.util.function.Function;

class Solution {
    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
        int n = depth.length;
        
        // dp[l][r]: [l, r]에서 보물을 찾기 위한 최소 최악비용
        int[][] dp = new int[n][n];
        
        // choice[l][r]: [l, r]에서 가장 먼저 파야 하는 열
        int[][] choice = new int[n][n];
        
        // 길이 1짜리 구간 초기화
        for (int i = 0; i < n; i++) {
            dp[i][i] = depth[i];
            choice[i][i] = i;
        }
        
        // dp
        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;
                dp[l][r] = Integer.MAX_VALUE;
                
                // [l, r]에서 k를 먼저 파는 경우
                for (int k = l; k <= r; k++) {
                    int leftCost = (k > l) ? dp[l][k - 1] : 0;
                    int rightCost = (k < r) ? dp[k + 1][r] : 0;
                    
                    int cost = depth[k] + Math.max(leftCost, rightCost);
                    
                    if (cost < dp[l][r]) {
                        dp[l][r] = cost;
                        choice[l][r] = k;
                    }
                }
            }
        }

        // 땅 파기
        int l = 0;
        int r = n - 1;
        
        while (l <= r) {
            int col = choice[l][r];
            int result = excavate.apply(col + 1);
            
            if (result == -1) r = col - 1;
            else if (result == 1) l = col + 1;
            else return col + 1;
        }
        
        return -1;
    }
}