/*
list + 시간 처리 + sliding window

1. list<시작, 종료> 만들기
2. lines를 list에 넣기 + 시간처리
3. sliding window
*/

import java.util.*;

class Solution {
    public int solution(String[] lines) {
        List<int[]> list = new ArrayList<>();

        // list<시작, 종료>에 lines 넣기
        for (String line : lines) {
            StringTokenizer st = new StringTokenizer(line);
            
            // yyyy-mm-dd 버리기
            st.nextToken();
            
            // 응답 완료시간 S
            // 00:00:00.000 대비 s + 4
            // ms로 변환 -> double 소수점 오차 방지
            String S = st.nextToken();
            int h = Integer.parseInt(S.substring(0, 2));
            int m = Integer.parseInt(S.substring(3, 5));
            int s = (int) (Double.parseDouble(S.substring(6, 12)) * 1000) + 4;
            int endSecond = (h * 3600 + m * 60) * 1000 + s;
            
            // 처리시간 T
            String T = st.nextToken();
            int time = (int) (Double.parseDouble(T.substring(0, T.length() - 1)) * 1000);
            
            // 응답 시작시간 (second - time + 1ms)
            list.add(new int[] {endSecond - time + 1, endSecond});
        }
        
        // sliding window: list 순회하면서 1초 더한 값이 어디까지 가는지 카운트
        int max = 1;
        
        for (int i = 0; i < list.size() - 1; i++) {
            int count = 1;
            int limit = list.get(i)[1] + 1000 - 1;
            
            for (int j = i + 1; j < list.size(); j++) {
                if (limit >= list.get(j)[0]) count++;
            }
            
            max = Math.max(max, count);
        }
        
        return max;
    }
}