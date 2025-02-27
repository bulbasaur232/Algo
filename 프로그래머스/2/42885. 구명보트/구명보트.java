import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int ans = 0;
        int s = 0;
        int e = people.length - 1;
        Arrays.sort(people);
        
        while(s < e){
            if(people[s] + people[e] <= limit){
                s++;
                e--;
            } else {
                e--;
            }
            ans++;
        }
        
        if(s == e){
            ans++;
        }
        
        return ans;
    }
}