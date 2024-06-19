import java.util.*;

/*
배달 먼저하고 남은 공간에 빈 상자 실어서 오기
1. 매번 배달배열, 수거배열의 맨 끝부터 cap만큼 감소시키기
2. 이 떄 이동거리는 가장 끝 인덱스
*/

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int d = n - 1;
        int p = n - 1;
        
        while(d != -1 || p != -1){
            int dist = -1;
            int c = cap;
            if(d != -1){
                while(d != -1 && c != 0){
                    if(deliveries[d] > 0){
                        dist = Math.max(dist, d);
                        int minus = Math.min(deliveries[d], c);
                        deliveries[d] -= minus;
                        c -= minus;
                    } else {
                        d--;
                    }
                }
            }
            
            c = cap;
            if(p != -1){
                while(p != -1 && c != 0){
                    if(pickups[p] > 0){
                        dist = Math.max(dist, p);
                        int minus = Math.min(pickups[p], c);
                        pickups[p] -= minus;
                        c -= minus;
                    } else {
                        p--;
                    }
                }
            }
            answer += (dist + 1) * 2;
        }
        
        return answer;
    }
}