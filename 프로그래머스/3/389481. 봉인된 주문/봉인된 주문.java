import java.util.*;

/*
0. 26진법을 쓰자. <- 1을 0처럼 쓰자.
1. 이분탐색으로 n이 들어갈 자리 찾기 <- bans가 안길어서 이분탐색 아니어도 가능
2. n 앞에 숫자 몇개 있는지 파악해서 n에서 빼주기
3. 뺀 숫자를 알파벳으로 바꾸기
   */

class Solution {
    public String solution(long n, String[] bans) {
        String ns = ntos(n);
        Arrays.sort(bans, Comparator.comparing(String::length)
                   .thenComparing(String::compareTo));
        
        for(String s : bans){
            if(s.length() < ns.length()){
                ns = ntos(++n);
            } else if(s.length() == ns.length() && s.compareTo(ns) <= 0){
                ns = ntos(++n);
            } else {
                break;
            }
        }

        return ns;
    }
    
    // 숫자를 문자열로 변환
    public String ntos(long n){
        StringBuilder sb = new StringBuilder();
        while(n-- >= 26){
            long left = n % 26;
            n = n / 26;
            sb.append((char)(left + 'a'));
        }
        
        sb.append((char) (n + 'a'));
        return sb.reverse().toString();
    }
}
