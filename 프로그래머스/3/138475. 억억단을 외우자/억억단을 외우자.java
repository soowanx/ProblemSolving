/*
구간값 관리
1. 약수 개수 구하기
2. 약수 개수 최댓값과 최댓값일 때의 수 저장
*/

import java.util.*;

class Solution {
    public int[] solution(int e, int[] starts) {
        // 약수 개수 구하기
        int[] cnt = new int[e + 1];
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) cnt[j]++;
        }
        
        // 범위 dp
        int max = -1;
        int[] num = new int[e + 1];
        
        max = cnt[e];
        num[e] = e;
        
        for (int i = e - 1; i >= 1; i--) {
            if (cnt[i] >= max) {
                max = cnt[i];
                num[i] = i;
            } else {
                num[i] = num[i + 1];
            }
        }
        
        // return
        int[] answer = new int[starts.length];
        for (int i = 0; i < answer.length; i++) answer[i] = num[starts[i]];
        return answer;
    }
}