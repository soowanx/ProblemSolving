/*
이분탐색 + 최대 양
*/

import java.util.*;

class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        int n = g.length;
        
        long start = 0;
        // 트럭 1대 + 옮길 수 있는 양 1 + 금, 은 10^9, 왕복 시간 2 * 10^5 = 4 * 10^5 * 10^9
        long end = 4 * (long) Math.pow(10, 14);
        
        while (start < end) {
            long gold = 0;
            long silver = 0;
            long total = 0;
            
            long mid = start + (end - start) / 2;
            
            // mid 시간동안 운반할 수 있는 광물
            for (int i = 0; i < n; i++) {
                long count = mid / (2L * t[i]);
                if (mid % (2L * t[i]) >= t[i]) count++;
                
                long canMove = w[i] * count;
                
                gold += Math.min((long) g[i], canMove);
                silver += Math.min((long) s[i], canMove);
                total += Math.min((long) g[i] + s[i], canMove);
            }
            
            if (gold >= a && silver >= b && total >= a + b) end = mid;
            else start = mid + 1;
        }
        
        return start;
    }
}