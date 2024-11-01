import java.util.*;

class Solution {
    
    int result = 0;
    int[] nums;
    int t;
    
    public int solution(int[] numbers, int target) {
        this.nums = numbers;
        t = target;
        solve(0, 0);
        
        return result;
    }
    
    public void solve(int depth, int val){
        if(depth == nums.length){
            if(val == t)
                result++;
            return;
        }
        solve(depth + 1, val + nums[depth]);
        solve(depth + 1, val - nums[depth]);
    }
}