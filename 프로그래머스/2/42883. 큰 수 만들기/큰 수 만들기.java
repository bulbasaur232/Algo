import java.util.*;

class Solution {
    public String solution(String number, int k) {
        int cnt = 0;
        StringBuilder sb = new StringBuilder(number);
        
        int i = 0;
        loop:
        while(cnt < k){
            for(; i < sb.length() - 1; i++){
                if(sb.charAt(i) < sb.charAt(i + 1)){
                    sb.deleteCharAt(i);
                    cnt++;
                    if(i > 0)i--;
                    continue loop;
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            cnt++;
        }        
        return sb.toString();
    }
}
