import java.util.*;

/*
1. 곡괭이 한 개 골라서 5개 캐는거 반복
2. 만약 이미 ans보다 크면 백트래킹
*/

class Solution {
    int[] picks;
    String[] minerals;
    int ans;
    int[][] fatigue;
    int pickN;
    
    public int solution(int[] picks, String[] minerals) {
        init(picks, minerals);
        dig(0, 0);
        return ans;
    }
    
    public void dig(int depth, int sum){
        // sum 백트래킹
        if(sum >= ans)
            return;
        
        // 곡괭이를 다 썼거나 or 광물을 다 캤거나
        if((depth == pickN) || (depth * 5 >= minerals.length)){
            ans = Math.min(ans, sum);
            return;
        }
        
        // 곡괭이 한개 골라서 다섯개 캐기
        for(int i = 0; i < 3; i++){
            if(picks[i] > 0){
                picks[i]--;
                
                int border = depth * 5 + 5;
                int adder = 0;
                for(int j = depth * 5; j < border && j < minerals.length;  j++){
                    adder += calc(i, minerals[j]);
                }
                dig(depth + 1, sum + adder);
                picks[i]++;
            }
        }
    }
    
    public int calc(int pick, String mineral){
        if(mineral.equals("diamond"))
            return fatigue[pick][0];
        else if(mineral.equals("iron"))
            return fatigue[pick][1];
        else 
            return fatigue[pick][2];
    }
    
    public void init(int[] picks, String[] minerals){
        this.picks = picks;
        this.minerals = minerals;
        ans = Integer.MAX_VALUE;
        fatigue = new int[3][3];
        fatigue[0][0] = 1;
        fatigue[0][1] = 1;
        fatigue[0][2] = 1;
        fatigue[1][0] = 5;
        fatigue[1][1] = 1;
        fatigue[1][2] = 1;
        fatigue[2][0] = 25;
        fatigue[2][1] = 5;
        fatigue[2][2] = 1;
        pickN = 0;
        for(int i = 0; i < picks.length; i++){
            pickN += picks[i];
        }
    }
}