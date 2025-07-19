import java.util.*;

/*
1. 처음에 한번 순회돌면서 제일 큰 숫자 찾기, 각 숫자 카운팅
2. 큐 반복순회하면서 빼기
*/

class Solution {
    PriorityQueue<Integer>  bigPq; // 가장 큰 숫자 저장
    HashMap<Integer, Integer> map; // 숫자 카운팅
    Queue<Num> numQ; // 숫자 큐
    int order;
    
    public int solution(int[] priorities, int location) {
        bigPq = new PriorityQueue<>(Comparator.reverseOrder());
        map = new HashMap();
        numQ = new ArrayDeque<>(); 
        order = 1;
        
        int c = 0;
        for(int i : priorities){
            if(c == location){
                numQ.offer(new Num(i, true));
            } else {
                numQ.offer(new Num(i, false));
            }
            
            if(!bigPq.contains(i))
                bigPq.offer(i);
            
            if(!map.containsKey(i)){
                map.put(i, 1);
            }else {
                map.replace(i, map.get(i) + 1);
            }
            c++;
        }
        
        while(!bigPq.isEmpty()){
            int b = bigPq.poll();
            int cnt = map.get(b);
            
            while(cnt > 0){
                Num now = numQ.poll();
                
                if(now.n == b){
                    if(now.isTarget)
                        return order;
                    order++;
                    cnt--;
                } else {
                    numQ.offer(now);
                }
            }
        }
        return -1;
    }
    
    static class Num{
        int n;
        boolean isTarget;
        
        public Num(int n, boolean isTarget){
            this.n = n;
            this.isTarget = isTarget;
        }
    }
}