import java.util.*;

/*
x^2 + x^2 와 (x-1)^2 + (x+1)^2 을 비교해보면 항상 평탄하게 만들어야 최소가 된다.
*/

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i : works){
            pq.offer(i);
        }
        
        while(!pq.isEmpty() && n > 0){
            int i = pq.poll();
            i--;
            n--;
            
            if(i != 0)
                pq.offer(i);
        }
        
        long ret = 0;
        for(int i : pq){
            ret += (long)i * i;
        }
        
        return ret;
    }  
}