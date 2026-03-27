/*

*/

import java.util.*;

class Solution {
    public int solution(int n, int[][] data) {
        int answer = 0;
        
        // x 오름차순 -> y 오름차순 정렬
        Arrays.sort(data, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (data[i][0] == data[j][0] || data[i][1] == data[j][1]) continue;
                
                boolean check = true;
                
                for (int k = i + 1; k < j; k++) {
                    if ((data[i][0] < data[k][0] && data[k][0] < data[j][0]) && 
                        (Math.min(data[i][1], data[j][1]) < data[k][1] && 
                        data[k][1] < Math.max(data[i][1], data[j][1]))) {
                        check = false;
                        break;
                    }
                }
                
                if (check) answer++;
            }
        }
        
        return answer;
    }
}