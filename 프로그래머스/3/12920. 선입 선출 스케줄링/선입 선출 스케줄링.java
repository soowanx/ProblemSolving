/*
이분탐색
1. 시간 t까지 처리 가능한 작업의 수 >= n 을 만족하는 최소 t 찾기
2. t-1 까지 처리한 작업의 수 구하기
3. 배열 순회하면서 남은 시간이 0인 코어 찾기
*/

import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        int answer = -1;

        // 이분탐색: 시간 t까지 처리 가능한 작업의 수가 n 이상이 되는 최소 t 찾기
        int left = 1;
        int right = 10000 * 10000;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // 처리 가능한 작업의 수 구하기
            int work = getWork(cores, mid);
            
            if (n <= work) right = mid;
            else left = mid + 1;
        }
        
        // t-1 까지 처리한 작업의 수
        int prev = getWork(cores, left - 1);
        int remain = n - prev;
        int done = 0;
        
        for (int i = 0; i < cores.length; i++) {
            if (left % cores[i] == 0) {
                done++;
                
                if (done == remain) return i + 1;
            }
        }
        
        return answer;
    }
    
    int getWork(int[] cores, int mid) {
        int work = cores.length;
        for (int i = 0; i < cores.length; i++) work += mid / cores[i];
        return work;
    }
}