import java.util.*;

class Solution {
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    
    int n;
    int m;
    
    void dfs(int[][] board, int target, boolean[][] visited, int r, int c, List<int[]> list) {
        list.add(new int[] {r, c});
        visited[r][c] = true;
        
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc] || board[nr][nc] != target) {
                continue;
            }
            
            dfs(board, target, visited, nr, nc, list);
        }
    }
    
    void change(List<int[]> list) {
        int minR = 51;
        int minC = 51;
        
        for (int[] l : list) {
            minR = Math.min(minR, l[0]);
            minC = Math.min(minC, l[1]);
        }
        
        for (int i = 0; i < list.size(); i++) {
            list.get(i)[0] -= minR;
            list.get(i)[1] -= minC;
        }
    }
    
    void rotate(List<int[]> list) {
        for (int i = 0; i < list.size(); i++) {
            int[] cur = list.get(i);
            
            int r = cur[0];
            int c = cur[1];
            
            list.get(i)[0] = c;
            list.get(i)[1] = -r;
        }
    }
    
    boolean isSame(List<int[]> e, List<int[]> p) {
        int size = e.size();
        int count = 0;
        
        e.sort((a, b) -> {
            return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
        });
        
        p.sort((a, b) -> {
            return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
        });
        
        for (int i = 0; i < size; i++) {
            if (e.get(i)[0] != p.get(i)[0] || e.get(i)[1] != p.get(i)[1]) {
                return false;
            }
        }
        
        return true;
    }
    
    public int solution(int[][] game_board, int[][] table) {
        n = table.length;
        m = table[0].length;
        
        // 퍼즐 조각 찾기
        List<List<int[]>> puzzle = new ArrayList<>();
        boolean[][] visited = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && table[i][j] == 1) {
                    List<int[]> p = new ArrayList<>();
                    dfs(table, 1, visited, i, j, p);
                    puzzle.add(p);
                }
            }
        }
        
        
        // 보드 빈 칸 찾기
        List<List<int[]>> empty = new ArrayList<>();
        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], false);
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && game_board[i][j] == 0) {
                    List<int[]> e = new ArrayList<>();
                    dfs(game_board, 0, visited, i, j, e);
                    empty.add(e);
                }
            }
        }
        
        // 퍼즐 조각 회전 -> 정규화 -> empty 비교
        int count = 0;
        boolean[] used = new boolean[puzzle.size()];
        
        for (List<int[]> e : empty) {
            boolean success = false;
            change(e);
            
            for (int idx = 0; idx < puzzle.size(); idx++) {
                if (used[idx]) {
                    continue;
                }
                
                List<int[]> p = puzzle.get(idx);
                if (e.size() != p.size()) {
                    continue;
                }
                
                List<int[]> tempP = new ArrayList<>();
                for (int i = 0; i < p.size(); i++) {
                    tempP.add(new int[] {p.get(i)[0], p.get(i)[1]});
                }
                
                change(tempP);
                
                for (int i = 0; i < 4; i++) {
                    if (i != 0) {
                        rotate(tempP);
                        change(tempP);
                    }
                    
                    if (isSame(e, tempP)) {
                        used[idx] = true;
                        success = true;
                        count += e.size();
                        break;
                    }
                }
                
                if (success) {
                    break;
                }
            }
        }
        
        return count;
    }
}
