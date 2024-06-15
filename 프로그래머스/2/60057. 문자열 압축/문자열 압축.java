import java.util.*;

/* 
한 문자열안에서 여러 단위로 자르는 것은 불가능
즉, 압축심볼의 크기를 찾는 문제임
*/

class Solution {
    public int solution(String s) {
        int minLen = s.length();
        for(int unit = 1; unit <= s.length(); unit++){
            String prev = "";
            int redundant = 1;
            StringBuilder compact = new StringBuilder();
            for(int idx = 0; idx < s.length(); idx += unit){
                String now;
                
                if(idx + unit >= s.length()){
                    now = s.substring(idx);
                    
                    if(now.equals(prev)){
                        redundant++;
                        compact.append(redundant).append(prev);
                    }  else {
                        if(redundant != 1)
                           compact.append(redundant);
                        compact.append(prev);
                        compact.append(now);
                    }
                    break;
                } else {
                   now = s.substring(idx, idx + unit);       
                }
                
                if(now.equals(prev)){
                    redundant++;
                } else {
                    if(idx != 0){
                        if(redundant != 1)
                           compact.append(redundant);
                        compact.append(prev);
                    }
                    redundant = 1;
                    prev = now;
                }
            }
            minLen = Math.min(minLen, compact.length());
        }
        
        return minLen;
    }
}