/*
treeset - higher
*/

import java.util.*;

class Solution {
    public int solution(int n, int[][] data) {
        int answer = 0;
        
        // x -> y 정렬
        Arrays.sort(data, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        
        TreeSet<Integer> set = new TreeSet<>();
        
        for (int i = 0; i < n - 1; i++) {
            set.clear();
            
            int j = i + 1;
            
            while (j < n) {
                // 마지막 j 찾기
                int tempJ = j;
                while (j < n && data[tempJ][0] == data[j][0]) j++;
                
                for (int k = tempJ; k < j; k++) {
                    int y1 = data[i][1];
                    int y2 = data[k][1];
                    
                    // 직사각형 넓이 > 0
                    if (data[i][0] != data[k][0] && y1 != y2) {
                        // 내부 탐색
                        Integer inside = set.higher(Math.min(y1, y2));
                        if (inside == null || inside >= Math.max(y1, y2)) answer++;
                    }
                }
                
                for (int k = tempJ; k < j; k++) {
                    if (data[k][0] != data[i][0]) set.add(data[k][1]);
                }
            }
        }
        
        return answer;
    }
}