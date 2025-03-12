import java.util.*;

/*
0. 26진법을 쓰자. <- 1을 0처럼 쓰자.
1. n을 알파벳으로 바꾸기
2. bans 오름차순 정렬
3. for문 돌면서 비교해보며 나보다 작은 친구 있으면 앞당겨지므로 알파벳 하나 뒷걸로 교체
4. 이제 더이상 앞당겨지지 않으면 break;
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
