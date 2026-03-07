/*
1. 조합 구하기 (dfs)
2. 승리할 확률이 크면 해당 조합 저장
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
        boolean[] visited = new boolean[n];
        List<Integer> listA = new ArrayList<>();
        
        getCombination(dice, visited, listA, 0);
        
        return answer;
    }
    
    void getCombination(int[][] dice, boolean[] visited, List<Integer> listA, int start) {
        if (listA.size() == n / 2) {
            // listB 구하기
            List<Integer> listB = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (!visited[i]) listB.add(i);
            }
            
            int win = countWin(dice, listA, listB);
            
            if (win > count) {
                count = win;
                for (int i = 0; i < listA.size(); i++) {
                    answer[i] = listA.get(i) + 1;
                }
            }
        }
        
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
        
        int win = 0;
        
        for (int a : arrA) {
            for (int b : arrB) {
                if (a > b) win++;
                else break;
            }
        }
        
        return win;
    }
    
    void dfs(int[] arr, int[][] dice, List<Integer> list, int depth, int sum) {
        if (depth == list.size()) {
            arr[idx++] = sum;
            return;
        }
        
        int diceIndex = list.get(depth);
        
        for (int i = 0; i < 6; i++) {
            dfs(arr, dice, list, depth + 1, sum + dice[diceIndex][i]);
        }
    }
}