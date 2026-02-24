/*
비트마스크
1. 모든 행 뒤집기 조합 탐색
2. bitCount로 뒤집은 행 개수 계산
3. 첫 행 기준으로 열 뒤집기 자동 결정
4. 전체 (i, j) 검사
5. 다 맞으면 행+열 개수 최솟값 갱신
*/

import java.util.*;

class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int answer = Integer.MAX_VALUE;
        int r = target.length;
        int c = target[0].length;
        
        for (int mask = 0; mask < (1<<r); mask++) {
            // 뒤집은 행 개수 계산. 1이면 뒤집고 0이면 그대로
            int rowCount = Integer.bitCount(mask);
            boolean possible = true;
            
            int[] col = new int[c];
            
            // 열 뒤집기 결정 (첫 행 기준)
            for (int j = 0; j < c; j++) col[j] = beginning[0][j] ^ target[0][j] ^ (mask & 1);
            
            // 검증
            for (int i = 0; i < r && possible; i++) {
                for (int j = 0; j < c; j++) {
                    int value = beginning[i][j] ^ col[j] ^ ((mask >> i) & 1);
                    
                    if (value != target[i][j]) {
                        possible = false;
                        break;
                    }
                }
            }
            
            // 최솟값 갱신
            if (possible) {
                int colCount = 0;
                for (int j = 0; j < c; j++) if (col[j] == 1) colCount++;
                answer = Math.min(answer, rowCount + colCount);
            }
        }
        
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}