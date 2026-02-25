/*
dp
*/

import java.util.*;

class Solution {
    public int solution(int n, int[] tops) {
        int MOD = 10007;
        
        int[] a = new int[n + 1]; // 오른쪽 마름모
        int[] b = new int[n + 1]; // 위/왼쪽 마름모 + 정삼각형
        
        a[0] = 1;
        b[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            if (tops[i - 1] == 0) {
                a[i] = (2 * a[i - 1] + b[i - 1]) % MOD;
                b[i] = (a[i - 1] + b[i - 1]) % MOD;
            } else {
                a[i] = (3 * a[i - 1] + 2 * b[i - 1]) % MOD;
                b[i] = (a[i - 1] + b[i - 1]) % MOD;
            }
        }
        
        return (int) ((a[n] + b[n]) % MOD);
    }
}