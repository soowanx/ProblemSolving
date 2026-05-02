import java.util.function.Function;
import java.util.*;

class Solution {
    int[] depth, prefix;
    int[] memo;

    // [left, right] 구간에서 보물을 반드시 찾기 위한 최소 비용
    int minCost(int left, int right) {
        if (left == right) return depth[left - 1];
        
        // 좌표 변환 (2차원 -> 1차원)
        int key = left * 201 + right;
        
        if (memo[key] != -1) return memo[key];

        int best = Integer.MAX_VALUE;
        
        for (int i = left; i <= right; i++) {
            int leftCost = (i > left) ? minCost(left, i - 1) : 0;
            int rightCost = (i < right) ? minCost(i + 1, right) : 0;
            int cost = depth[i - 1] + Math.max(leftCost, rightCost);
            
            best = Math.min(best, cost);
        }
        
        return memo[key] = best;
    }

    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
        this.depth = depth;
        
        int n = depth.length;
        
        // 구간 비용 캐시
        memo = new int[201 * 201];
        Arrays.fill(memo, -1);

        int left = 1, right = n;
        
        while (left <= right) {
            int best = left;
            int bestCost = Integer.MAX_VALUE;
            
            for (int i = left; i <= right; i++) {
                int leftCost = (i > left) ? minCost(left, i - 1) : 0;
                int rightCost = (i < right) ? minCost(i + 1, right) : 0;
                int cost = depth[i - 1] + Math.max(leftCost, rightCost);
                
                if (cost < bestCost) {
                    bestCost = cost;
                    best = i;
                }
            }

            if (money < depth[best - 1]) return -1;
            money -= depth[best - 1];

            int res = excavate.apply(best);
            
            if (res == 0) return best;
            else if (res < 0) right = best - 1;
            else left = best + 1;
        }
        return -1;
    }
}