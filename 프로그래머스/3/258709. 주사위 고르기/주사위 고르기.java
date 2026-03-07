/*
dfs (조합, 합 생성), 이분탐색 (승리 수 계산)
1. 
2. 
3. 
*/

import java.util.*;

class Solution {
    int n;
    int[] answer;
    int idx;
    int count = 0;
    
    public int[] solution(int[][] dice) {
        n = dice.length;
        answer = new int[n / 2];
        
        // 조합 구하기 (dfs)
        getCombination(dice, new boolean[n], new ArrayList<>(), 0);
        
        return answer;
    }
    
    // 조합
    void getCombination(int[][] dice, boolean[] visited, List<Integer> listA, int start) {
        if (listA.size() == n / 2) {
            // listB 구하기
            List<Integer> listB = new ArrayList<>();
            for (int i = 0; i < n; i++) if (!visited[i]) listB.add(i);
            
            int win = countWin(dice, listA, listB);
            
            if (win > count) {
                count = win;
                for (int i = 0; i < listA.size(); i++) answer[i] = listA.get(i) + 1;
            }
        }
        
        // dfs
        for (int i = start; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                listA.add(i);
                
                getCombination(dice, visited, listA, i + 1);
                
                listA.remove(listA.size() - 1);
                visited[i] = false;
            }
        }
    }
    
    // 승리 횟수
    int countWin(int[][] dice, List<Integer> listA, List<Integer> listB) {
        int len = listA.size();
        
        int[] arrA = new int[(int) Math.pow(6, len)];
        int[] arrB = new int[(int) Math.pow(6, len)];
        
        idx = 0;
        dfs(arrA, dice, listA, 0, 0);
        
        idx = 0;
        dfs(arrB, dice, listB, 0, 0);
        
        Arrays.sort(arrA);
        Arrays.sort(arrB);
        
        // 승리 횟수 구하기. 이분탐색
        int win = 0;
        
        for (int a : arrA) {
            int start = 0;
            int end = arrB.length - 1;
            
            while (start <= end) {
                int mid = start + (end - start) / 2;
                
                if (arrB[mid] < a) start = mid + 1;
                else end = mid - 1;
            }
            
            win += start;
        }
        
        return win;
    }
    
    // 합 구하기
    void dfs(int[] arr, int[][] dice, List<Integer> list, int depth, int sum) {
        if (depth == list.size()) {
            arr[idx++] = sum;
            return;
        }
        
        int diceIndex = list.get(depth);
        
        for (int i = 0; i < 6; i++) dfs(arr, dice, list, depth + 1, sum + dice[diceIndex][i]);
    }
}