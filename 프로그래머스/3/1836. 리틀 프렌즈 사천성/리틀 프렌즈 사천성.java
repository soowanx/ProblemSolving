/*
greedy
*/

import java.util.*;

class Solution {
    public String solution(int m, int n, String[] board) {
        // char 배열 생성 + 알파벳 위치 저장
        char[][] arr = new char[m][n];
        Map<Character, List<int[]>> alpha = new HashMap<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = board[i].charAt(j);
                
                if (arr[i][j] != '.' && arr[i][j] != '*') {
                    if (!alpha.containsKey(arr[i][j])) alpha.put(arr[i][j], new ArrayList<>());
                    alpha.get(arr[i][j]).add(new int[] {i, j});
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        // greedy
        while (!alpha.isEmpty()) {
            boolean removed = false;
            
            for (char ch = 'A'; ch <= 'Z'; ch++) {
                if (alpha.containsKey(ch) && canRemove(ch, arr, alpha)) {
                    // 보드에서 제거
                    for (int[] cur : alpha.get(ch)) arr[cur[0]][cur[1]] = '.';
                    
                    // map에서 제거
                    alpha.remove(ch);
                    
                    sb.append(ch);
                    removed = true;
                    break;
                }
            }
            
            if (!removed) return "IMPOSSIBLE";
        }
        
        return sb.toString();
    }
    
    boolean canRemove(char ch, char[][] arr, Map<Character, List<int[]>> alpha) {
        List<int[]> pos = alpha.get(ch);
        int r1 = pos.get(0)[0], c1 = pos.get(0)[1];
        int r2 = pos.get(1)[0], c2 = pos.get(1)[1];
        
        // 가로 -> 세로
        if (clearRow(ch, arr, r1, c1, c2) && clearCol(ch, arr, c2, r1, r2)) return true;
        
        // 세로 -> 가로
        if (clearCol(ch, arr, c1, r1, r2) && clearRow(ch, arr, r2, c1, c2)) return true;
        
        return false;
    }
    
    boolean clearRow(char ch, char[][] arr, int r, int c1, int c2) {
        int start = Math.min(c1, c2);
        int end = Math.max(c1, c2);
        
        for (int i = start; i <= end; i++) {
            if (arr[r][i] != '.' && arr[r][i] != ch) return false;
        }
        
        return true;
    }
    
    boolean clearCol(char ch, char[][] arr, int c, int r1, int r2) {
        int start = Math.min(r1, r2);
        int end = Math.max(r1, r2);
        
        for (int i = start; i <= end; i++) {
            if (arr[i][c] != '.' && arr[i][c] != ch) return false;
        }
        
        return true;
    }
}