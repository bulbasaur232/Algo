import java.util.*;

/*
1. 정렬 후 투포인터로 가장 가벼운거랑 무거운거 태움
2. 투포인터가 만날 때까지 진행하며 못태운거는 혼자 타라해
*/

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int s = 0;
        int e = people.length - 1;
        
        
        // 3개일 경우는?
        while(s <= e){
            int sum = people[s] + people[e];
            if(sum <= limit){
                s++;
                e--;
            } else {
                e--;
            }
            answer++;
        }
        
        return answer;
    }
}

    
    
    
    