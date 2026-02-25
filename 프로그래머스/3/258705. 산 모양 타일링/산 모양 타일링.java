/*
dp
아래 삼각형 기준: 왼쪽 마름모, 오른쪽 마름모, 위쪽 마름모, 정삼각형 -> 4가지
a: 오른쪽 마름모
b: 위/왼쪽 마름모, 정삼각형
*/

import java.util.*;

class Solution {
    public int solution(int n, int[] tops) {
        int MOD = 10007;
        
        int[] a = new int[n + 1]; // 오른쪽 마름모
        int[] b = new int[n + 1]; // 위/왼쪽 마름모, 정삼각형
        
        // 초기 상태는 오른쪽 마름모로 끝나지 않았으므로 a[0] = 0, b[0] = 1
        a[0] = 0;
        b[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            if (tops[i - 1] == 1) { // 위쪽 마름모 + 정사각형 + 왼쪽 마름모 (오른쪽 마름모로 끝나지 않았을 때)
                b[i] = (2 * a[i - 1] + 3 * b[i - 1]) % MOD;
            } else { // 정사각형 + 왼쪽 마름모 (오른쪽 마름모로 끝나지 않았을 때)
                b[i] = (a[i - 1] + 2 * b[i - 1]) % MOD;
            }
            
            a[i] = (a[i - 1] + b[i - 1]) % MOD;
        }
        
        return (int) ((a[n] + b[n]) % MOD);
    }
}