import java.util.*;

/*
1. 모든 출입구를 우선순위 큐에 넣는다.
2. 다익스트라 알고리즘 실행
3. 산봉우리에 도착할때 멈추는데 최소 intensity를 가지는 산봉우리는 모두 돌아봐야 한다. <- 숫자가 큰 산봉우리에 먼저 도착할 수 있음
*/

class Solution {
    int n;
    int[][] paths;
    int[] gates;
    int[] summits;
    
    ArrayList<Edge>[] edges;
    Set<Integer> summitSet;
    int[] min;
    
    int ansSummit;
    int ansIntensity;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        init(n, paths, gates, summits);
        dijkstra();
        
        return new int[]{ansSummit, ansIntensity};
    }
    
    void init(int n, int[][] paths, int[] gates, int[] summits){
        this.n = n;
        this.paths = paths;
        this.gates = gates;
        this.summits = summits;
        ansSummit = Integer.MAX_VALUE;
        ansIntensity = Integer.MAX_VALUE;

        summitSet = new HashSet<>();
        for(int summit : summits){
            summitSet.add(summit);
        }
        
        edges = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            edges[i] = new ArrayList();
        }
        for(int[] path : paths){
            edges[path[0]].add(new Edge(path[0], path[1], path[2]));
            edges[path[1]].add(new Edge(path[1], path[0], path[2]));
        }
    }
    
    void dijkstra(){
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.intensity - e2.intensity);
        min = new int[n + 1];
        Arrays.fill(min, 10000001);
        int minIntensity = Integer.MAX_VALUE;
        
        for(int gate : gates){
            min[gate] = 0;
            for(Edge e : edges[gate]){
                pq.offer(e);
            }
        }
        
        while(!pq.isEmpty()){
            Edge now = pq.poll();
            
            if(now.intensity > minIntensity)
                break;
            
            if(min[now.to] < now.intensity)
                continue;
            
            if(summitSet.contains(now.to)){
                minIntensity = now.intensity;
                ansIntensity = minIntensity;
                ansSummit = Math.min(ansSummit, now.to);
                continue;
            }
            
            for(Edge next : edges[now.to]){
                int maxIntensity = Math.max(now.intensity, next.intensity);
                if(maxIntensity < min[next.to]){
                    next.intensity = maxIntensity;
                    pq.offer(next);
                    min[next.to] = maxIntensity;
                }
            }
        }
        
    }
    
    static class Edge{
        int from;
        int to;
        int intensity;
        
        public Edge(int from, int to, int intensity){
            this.from = from;
            this.to = to;
            this.intensity = intensity;
        }
    }
}