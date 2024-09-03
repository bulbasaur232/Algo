import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
         int[] days = new int[speeds.length];
        for(int i = 0; i < speeds.length; i++){
            days[i] = (int)Math.ceil((100 - progresses[i]) / (double)speeds[i]);
        }
        
        List<Integer> list = new ArrayList<>();
        int cnt = 1;
        int a = days[0];

        for(int i = 1; i < days.length; i++){
            int b = days[i];
            if(a >= b){
                cnt++;
            } else {
                list.add(cnt);
                cnt = 1;
                a = b;
            }
        }
        
        list.add(cnt);
        
        
        int[] ret = new int[list.size()];
        for(int i = 0; i < ret.length; i++){
            ret[i] = list.get(i);
        }
        
        return ret;
        
    }
}