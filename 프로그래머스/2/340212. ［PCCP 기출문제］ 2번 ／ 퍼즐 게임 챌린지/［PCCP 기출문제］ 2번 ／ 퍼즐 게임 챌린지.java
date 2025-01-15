import java.util.*;

class Solution {
    int[] diffs;
    int[] times;
    long limit;
    int high;
    int low;
    
    public int solution(int[] diffs, int[] times, long limit) {
        this.diffs = diffs;
        this.times = times;
        this.limit = limit;
        high = Integer.MIN_VALUE;
        low = Integer.MAX_VALUE;
        
        for(int i = 0; i < diffs.length; i++){
            if(diffs[i] > high)
                high = diffs[i];
            if(diffs[i] < low)
                low = diffs[i];
        }
        int ans = Integer.MAX_VALUE;
        int mid = (high + low) / 2;
        
        while(high >= low){
            long time = calc(mid);
            if(time == limit){
                ans = mid;
                break;
            } else if(time > limit){
                low = mid + 1;
            } else {
                ans = Math.min(ans, mid);
                high = mid - 1;
            }
            
            mid = (high + low) / 2; 
        }
        
        return ans;
    }
    
    public long calc(int level){
        long sum = 0;
        for(int i = 0; i < diffs.length; i++){
            if(level >= diffs[i]){
                sum += times[i];
            } else {
                sum += (diffs[i] - level) * (times[i] + times[i - 1]) + times[i];
            }
        }
        
        return sum;
    }
    
}