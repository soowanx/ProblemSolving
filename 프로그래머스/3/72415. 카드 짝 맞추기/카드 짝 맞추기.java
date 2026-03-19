/*
완전탐색(카드 순서) + bfs(이동)

1. 카드 번호 찾아서 저장: HashMap
2. 카드 이동 순서 정하기: dfs
    - 남은 카드가 없으면 정답 갱신
    - 제거할 카드 번호 선택
3. 최소 이동 경로 찾기: bfs
    - 방향키
    - Ctrl + 방향키
*/

import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    Map<Integer, List<int[]>> map = new HashMap<>();
    
    public int solution(int[][] board, int r, int c) {
        // 카드 번호 모두 찾기
        map = new HashMap<>();
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = board[i][j];
                
                if (num != 0) {
                    if (!map.containsKey(num)) map.put(num, new ArrayList<>());
                    map.get(num).add(new int[] {i, j});
                }
            }
        }
        
        // 카드 순서 정하기
        dfs(board, r, c, 0, map.size());
        
        return answer;
    }
    
    void dfs (int[][] board, int r, int c, int count, int size) {
        if (count >= answer) return;
        
        // 남은 카드가 없으면 정답 갱신
        if (size == 0) {
            answer = Math.min(answer, count);
            return;
        }
        
        // 제거할 카드 번호 선택
        for (int num : map.keySet()) {
            List<int[]> list = map.get(num);
            int[] a = list.get(0);
            int[] b = list.get(1);
            
            // 이미 제거됐으면 건너뛰기
            if (board[a[0]][a[1]] == 0) continue;
            
            // a -> b
            int d1 = bfs(board, r, c, a[0], a[1]);
            int d2 = bfs(board, a[0], a[1], b[0], b[1]);
            
            board[a[0]][a[1]] = 0;
            board[b[0]][b[1]] = 0;
            dfs(board, b[0], b[1], count + d1 + d2 + 2, size - 1);
            board[a[0]][a[1]] = num;
            board[b[0]][b[1]] = num;
            
            // b -> a
            int d3 = bfs(board, r, c, b[0], b[1]);
            int d4 = bfs(board, b[0], b[1], a[0], a[1]);
            
            board[a[0]][a[1]] = 0;
            board[b[0]][b[1]] = 0;
            dfs(board, a[0], a[1], count + d3 + d4 + 2, size - 1);
            board[a[0]][a[1]] = num;
            board[b[0]][b[1]] = num;
        }
    }
    
    int bfs (int[][] board, int r1, int c1, int r2, int c2) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[4][4];
        
        queue.add(new int[] {r1, c1, 0});
        visited[r1][c1] = true;
        
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int cnt = cur[2];
            
            if (r == r2 && c == c2) return cnt;
            
            // 방향키
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (nr < 0 || nc < 0 || nr > 3 || nc > 3 || visited[nr][nc]) continue;
                
                queue.add(new int[] {nr, nc, cnt + 1});
                visited[nr][nc] = true;
            }
            
            // Ctrl + 방향키
            for (int i = 0; i < 4; i++) {
                int nr = r;
                int nc = c;
                
                while (true) {
                    int tr = nr + dr[i];
                    int tc = nc + dc[i];
                    
                    if (tr < 0 || tc < 0 || tr > 3 || tc > 3) break;
                    
                    nr = tr;
                    nc = tc;
                    
                    if (board[nr][nc] != 0) break;
                }
                
                if (visited[nr][nc]) continue;

                queue.add(new int[] {nr, nc, cnt + 1});
                visited[nr][nc] = true;
            }
        }
        
        return 0;
    }
}