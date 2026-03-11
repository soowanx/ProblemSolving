/*
역추적

1. 범위 변수 생성
2. queries 뒤에서 순회
3. command에 따라 범위 변경
*/

import java.util.*;

class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long rowStart = x;
        long rowEnd = x;
        long colStart = y;
        long colEnd = y;
        
        for (int i = queries.length - 1; i >= 0; i--) {
            int command = queries[i][0];
            int dx = queries[i][1];
            
            if (command == 0) { // 열 감소
                colEnd = Math.min(colEnd + dx, m - 1);
                if (colStart != 0) colStart += dx;
            } else if (command == 1) { // 열 증가
                colStart = Math.max(colStart - dx, 0);
                if (colEnd != m - 1) colEnd -= dx;
            } else if (command == 2) { // 행 감소
                rowEnd = Math.min(rowEnd + dx, n - 1);
                if (rowStart != 0) rowStart += dx;
            } else if (command == 3) { // 행 증가
                rowStart = Math.max(rowStart - dx, 0);
                if (rowEnd != n - 1) rowEnd -= dx;
            }
            
            if (colStart > colEnd || rowStart > rowEnd) return 0;
        }
        
        return (rowEnd - rowStart + 1) * (colEnd - colStart + 1);
    }
}