import java.util.*;

/*
진입차수, 진출차수 이용
*/

class Solution {
    int[] in;
    int[] out;
    int generate, doughnut, bar, eight;
    int max;
    boolean[] exist;
    
    public int[] solution(int[][] edges) {
        in = new int[1000001];
        out = new int[1000001];
        exist = new boolean[1000001];
        
        for(int[] edge : edges){
            out[edge[0]] += 1;
            in[edge[1]] += 1;
            max = Math.max(max, edge[0]);
            max = Math.max(max, edge[1]);
            exist[edge[0]] = true;
            exist[edge[1]] = true;
        }
        
        for(int i = 1; i <= max; i++){
            if(!exist[i])
                continue;
            
            if(out[i] == 0){
                bar++;
            } else if(out[i] >= 2){
                if(out[i] == 2 && in[i] >= 2){
                    eight++;
                } else if(in[i] == 0){
                    generate = i;
                }
            }
        }
        doughnut = out[generate] - eight - bar;
        
        return new int[]{generate, doughnut, bar, eight};
    }
}