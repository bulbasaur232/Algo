import java.util.*;

class Solution {
    
    int[][] map;
    
    public int solution(int[][] maps) {
        map = maps;
        return bfs();
    }
    
    int bfs(){
        int[] my = { -1, 1, 0, 0};
        int[] mx = {0, 0, -1, 1};
        Queue<Point> q = new ArrayDeque();
        boolean[][] visited = new boolean[map.length][map[0].length];
        q.offer(new Point(0, 0, 1));
        
        while(!q.isEmpty()){
            Point p = q.poll();
            
            for(int i = 0; i < 4; i++){
                int ny = p.y + my[i];
                int nx = p.x + mx[i];
                
                if(ny == map.length - 1 && nx == map[0].length - 1){
                    return p.cnt + 1;
                }
                
                if(isSafe(ny, nx) && map[ny][nx] == 1 && !visited[ny][nx]){
                    q.offer(new Point(ny, nx, p.cnt + 1));
                    visited[ny][nx] = true;
                }
            }
        }
        
        return -1;
    }
    
    boolean isSafe(int y, int x){
        return y >= 0 && y < map.length && x >= 0 && x < map[0].length;
    }
    
    static class Point{
        int y;
        int x;
        int cnt;
        
        public Point(int y, int x, int cnt){
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}