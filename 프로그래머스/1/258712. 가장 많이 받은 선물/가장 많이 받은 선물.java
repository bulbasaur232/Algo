import java.util.*;

/*
필요한건 
1. 두 친구간에 선물 주고 받은 횟수를 기록한 맵
2. 받은 선물 개수 저장하는 맵
3. 준 선물 개수 저장하는 맵

아래와 같은 풀이도 가능하지만  <String, Integer> 맵을 하나 만들어서 이름을 주면 인덱스를 반환하도록 하여 배열로 하는 풀이도 가능
*/

class Solution {
    public int solution(String[] friends, String[] gifts) {
        HashMap<Pair, Integer> record = new HashMap<>();
        HashMap<String, Integer> give = new HashMap<>();
        HashMap<String, Integer> take = new HashMap<>();
        HashMap<String, Integer> next = new HashMap<>();
        int result = 0;
        
        for(String s : friends){
            next.put(s, 0);
            give.put(s, 0);
            take.put(s, 0);
        }
        
        for(String s : gifts){
            StringTokenizer st = new StringTokenizer(s);
            String from = st.nextToken();
            String to = st.nextToken();
            Pair pair = new Pair(from, to);
            
            if(record.containsKey(pair)){
                record.put(pair, record.get(pair) + 1);
            } else {
                record.put(pair, 1);
            }
            
            give.put(from, give.get(from) + 1);
            
            take.put(to, take.get(to) + 1);
        }
        
        for(int i = 0; i < friends.length - 1; i++){
            for(int j = i + 1; j < friends.length; j++){
                String a = friends[i];
                String b = friends[j];
                
                int atob = record.getOrDefault(new Pair(a, b), 0);
                int btoa = record.getOrDefault(new Pair(b, a), 0);
                
                if(atob > btoa){
                    next.replace(a, next.get(a) + 1);
                    continue;
                } else if( btoa > atob){
                    next.replace(b, next.get(b) + 1);
                    continue;
                }
                
                int aidx = give.get(a) - take.get(a);
                int bidx = give.get(b) - take.get(b);
                
                if(aidx > bidx){
                    next.replace(a, next.get(a) + 1);
                } else if(bidx > aidx){
                    next.replace(b, next.get(b) + 1);
                }
            }
        }
        
        for(int idx : next.values()){
            result = Math.max(result, idx);
        }
        
        return result;
    }
    
    static class Pair{
        String from;
        String to;
        
        public Pair(String from, String to){
            this.from = from;
            this.to = to;
        }
        
        @Override
        public boolean equals(Object o){
            if(o instanceof Pair){
                Pair p = (Pair)o;
            return this.from.equals(p.from) && this.to.equals(p.to);
            } else {
                return false;
            }
        }
        
        @Override
        public int hashCode(){
            return Objects.hash(from, to);
        }
    }
    
}