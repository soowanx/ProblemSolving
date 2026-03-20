/*
진법 변환

10진수 -> n진수: Integer.toString(int num, int radix)
n진수 -> 10진수: Integer.parseInt(String str, int radix)
*/

import java.util.*;

class Solution {
    List<String> list;
    
    public String[] solution(String[] expressions) {
        list = new ArrayList<>();
        
        // 가능한 진법 배열 (2 ~ 9)
        boolean[] possible = new boolean[10];
        for (int i = 2; i <= 9; i++) possible[i] = true;

        // 가능한 진법 구하기
        for (String expression : expressions) {
            boolean isX = expression.charAt(expression.length() - 1) == 'X';
            
            StringTokenizer st = new StringTokenizer(expression, " ");
            String a = st.nextToken();
            char op = st.nextToken().charAt(0);
            String b = st.nextToken();
            st.nextToken();
            String c = st.nextToken();
            
            // a, b, c 중 가장 큰 한 자리 수
            StringBuilder sb = new StringBuilder();
            
            if (isX) sb.append(a).append(b);
            else sb.append(a).append(b).append(c);
            
            int maxNum = -1;
            for (char ch : sb.toString().toCharArray()) maxNum = Math.max(maxNum, (ch - '0'));
            for (int i = 2; i <= maxNum; i++) possible[i] = false;
            
            // 진법 변환 후 계산
            for (int i = 2; i <= 9; i++) {
                if (!isX && possible[i] && !isSame(a, b, c, op, i)) possible[i] = false;
            }
        }
        
        // X 구하기
        for (String expression : expressions) {
            if (expression.charAt(expression.length() - 1) != 'X') continue;
            
            StringTokenizer st = new StringTokenizer(expression, " ");
            String a = st.nextToken();
            char op = st.nextToken().charAt(0);
            String b = st.nextToken();
            
            // 계산
            Set<String> result = new HashSet<>();
            
            for (int i = 2; i <= 9; i++) {
                if (possible[i]) result.add(Integer.toString(cal(a, b, op, i), i));
            }
            
            if (result.size() == 1) makeAnswer(a, b, result.iterator().next(), op);
            else makeAnswer(a, b, "?", op);
        }
        
        String[] answer = new String[list.size()];
        for (int i = 0; i < list.size(); i++) answer[i] = list.get(i);
        return answer;
    }
    
    void makeAnswer(String a, String b, String c, char op) {
        StringBuilder sb = new StringBuilder();
        sb.append(a).append(" ").append(op).append(" ").append(b).append(" = ").append(c);
        list.add(sb.toString());
    }
    
    int cal (String a, String b, char op, int radix) {
        int numA = Integer.parseInt(a, radix);
        int numB = Integer.parseInt(b, radix);
        
        return op == '+' ? numA + numB : numA - numB;
    }
    
    boolean isSame (String a, String b, String c, char op, int radix) {
        int numA = Integer.parseInt(a, radix);
        int numB = Integer.parseInt(b, radix);
        int numC = Integer.parseInt(c, radix);
        
        return op == '+' ? numA + numB == numC : numA - numB == numC;
    }
}