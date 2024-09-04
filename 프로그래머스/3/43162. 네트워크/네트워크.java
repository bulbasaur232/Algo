import java.util.*;

class Solution {
    
    int[][] connect;
    boolean[] visited;
    int n;
    
    public int solution(int nn, int[][] computers) {
        int cnt = 0;
        n = nn;
        connect = computers;
        
        visited = new boolean[n];
        
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                bfs(i);
                cnt++;
            }
        }
        return cnt;
    }
    
    public void bfs(int start){
        Queue<Integer> q = new ArrayDeque();
        q.offer(start);
        visited[start] = true;
        
        while(!q.isEmpty()){
            int now = q.poll();
            
            for(int i = 0; i < n; i++){
                if(i != now && !visited[i] && connect[now][i] == 1){
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}